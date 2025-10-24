package SaveSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class saveReader {

    public static int readSaveLife() {
        try (BufferedReader br = new BufferedReader(
            new FileReader("saveFiles_txt/lifeCount.txt"))) {
            return Integer.parseInt(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int readSaveLevel() {
        try (BufferedReader br = new BufferedReader(
            new FileReader("saveFiles_txt/level.txt"))) {
            return Integer.parseInt(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

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
