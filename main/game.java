package main;

import Cells.*;
import Levels.levels;
import SaveSystem.saveReader;
import SaveSystem.saveWriter;
import UI.Screens.PlayerPanel;
import java.awt.*;
import javax.swing.*;

/**
 * Main application class that initializes and runs the Minesweeper-like game.
 *
 * This class holds global game state (field size, mine count, revealed cells,
 * flags, save/load state) and is responsible for constructing the Swing UI,
 * creating the MineField, and populating the UI with cell components.
 */
public class game {

    /** Number of columns in the game field. */
    public static int fieldLength;

    /** Number of rows in the game field. */
    public static int fieldHeight;

    /** Total number of mines placed in the field. */
    public static int mineCount;

    /** Number of cells that have been revealed by the player. */
    public static int cellsRevealed;

    /** Number of non-mine cells that must be revealed to win (computed). */
    public static int target = fieldLength * fieldHeight - mineCount;

    /** Number of flags the player has placed. */
    public static int flagsSet;

    /** Player's remaining lives loaded from save. */
    public static int lifeCount = saveReader.readSaveLife();

    /** Current level number loaded from save. */
    public static int levelNumber = saveReader.readSaveLevel(); 

    /** The MineField model that contains the field data and game logic. */
    static MineField mineField;

    /** Raw integer matrix representation of the field (as provided by MineField). */
    public static int[][] field;

    /** 2D array of EmptyCell UI components for all cells. */
    public static EmptyCell[][] cellField;

    /** 2D array of MineCell UI components for mine cells. */
    public static MineCell[][] mineCellField;

    /** Main application window (JFrame). */
    public static JFrame frame;

    /** Root panel that holds the game field and player panel. */
    public static JPanel mainPanel;

    /** Top/bottom player status UI panel. */
    public static PlayerPanel playerPanel;

    /** JPanel that holds the grid of cell components representing the field. */
    public static JPanel panel; //Jpanel with the game field

    /**
     * Builds and shows the game UI for the current saved level.
     *
     * This method:
     * - Loads level settings and save state,
     * - (Re)creates the MineField model,
     * - Initializes Swing components and the grid of cell components,
     * - Packs and displays the main frame centered on screen.
     */
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

    /**
     * Application.
     */
    public static void main(String[] args) {  
        game.render();
    }
}
