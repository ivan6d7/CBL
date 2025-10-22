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

}
