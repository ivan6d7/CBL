package Levels;

import lib.*;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class levels {
    public static int levelLength;
    public static int levelHeight;
    public static int mineCount;

    static Gson gson = new Gson();

    public static levels generateLevel() {
        try (FileReader reader = new FileReader("Levels/level1.json")) {
            return gson.fromJson(reader, levels.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
