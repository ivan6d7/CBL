import java.awt.*;
import java.io.*;
import javax.swing.*;


public class game {

    int fieldLength = 10;
    int fieldHeight = 10;

    mineField mineField = new mineField(fieldLength, fieldHeight);


    void render() {

        JFrame frame = new JFrame();
        // 400 width and 500 height
        frame.setSize(mineField.length * 50, mineField.height * 50);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(fieldLength, fieldHeight));

        for (int y = 0; y < mineField.height; y++) {
            for (int x = 0; x < mineField.length; x++) {
                JButton cell = new JButton();
                cell.setSize(50, 50);
                cell.setMargin(new Insets(0, 0, 0, 0));
                cell.setBackground(Color.BLUE);
                panel.add(cell);
            }
        }

        frame.add(panel);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {      
        new game().render();
    }
}
