import javax.swing.*;

/**
 * Projectile class controls the behaviour and attributes attached to the main character's projectiles
 */
public class Projectile extends GameThing {

    //default constructor calls superclass constructor (to create and add the label onto the frame) and calls the methods it needs to operate
    Projectile(JFrame home, MainCharacter MC, int xLoc, int xVel, ImageIcon bullet, GameThing boss) {
        super(home, xLoc, MC.getY() + 72, bullet);
        collides(boss);
        move(xVel, home);
    }

    //Method that moves the projectile in a straight line from the main character depending on the main character's direction(if left then it shoots left if right then it shoots right)
//This is done using a timer that continuously updates the projectile's location
    private void move(int xVel, JFrame home) {
        Timer projAct = new Timer(30, e -> {
            setLocation(getBounds().x + xVel, getBounds().y);
            if (getX() > home.getWidth()) {
                home.remove(this);
            }
            if (getX() < 0) {
                home.remove(this);
            }
        });
        projAct.start();
    }

    public void collides(GameThing boss) {
        Timer collisionTimer = new Timer(10, e -> {
            if (this.getBounds().intersects(boss.getBounds()) && getDamaging()) {
                boss.HP--;
                home.remove(this);
                setDamaging(false);
                System.out.println(boss.HP);
            }
        });
        collisionTimer.start();

    }


}
