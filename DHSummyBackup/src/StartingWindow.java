import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingWindow extends SuperFrame {
    JLabel title = new JLabel("DH SUMMY");
    JButton start = new JButton("Start"), score = new JButton("Score"), quit = new JButton("Quit");
    ActionListener quitAct = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }, play = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new Game();
        }
    };

    StartingWindow(){
        super();
        getContentPane().setBackground(Color.BLACK);
        title.setBounds(50,50,getWidth(),50);
        title.setOpaque(false);
        title.setFont(new Font("Monospaced", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        add(title);

        setJButton(start,320, play);
        setJButton(score,352,null);
        setJButton(quit,384,quitAct);
    }
    public void setJButton(JButton button , int yLoc, ActionListener action){
        button.setBounds(50,yLoc,150,32);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Monospaced", Font.BOLD, 32));
        button.setForeground(Color.WHITE);
        button.addActionListener(action);
        add(button);
    }

    public static void main(String[] args) {
        new StartingWindow();
    }
}
