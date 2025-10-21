package Levels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class levels {
    public int levelLength;
    public int levelHeight;
    public int mineCount;

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
