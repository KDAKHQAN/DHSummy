
import javax.swing.*;

public class GameThing extends JLabel{

    JFrame home;

    GameThing(JFrame house, int xLoc, int yLoc, ImageIcon picture){
        home = house;
        setOnFrame(xLoc, yLoc, picture.getIconWidth(), picture.getIconHeight());
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.BOTTOM);
        setIcon(picture);
    }

    private void setOnFrame(int xLoc, int yLoc, int xSize, int ySize){
        setBounds(xLoc, yLoc, xSize, ySize);
        home.add(this, 0);
    }

    protected boolean collides(GameThing collidingWith){
        return (getLocation().x+getBounds().width == collidingWith.getLocation().x && getLocation().y+getBounds().height == collidingWith.getLocation().y);
    }
}