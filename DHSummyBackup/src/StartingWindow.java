import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as the main menu, all other menus stem from this one
 */
public class StartingWindow extends SuperFrame {
    //Declaration of variables used in this class
    private JLabel title = new JLabel("DH SUMMY");
    private JButton start = new JButton("Start"), score = new JButton("Score"), quit = new JButton("Quit");
    private ActionListener quitAct = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }, play = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new Game();
        }
    };

    //default constructor uses super constructor to create frame then adds title and buttons to it
    StartingWindow() {
        super();
        getContentPane().setBackground(Color.BLACK);
        title.setBounds(50, 50, getWidth(), 50);
        title.setOpaque(false);
        title.setFont(new Font("Monospaced", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        add(title);

        setJButton(start, 320, play);
        setJButton(score, 352, null);
        setJButton(quit, 384, quitAct);
    }

    //Method to create and set the button and its functions onto the JFrame
    public void setJButton(JButton button, int yLoc, ActionListener action) {
        button.setBounds(50, yLoc, 150, 32);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Monospaced", Font.BOLD, 32));
        button.setForeground(Color.WHITE);
        button.addActionListener(action);
        add(button);
    }

    //Project Main
    public static void main(String[] args) {
        new StartingWindow();
    }
}
