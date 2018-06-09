import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

class Boss extends GameThing {
    Timer HeartAttackTimer;

    Boss(JFrame home) {
        super(home, home.getWidth() - Resource.bossRest.getIconWidth() + 50, home.getHeight() - Resource.bossRest.getIconHeight() - 56, Resource.bossRest);
        //HeartProjectileAttack(home);
        CreepAttack(home);
    }

    public void HeartProjectileAttack(JFrame Home) {
        setIcon(Resource.bossKissing);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() - 56, 4);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() - 33, 7);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() - 10, 10);

    }
    public void CreepAttack(JFrame home){
        setIcon(Resource.creepFace);
        new Minion(home);
    }


    public void move() {

    }

    public boolean collides(JLabel collidingWith) {
        return false;
    }

    public void setSize() {
    }


}
