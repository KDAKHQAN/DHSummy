import javax.swing.*;
import java.awt.*;

/**
 * Game Class contains all the parts and objects needed to run the application (game logic)
 */
public class Game extends SuperFrame {
    //Declaration of variables used in Game class
    private int min, sec, mill;
    private JLabel backGround = new JLabel();
    private JLabel timerFrame = new JLabel(min + ":" + sec + ":" + mill);
    private MainCharacter mainCharacter;
    private Boss huShawn;
    private GameThing heart1, heart2, heart3;
    private String scoreTime = "";
    private Read_Write rw = new Read_Write();

    //Default constructor inherits superclass (SuperFrame) to create the jframe then add all of the components needed for the game
    Game() {
        super();
        setGame();
        makeBackground();
        setMainCharacterMove();
    }

    //Method that calls the movement code from the MainCharacter class, used to make the main character move
    private void setMainCharacterControls() {
        mainCharacter.move();
        checkMCHP(mainCharacter.HP);
        checkBOSSHP(huShawn.HP);
    }

    //Method that creates and starts timer seen in the top right corner of the screen, used for scoring purposes
    private void setTimer() {
        timerFrame.setFont(new Font("Monospaced", Font.BOLD, 18));
        timerFrame.setForeground(Color.RED);
        timerFrame.setIcon(Resource.timerFrame);
        timerFrame.setHorizontalTextPosition(JLabel.CENTER);
        timerFrame.setBounds(getWidth() - Resource.timerFrame.getIconWidth(), 0, Resource.timerFrame.getIconWidth(), Resource.timerFrame.getIconHeight());
        add(timerFrame);
        scoreTimer();

    }

    //Method that adds the non object GUI to the frame, adds health indicators (hearts), timer and background
    private void makeBackground() {
        setHearts();
        setTimer();
        backGround.setIcon(Resource.background);
        backGround.setBounds(0, 0, getWidth(), getHeight());
        add(backGround);

    }

    //Creates the hearts and adds them onto the JFrame
    private void setHearts() {
        heart1 = new GameThing(this, 0, 0, Resource.heartFull);
        heart2 = new GameThing(this, Resource.heartFull.getIconWidth(), 0, Resource.heartFull);
        heart3 = new GameThing(this, 2 * Resource.heartFull.getIconWidth(), 0, Resource.heartFull);
    }

    //Method that creates and adds the objects to the game (boss and maincharacter) and what they need to work
    private void setGame() {
        mainCharacter = new MainCharacter(this);
        huShawn = new Boss(this);
        mainCharacter.setBoss(huShawn);
        huShawn.setMC(mainCharacter);
        huShawn.Attack();
    }

    //Method that checks the maincharacter's HP and adjusts the health indicators accordingly (hearts become empty if health is missing) and insures that the hp variable is within the proper range
    private void checkMCHP(int hp) { //TODO: Too much work to make efficient, try using array of JLabels to reduce amount of if-statements

        switch (hp) {
            case 2:
                heart3.setIcon(Resource.heartEmpty);
                break;
            case 1:
                heart2.setIcon(Resource.heartEmpty);
                break;
            case 0:
                heart1.setIcon(Resource.heartEmpty);
                mainCharacter.HP = 0;
                dispose();
                break;
        }

    }

    private void scoreTimer() {
        Timer scoreTimer = new Timer(10, e -> {
            mill++;
            if (mill == 100) {
                mill = 0;
                sec++;
            }
            if (sec == 60) {
                sec = 0;
                min++;
            }
            scoreTime = min + ":" + sec + ":" + mill;
            timerFrame.setText(scoreTime);
        });
        scoreTimer.start();
    }

    private void checkBOSSHP(int hp) { //TODO: Too much work to make efficient, try using array of JLabels to reduce amount of if-statements

        switch (hp) {
            case 40:

                int temp = timeToInt(min, sec, mill);
                rw.writeMessage(scoreTime, temp);
                huShawn.HP = 0;
                dispose();
                break;
        }
    }

    private void setMainCharacterMove() {
        Timer mainCharacterMove = new Timer(30, e -> {
            setMainCharacterControls();
        });
        mainCharacterMove.start();
    }

    private int timeToInt(int min, int sec, int mill) {

        return min * 3600 + sec * 60 + mill;

    }
}