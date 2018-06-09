import javax.swing.*;

public class Projectile extends GameThing{
    Timer projAct;
    Projectile(JFrame home, MainCharacter MC, int xLoc, int xVel, ImageIcon bullet,GameThing boss) {
        super(home,xLoc, MC.getY()+72, bullet);
        collides(boss);
        move(xVel, home);
    }

    public void move(int xVel, JFrame home){
        projAct = new Timer(30, e -> {
            setLocation(getBounds().x + xVel, getBounds().y);
            if(getX()>home.getWidth()){
                home.remove(this);
            }
            if(getX()<0){
                home.remove(this);
            }
            System.out.println(getX());
        });
        projAct.start();
    }
    /*public void collides(GameThing boss){
        if(this.getX()+this.getWidth() > boss.getX()){
            boss.HP --;
            home.remove(this);
            System.out.println(boss.HP);
        }
    };*/

}
