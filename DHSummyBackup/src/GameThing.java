
import javax.swing.*;

/**
 * This Class is the parent class to the all JLabel based objects in this project, used to hold methods that are shared accross the subclasses
 */
public class GameThing extends JLabel {
    //variables used in this class
    int HP;
    private boolean damaging = true;
    JFrame home;

    //Default constructor creates and sets the JLabel to the desired specifications
    GameThing(JFrame house, int xLoc, int yLoc, ImageIcon picture) {
        home = house;
        setOnFrame(xLoc, yLoc, picture.getIconWidth(), picture.getIconHeight());
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.BOTTOM);
        setIcon(picture);
    }

    //Method that sets the JLabel on the frame
    private void setOnFrame(int xLoc, int yLoc, int xSize, int ySize) {
        setBounds(xLoc, yLoc, xSize, ySize);
        home.add(this, 0);
    }

    protected void collides(GameThing collidingWith) {
        if (this.getX() + this.getWidth() == collidingWith.getX()) {
            collidingWith.HP--;//return (getLocation().x+getBounds().width == collidingWith.getLocation().x && getLocation().y+getBounds().height == collidingWith.getLocation().y);
        }
    }

    public boolean getDamaging(){
        return damaging;
    }

    public void setDamaging(boolean damage){
        damaging = damage;
    }

    public void decHP() {

        if (HP > 0) {

            HP--;

        }

    }

}