package main;

import Cells.*;
import Levels.levels;
import java.awt.*;
import javax.swing.*;



public class game {

    public static int fieldLength = 10;
    public static int fieldHeight = 10;
    public static int mineCount = 10;
    public static int cellsRevealed;
    public static int target = fieldLength * fieldHeight - mineCount;

    static MineField mineField = new MineField(fieldLength, fieldHeight, mineCount);

    public static int[][] field = mineField.field;

    public static EmptyCell[][] cellField = new EmptyCell[fieldLength][fieldHeight];

    public static JFrame frame = new JFrame();

    public static int levelNumber = 1; 

    public static void render() {

        levels level = new levels(levelNumber);

        fieldLength = level.levelLength;
        fieldHeight = level.levelHeight;
        mineCount = level.mineCount;
        target = fieldLength * fieldHeight - mineCount;

        mineField = new MineField(fieldLength, fieldHeight, mineCount);

        field = mineField.field;

        cellField = new EmptyCell[fieldLength][fieldHeight];

        cellsRevealed = 0;

        frame.getContentPane().removeAll();

        // 400 width and 500 height
        frame.setSize(mineField.length * 50, mineField.height * 50);

        JPanel panel = new JPanel(); //Jpanel with the game field
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
