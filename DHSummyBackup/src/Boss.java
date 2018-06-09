import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

class Boss extends GameThing {
    int HP = 100, counter = 0;
    Timer Attack;

    Boss(JFrame home) {
        super(home, home.getWidth() - Resource.bossRest.getIconWidth() + 50, home.getHeight() - Resource.bossRest.getIconHeight() - 56, Resource.bossRest);
        Attack();
    }

    public void HeartProjectileAttack(JFrame Home) {
        setIcon(Resource.bossKissing);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() + 46, 5);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() + 13, 10);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() + 10, 15);

    }

    public void CreepAttack(JFrame home) {
        setIcon(Resource.creepFace);
        new Minion(home);
    }

    public void Attack() {
        Attack = new Timer(500, e -> {
            counter++;
            if(counter == 5){
                    setIcon(Resource.bossRest);
            }
            if (counter == 20) {
                HeartProjectileAttack(home);
            }
            if(counter == 27){
                setIcon(Resource.bossRest);
            }
            if (counter == 35) {
                CreepAttack(home);
                counter = 0;
            }
            System.out.println("X");
        });
        Attack.start();
    }


    public void move() {

    }

    public boolean collides(JLabel collidingWith) {
        return false;
    }

    public void setSize() {
    }


}
