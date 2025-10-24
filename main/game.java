package main;

import Cells.*;
import Levels.levels;
import SaveSystem.saveReader;
import SaveSystem.saveWriter;
import UI.Screens.PlayerPanel;
import java.awt.*;
import javax.swing.*;



public class game {

    public static int fieldLength = 10;
    public static int fieldHeight = 10;
    public static int mineCount = 10;
    public static int cellsRevealed;
    public static int target = fieldLength * fieldHeight - mineCount;

    public static int flagsSet;
    public static int lifeCount = saveReader.readSaveLife();

    public static int levelNumber = saveReader.readSaveLevel(); 

    static MineField mineField;

    public static int[][] field;

    public static EmptyCell[][] cellField;
    public static MineCell[][] mineCellField;

    public static JFrame frame;

    public static JPanel mainPanel;

    public static PlayerPanel playerPanel;


    public static JPanel panel; //Jpanel with the game field

    public static void render() {

        panel = new JPanel();

        frame = new JFrame();

        mainPanel = new JPanel();

        cellField = new EmptyCell[fieldLength][fieldHeight];

        mineCellField = new MineCell[fieldLength][fieldHeight];

        saveWriter.saveLevel();

        playerPanel = new PlayerPanel();

        levels level = new levels(levelNumber);

        fieldLength = level.levelLength;
        fieldHeight = level.levelHeight;
        mineCount = level.mineCount;
        target = fieldLength * fieldHeight - mineCount;

        mineField = new MineField(fieldLength, fieldHeight, mineCount);

        field = mineField.field;

        cellField = new EmptyCell[fieldLength][fieldHeight];

        cellsRevealed = 0;

        flagsSet = 0;

        frame.getContentPane().removeAll();

        // 400 width and 500 height
        //frame.setSize(mineField.length * 50, mineField.height * 50);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(new GridLayout(fieldLength, fieldHeight));


        for (int y = 0; y < mineField.height; y++) {
            for (int x = 0; x < mineField.length; x++) {
                if (field[y][x] == 1) {
                    MineCell mineCell = new MineCell(y, x);

                    panel.add(mineCell);
                } else if (field[y][x] == 2) {

                    panel.add(new HealPotionCell(y, x, mineField));

                } else if (field[y][x] == 3) {

                    panel.add(new RevealPotionCell(y, x, mineField));

                } else if (field[y][x] == 4) {

                    panel.add(new DisarmKitCell(y, x, mineField));

                } else {
                    EmptyCell emptyCell = new EmptyCell(y, x, mineField);

                    panel.add(emptyCell);                    
                }

            }
        }

        frame.add(panel);
        frame.add(playerPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // center on screen
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
        
    }

    public static void main(String[] args) {  
        game.render();
    }
}
