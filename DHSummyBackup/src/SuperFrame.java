import javax.swing.*;
import java.awt.*;

/**
 * this class is used to create JFrames in this project
 */
public class SuperFrame extends JFrame {
    //Constructor creates and sets a frame onto the screen
    SuperFrame() {
        setLayout(null);
        setSize(960, 540);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
//        setVisible(true);
    }
}
