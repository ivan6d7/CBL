package SaveSystem;

import java.io.FileWriter;
import java.io.IOException;
import main.game;

public class saveWriter {
    
    public static String lifeCountPath = "saveFiles_txt/lifeCount.txt";

    public static void save() {
        
        try (FileWriter writer = new FileWriter(lifeCountPath)) {
            writer.write((String.valueOf(game.lifeCount)));
            System.out.println("File written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    

}
