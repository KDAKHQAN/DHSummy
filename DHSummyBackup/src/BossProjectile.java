import javax.swing.*;

public class BossProjectile extends GameThing{
Timer HeartAttackTimer;
    BossProjectile(JFrame home, int yLoc, int xVel) {
        super(home, 800, yLoc, Resource.bossProjectile);
        move(xVel, home);
    }

    public void move(int xVel, JFrame home){
        HeartAttackTimer = new Timer(30, e -> {
            setLocation(getBounds().x - xVel, getBounds().y+3);
            if(getY()>home.getHeight()-100){
                home.remove(this);
            }
        });
        HeartAttackTimer.start();
    }
    public boolean collides(JLabel collidingWith){return false;};

}