package Cells;

import javax.swing.ImageIcon;

import main.MineField;
import main.game;

public class HealPotionCell extends EmptyCell{

    String pathToIcon = "sprites/healing_potion_icon.png";
    ImageIcon icon = new ImageIcon(pathToIcon);
    
    public HealPotionCell(int row, int column, MineField mineField) {
        super(row, column, mineField);
    }

    @Override
    public void reveal(int row, int col) {

        System.out.println("clicked");

        if (!isRevealed) {
            game.lifeCount += 1;
            game.playerPanel.updateData();
        }

        super.reveal(row, col);        

        this.setIcon(icon);
    }
}
