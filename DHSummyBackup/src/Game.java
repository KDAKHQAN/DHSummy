import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Game extends SuperFrame{

    private int min, sec, mill;
    private Timer mainCharacterMove, scoreTimer;
    private ActionListener playerControls;
    private JLabel backGround = new JLabel();
    private JLabel timerFrame = new JLabel(min + ":" + sec + ":" + mill);
    private MainCharacter mainCharacter;
    private Boss huShawn;
    private GameThing heart1, heart2, heart3;

    Game() {
        super();
        setGame();

        makeBackground();
    }

    private void setMainCharacterControls(){
        mainCharacter.move();
        checkHP((byte) 2);
    }

    private void setTimer(){
        timerFrame.setFont(new Font("Monospaced", Font.BOLD, 18));
        timerFrame.setForeground(Color.RED);
        timerFrame.setIcon(Resource.timerFrame);
        timerFrame.setHorizontalTextPosition(JLabel.CENTER);
        timerFrame.setBounds(getWidth()-Resource.timerFrame.getIconWidth(), 0,Resource.timerFrame.getIconWidth(),Resource.timerFrame.getIconHeight() );
        add(timerFrame);
        scoreTimer = new Timer(10, e -> {
            mill++;
            if(mill == 100){
                mill = 0;
                sec++;
            }
            if(sec == 60){
                sec = 0;
                min++;
            }
            timerFrame.setText(min + ":" + sec + ":" + mill);
        });
        scoreTimer.start();

    }
    private void makeBackground(){
        setHearts();
        setTimer();
        backGround.setIcon(Resource.background);
        backGround.setBounds(0,0,getWidth(),getHeight());
        add(backGround);

    }

    private void setHearts(){
        heart1 = new GameThing(this, 0,0 ,Resource.heartFull);
        heart2 = new GameThing(this, Resource.heartFull.getIconWidth(),0, Resource.heartFull);
        heart3 = new GameThing(this,2* Resource.heartFull.getIconWidth(),0,Resource.heartFull);
    }

    private void setGame(){
        mainCharacter = new MainCharacter(this);
        playerControls = e -> mainCharacter.setControls(this.getRootPane());

        mainCharacterMove = new Timer(30, e -> {setMainCharacterControls();});
        mainCharacterMove.start();

        huShawn = new Boss(this );
    }

    private void checkHP(byte hp){ //TODO: Too much work to make efficient, try using array of JLabels to reduce amount of if-statements

        switch (hp){

            case 2: heart3.setIcon(Resource.heartEmpty);
                break;
            case 1: heart3.setIcon(Resource.heartEmpty);
                heart2.setIcon(Resource.heartEmpty);
                break;
            case 0: heart1.setIcon(Resource.heartEmpty);
                heart2.setIcon(Resource.heartEmpty);
                heart3.setIcon(Resource.heartEmpty);
                break;
        }

    }

    public static void main(String[] args) {
        Game game = new Game();
    }
}