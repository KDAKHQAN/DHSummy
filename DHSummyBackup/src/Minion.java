import javax.swing.*;

/**
 * This class creates the minion used in the boss's creepattack, it is a source of damage for the boss to the main character
 */
public class Minion extends GameThing {
    boolean damaging = true;
    //constructor creates the minion and adds him to the frame as well as calls his movement method
    Minion(JFrame home, MainCharacter MC) {
        super(home, 0, 495 - Resource.bossCreep.getIconWidth(), Resource.bossCreep);
        collides(MC);
        move(home);
    }

    //Method that controls the way the minion moves (straight line), using a timer to continuously update the label's position
    void move(JFrame home) {
        Timer CreepTimer = new Timer(30, e -> {
            setLocation(getBounds().x + 10, getBounds().y);
            if (getX() - 2 > home.getWidth()) {
                home.remove(this);
            }
        });
        CreepTimer.start();
    }

    public void collides(MainCharacter character) {
        Timer collisionTimer = new Timer(10, e -> {
            if (this.getBounds().intersects(character.getBounds()) && damaging && !character.isShield) {
                character.HP--;
                home.remove(this);
                damaging = false;
                System.out.println(character.HP);
            }
        });
        collisionTimer.start();

    }
}


