import javax.swing.*;
import java.awt.event.*;

/**
 * Main Character class is used to control the behaviour of the player controlled maincharacter
 */
class MainCharacter extends GameThing {
    //Declaration of variables used in this class
    public boolean isJump, isLeft, isRight, isShield, atTop, isShoot;
    private byte direction = 1, jumpHeight = 35, counter = 0;
    private Timer jumpTimer, shootLimit;
    private GameThing boss;

    //Constructor creates and set the maincharacter onto a frame and sets the HP
    MainCharacter(JFrame home) {
        super(home, 200, home.getHeight() - 155, Resource.mainRestingRight);
        HP = 3;
//This timer is used to limit the player's ability to rapidly shoot projectiles
        shootLimit = new Timer(200, e -> {
            counter++;
            if (counter == 100000) {
                counter = 0;
                isShoot = true;
            }
            if (isShoot && !isShield) {
                if (direction == -1) {
                    new Projectile(home, this, getX() - Resource.bulletLeft.getIconWidth(), -15, Resource.bulletLeft, boss);
                    isShoot = false;
                }
                if (direction == 1) {
                    new Projectile(home, this, getX() + getWidth() - 10, 15, Resource.bulletRight, boss);
                    isShoot = false;
                }
            }
        });
        setControls(home.getRootPane());
//This timer is used to enable the main character to jump
        jumpTimer = new Timer(30, e -> jumping());
    }


    //Method that uses booleans attached to themain character to determine which action the player is looking for
    void move() {
        if (isJump) {
            jump();
        }
        if (isLeft) {
            left(isRight, isShield);
        }
        if (isShield) {
            shield(isRight, isLeft);
        }
        if (isRight) {
            right(isLeft, isShield);
        }
        if (isShoot) {
            shoot();
        }
//Pause screen code?
    }

    //Method that starts the jump timer above
    private void jump() {
        jumpTimer.start();
    }

    //Method implemented in jump timer above
    private void jumping() {

        if (jumpHeight == 0) {
            atTop = true;
        }
        if (!atTop) {
            jumpHeight -= 5;
            setLocation(getX(), getLocation().y - jumpHeight);
        } else {
            setLocation(getX(), getLocation().y + jumpHeight);
            jumpHeight += 5;
        }
        if (getLocation().y >= (home.getHeight() - getHeight()) - 50) {
            setLocation(getX(), home.getHeight() - getHeight() - 50);
            atTop = false;
            jumpHeight = 35;
            jumpTimer.stop();
        }
    }

    //this method is triggered when the player lets go of the button mapped to allow the main character to move left, it resets the character's icon and boolean to ensure the character stops moving left
    private void left() {
        setIcon(Resource.mainRestingLeft);
        isLeft = false;
    }

    //this method is triggered when the player presses the button mapped to move the main character left, it updates the icon
// and the location of the label as well as change the direction integer in order to help the shoot method determine which side to shoot
    private void left(boolean right, boolean shield) {
        if (!right && !shield) {
            direction = -1;
            setIcon(Resource.mainRunningLeft);
            setLocation(getBounds().x - 7, getBounds().y);
        }
    }

    //this method is triggered when the player lets go of the button mapped to allow the main character to move right, it resets the character's icon and boolean to ensure the character stops moving right
    private void right() {
        setIcon(Resource.mainRestingRight);
        isRight = false;
    }

    //this method is triggered when the player presses the button mapped to move the main character right, it updates the icon
// and the location of the label as well as change the direction integer in order to help the shoot method determine which side to shoot
    private void right(boolean left, boolean shield) {
        if (!left && !shield) {
            direction = 1;
            setIcon(Resource.mainRunningRight);
            setLocation(getBounds().x + 7, getBounds().y);
        }
    }

    //this method is triggered when the player lets go of the button mapped to allow the main character to shield, it resets the character's icon and boolean to ensure the character stops shielding
    private void shield() {
        isShield = false;
        if (isLeft) {
            setIcon(Resource.mainRestingLeft);
        }
        setIcon(Resource.mainRestingRight);
    }

    //this method is triggered when the player presses the button mapped to shield, it sets the direction booleans to false to ensure the player cannot abuse shield and move while shielding
    //this method also updates the icon
    private void shield(boolean right, boolean left) {
        right = false;
        left = false;
        setIcon(Resource.mCShield);
    }

    //this method is triggered when the player presses the button mapped to shoot, it starts the timer above that controls the shooting behaviour
    private void shoot() {
        shootLimit.start();
    }

    //this method terminates the shooting when the player releases the button mapped to shoot
    private void releasedShoot() {
    }

    //this method maps the controls to the desired keys
    void setControls(JComponent RootPane) {

        addKeyBinder(RootPane, KeyEvent.VK_ESCAPE, "escape", escape -> System.exit(0), null);
//TODO: 29th May 2018: FIND MORE EFFICIENT METHOD TO STOP MOVING AND SET ICONS
        addKeyBinder(RootPane, KeyEvent.VK_A, "go left", pressedL -> isLeft = true, releasedL -> left());

        addKeyBinder(RootPane, KeyEvent.VK_D, "go right", pressedR -> isRight = true, releasedR -> right());

        addKeyBinder(RootPane, KeyEvent.VK_W, "go up", pressedW -> isJump = true, releasedW -> isJump = false);

        addKeyBinder(RootPane, KeyEvent.VK_S, "go block", pressedS -> isShield = true, releasedS -> shield());

        addKeyBinder(RootPane, KeyEvent.VK_ENTER, "shoot", pressedEnter -> isShoot = true, releasedEnter -> releasedShoot());
    }

    //this method creates the keybinders to be used in mapping the controls
    private void addKeyBinder(JComponent jComponent, int keyCode, String id, ActionListener actionListener, ActionListener releasedListener) {

        InputMap inputMap = jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = jComponent.getActionMap();


        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, false), "Pressed " + id);
        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, true), "Released " + id);

        actionMap.put("Pressed " + id, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
            }
        });

        actionMap.put("Released " + id, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                releasedListener.actionPerformed(e);
            }
        });

    }

    public void setBoss(GameThing dong) {

        boss = dong;
    }
}