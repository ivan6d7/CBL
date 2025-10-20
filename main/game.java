package main;

import Cells.*;
import java.awt.*;
import javax.swing.*;



public class game {

    public static int fieldLength = 20;
    public static int fieldHeight = 20;
    public static int mineCount = 40;

    static MineField mineField = new MineField(fieldLength, fieldHeight, mineCount);

    public static int[][] field = mineField.field;

    public static EmptyCell[][] cellField = new EmptyCell[fieldLength][fieldHeight];

    public static JFrame frame = new JFrame();


    public static void render() {

        frame.getContentPane().removeAll();

        // 400 width and 500 height
        frame.setSize(mineField.length * 50, mineField.height * 50);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(fieldLength, fieldHeight));


        for (int y = 0; y < mineField.height; y++) {
            for (int x = 0; x < mineField.length; x++) {
                if (field[y][x] == 1) {
                    MineCell mineCell = new MineCell(y, x);

                    panel.add(mineCell);
                } else {
                    EmptyCell emptyCell = new EmptyCell(y, x, mineField);

                    panel.add(emptyCell);                    
                }

            }
        }

        frame.add(panel);
        frame.setLocationRelativeTo(null); // center on screen
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
        
    }

    public static void main(String[] args) {  
        game.render();
    }
}
