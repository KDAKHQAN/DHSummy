import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**This class controls the way the boss's projectiles behave in the game*/
public class BossProjectile extends GameThing{
    boolean damaging = true;
    //Constructor creates and sets the projectile on the screen then calls the method that moves the projectile
    BossProjectile(JFrame home, int yLoc, int xVel, MainCharacter MC) {
        super(home, 800, yLoc, Resource.bossProjectile);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        setBorder(border);
        collides(MC);
        move(xVel, home);
    }
//method that moves the projectile, removing it from the frame if it exits the screen bounds, moves via timer that continously updates label's location
    private void move(int xVel, JFrame home){
        Timer HeartAttackTimer = new Timer(30, e -> {
            setLocation(getBounds().x - xVel, getBounds().y+3);
            if(getY()>home.getHeight()-50-getHeight()){
                setLocation(getX(),1000);
                home.remove(this);
            }
        });
        HeartAttackTimer.start();
    }
    public void collides(MainCharacter character) {
        Timer collisionTimer = new Timer(10, e -> {
            if (this.getBounds().intersects(character.getBounds()) && damaging && !character.isShield) {
                character.HP--;
                home.remove(this);
                damaging = false;
            }
        });
        collisionTimer.start();

    }
}
