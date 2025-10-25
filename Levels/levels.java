package Levels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class called by the game class that loads all information about the level necessary for generation.
 *
 * The expected file is "levels_txt/lvl{levelNumber}.txt" and must contain three lines.
 * Example file contents:
 * 10
 * 15
 * 20
 *
 * Instances of this class expose the parsed values via public fields.
 */
public class levels {

    /**
     * Number of columns in the level (parsed from line 2 of the file).
     */
    public int levelLength;

    /**
     * Number of rows in the level (parsed from line 1 of the file).
     */
    public int levelHeight;

    /**
     * Number of mines for the level (parsed from line 3 of the file).
     */
    public int mineCount;

    /**
     * Construct a levels object by loading data for the given level number.
     * 
     * @param levelNumber index of the level file to load
     */
    public levels(int levelNumber) {

        try (BufferedReader br = new BufferedReader(
            new FileReader("levels_txt/lvl" + levelNumber + ".txt"))) {

            this.levelHeight = Integer.parseInt(br.readLine());
            this.levelLength = Integer.parseInt(br.readLine());
            this.mineCount = Integer.parseInt(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
