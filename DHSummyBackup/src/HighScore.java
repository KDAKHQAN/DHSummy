import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighScore extends SuperFrame {

    private JList List;//stores the all the recorded high scores
    private JScrollPane ScrollView = new JScrollPane();//displays the high scores
    private ArrayList<Integer> items;//temporary store for the high score
    private Read_Write rw = new Read_Write();

    public HighScore() {

        rw.readFile();

        items = rw.allHighScore;

        //displays high score
        ScrollView.setBounds(0, 0, getWidth(), getHeight());
        ScrollView.setForeground(Color.WHITE);

        //displays the new high scores again
        List = new JList(items.toArray());
        ScrollView.setViewportView(List);

        add(ScrollView);

        setVisible(false);

    }

}
