import javax.swing.*;
public class Minion extends GameThing {

    Timer CreepTimer;

    Minion(JFrame home) {
        super(home, 0, 495-Resource.bossCreep.getIconWidth(), Resource.bossCreep);
        move(home);
    }

    void move(JFrame home){
            CreepTimer = new Timer(30, e -> {
                setLocation(getBounds().x +10, getBounds().y);
                if(getX()-2>home.getWidth()){
                    home.remove(this);
                }
            });
            CreepTimer.start();
        }

        // public void setSize(){};


        // public boolean collides(JLabel db){
        //   return false;
    }


