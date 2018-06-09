import javax.swing.*;
import java.awt.event.*;

class MainCharacter extends GameThing {

    private boolean isJump, isLeft, isRight, isShield, atTop, isShoot;
    private byte direction = 1, jumpHeight = 35, counter = 0;
    private Timer jumpTimer,shootLimit;

    MainCharacter(JFrame home, GameThing boss) {
        super(home, 200, home.getHeight()- 155, Resource.mainRestingRight);
        HP = 3;

        shootLimit = new Timer (500, e-> {
            counter ++;
            if(counter == 100000){
                counter = 0;
                isShoot = true;
                System.out.println("X");
            }
            if(isShoot) {
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

        /*heart1.setIcon(Resource.heartFull);
        heart2.setIcon(Resource.heartFull);
        heart3.setIcon(Resource.heartFull);
        setHPonFrame(home);*/

        jumpTimer = new Timer(30, e -> jumping());
    }

    void move() {
        if (isJump) {
            jump();
        }
        if (isLeft) {
            moveLeft();
        }
        if (isShield) {
            shielding();
        }
        if (isRight) {
            moveRight();
        }
        if(isShoot){
            shoot();
        }
//Pause screen code?
    }

    private void jump() {
        jumpTimer.start();
    }
    private void shoot(){
        shootLimit.start();
    }

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

    private void shielding() {
    }

    private void moveLeft() {
        if (!isRight && !isShield) {
            direction = -1;
            setIcon(Resource.mainRunningLeft);
            setLocation(getBounds().x - 7, getBounds().y);
        }
    }

    private void moveRight() {
        if (!isLeft && !isShield) {
            direction = 1;
            setIcon(Resource.mainRunningRight);
            setLocation(getBounds().x + 7, getBounds().y);
        }
    }

    private void releasedShield() {
        isShield = false;
        if (isLeft) {
            setIcon(Resource.mainRestingLeft);
        }
        setIcon(Resource.mainRunningRight);
    }

    private void pressedShield() {
        isRight = false;
        isLeft = false;
        isShield = true;
        setIcon(Resource.mCShield);
    }
    private void pressedShoot(){

    }
    private void releasedShoot(){}

    void setControls(JComponent RootPane) {

        addKeyBinder(RootPane, KeyEvent.VK_ESCAPE, "escape", escape -> System.exit(0), null);
//TODO: 29th May 2018: FIND MORE EFFICIENT METHOD TO STOP MOVING AND SET ICONS
        addKeyBinder(RootPane, KeyEvent.VK_A, "go left", pressedL -> isLeft = true, releasedL -> releasedLeft());

        addKeyBinder(RootPane, KeyEvent.VK_D, "go right", pressedR -> isRight = true, releasedR -> releasedRight());

        addKeyBinder(RootPane, KeyEvent.VK_W, "go up", pressedW -> isJump = true, releasedW -> isJump = false);

        addKeyBinder(RootPane, KeyEvent.VK_S, "go block", pressedS -> pressedShield(), releasedS -> releasedShield());

        addKeyBinder(RootPane, KeyEvent.VK_ENTER, "shoot", pressedEnter -> isShoot = true, releasedEnter -> releasedShoot());
    }

    void releasedLeft() {
        setIcon(Resource.mainRestingLeft);
        isLeft = false;
    }

    void releasedRight() {
        setIcon(Resource.mainRestingRight);
        isRight = false;
    }

    void addKeyBinder(JComponent jComponent, int keyCode, String id, ActionListener actionListener, ActionListener releasedListener) {

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

    int getHP() {
        return HP;
    }

    public void setHP(byte health) {
        HP = health;
    }
}