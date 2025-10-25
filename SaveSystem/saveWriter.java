package SaveSystem;

import java.io.FileWriter;
import java.io.IOException;
import main.game;

/**
 * Class for writing simple game save data to plain-text files.
 *
 * The class writes life count, current level, and item availability to files
 * located in the saveFiles_txt directory.
 */
public class saveWriter {
    
    /**
     * Path to the file that stores the player's life count.
     */
    public static String lifeCountPath = "saveFiles_txt/lifeCount.txt";

    /**
     * Path to the file that stores the current level number.
     */
    public static String levelPath = "saveFiles_txt/level.txt";

    /**
     * Write the current game life count to the save file.
     */
    public static void saveLife() {
        
        try (FileWriter writer = new FileWriter(lifeCountPath)) {
            writer.write((String.valueOf(game.lifeCount)));
            System.out.println("File written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Write the current level number to the save file.
     */
    public static void saveLevel() {
        try (FileWriter writer = new FileWriter(levelPath)) {
            writer.write((String.valueOf(game.levelNumber)));
            System.out.println("File written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Save the current item status to the save file.
     */
    public static void saveItems() {
        try (FileWriter writer = new FileWriter("saveFiles_txt/items.txt")) {

            writer.write((String.valueOf(
                game.playerPanel.itemPanel.healingPotionButton.isActive())) + "\n");
            writer.write((String.valueOf(
                game.playerPanel.itemPanel.revealPotionButton.isActive())) + "\n");
            writer.write((String.valueOf(
                game.playerPanel.itemPanel.disarmKitButton.isActive())) + "\n");

            System.out.println("File written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Write 0 for all item statuses in the game file (used by game over button).
     */
    public static void saveItems(boolean B) {
        try (FileWriter writer = new FileWriter("saveFiles_txt/items.txt")) {

            writer.write((String.valueOf(0)) + "\n");
            writer.write((String.valueOf(0)) + "\n");
            writer.write((String.valueOf(0)) + "\n");

            System.out.println("File written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
