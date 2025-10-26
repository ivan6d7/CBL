package SaveSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for reading saved game stats from text files.
 */
public class saveReader {

    /**
     * Read the saved life count.
     *
     * The method reads the first line of the file
     * "saveFiles_txt/lifeCount.txt" and parses it as an integer.
     *
     * @return the parsed life count, or 0 if the file cannot be read or parsed
     */
    public static int readSaveLife() {
        try (BufferedReader br = new BufferedReader(
            new FileReader("saveFiles_txt/lifeCount.txt"))) {
            return Integer.parseInt(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Read the saved current level number.
     *
     * The method reads the first line of the file
     * "saveFiles_txt/level.txt" and parses it as an integer.
     *
     * @return the parsed level number, or 0 if the file cannot be read or parsed
     */
    public static int readSaveLevel() {
        try (BufferedReader br = new BufferedReader(
            new FileReader("saveFiles_txt/level.txt"))) {
            return Integer.parseInt(br.readLine());

        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Read saved item availability flags.
     *
     * The method expects "saveFiles_txt/items.txt" to contain three lines,
     * each an integer representing an item status. On success this returns an
     * Integer array of length 3 with parsed values.
     *
     * @return an Integer array of length 3 with item statuses, or a new Integer[2]
     *         if the file cannot be read or parsed; in case of error the stack
     *         trace is printed
     */
    public static Integer[] readItems() {

        Integer[] itemStatus = new Integer[3];

        try (BufferedReader br = new BufferedReader(
            new FileReader("saveFiles_txt/items.txt"))) {
            itemStatus[0] = Integer.parseInt(br.readLine());
            itemStatus[1] = Integer.parseInt(br.readLine());
            itemStatus[2] = Integer.parseInt(br.readLine());
            return itemStatus;
        } catch (IOException e) {
            e.printStackTrace();
            return new Integer[2];
        }
    }


}
