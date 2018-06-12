import java.io.*;
import java.util.*;

//file IO class
public class Read_Write {

    private String highFileName = "High Score.txt";
    private String txtFileName = "Time Display.txt";
    private String path = "/src/Resources/All_Data/";
    private String txtDisplay = "";
    private int currentHighScore = Integer.MAX_VALUE;
    public ArrayList<Integer> allHighScore = new ArrayList<>();

    //reads high score file
    public void readFile() {

        try {

            Scanner readHigh = new Scanner(new File(System.getProperty("user.dir") + path + highFileName));//high score file
            Scanner readTxt = new Scanner(new File(System.getProperty("user.dir") + path + txtFileName));//DLC password file

            //while there's a next line in the high score window
            while (readHigh.hasNext()) {

                allHighScore.add(readHigh.nextInt());

            }

            while (readTxt.hasNext()) {

                txtDisplay = readTxt.nextLine();

            }

            if (!allHighScore.isEmpty()) {

                selectionSort(allHighScore);

            }

            if (!allHighScore.isEmpty()) {
                currentHighScore = allHighScore.get(0);
            }
            readHigh.close();
        } catch (FileNotFoundException e) {
        }
    }

    public void writeMessage(String txt, int score) {

        try {

            PrintWriter outputStreamScore = new PrintWriter(new FileWriter(System.getProperty("user.dir") + path + highFileName, true));
            PrintWriter outputStreamTxt = new PrintWriter(System.getProperty("user.dir") + path + txtFileName);

            if (score < currentHighScore) {

                outputStreamScore.println(score);
                outputStreamTxt.println(txt);

                outputStreamScore.close();
                outputStreamTxt.close();

            }


        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

    }

    public void selectionSort(ArrayList<Integer> arr) {

        for (int i = 0; i < arr.size() - 1; i++) {

            int index = i;
            for (int j = i + 1; j < arr.size(); j++) {

                if (arr.get(j) < arr.get(index)) {
                    index = j;
                }

            }

            switchNums(i, index, arr);
        }

    }

    //help method for selection sort
    private void switchNums(int i, int index, ArrayList<Integer> a) {

        int temp = a.get(i);
        a.set(i, a.get(index));
        a.set(index, temp);

    }

}
