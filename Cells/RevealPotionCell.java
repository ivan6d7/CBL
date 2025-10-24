package Cells;

import javax.swing.ImageIcon;

import main.MineField;
import main.game;

public class RevealPotionCell extends EmptyCell{

    ImageIcon revealIcon = new ImageIcon("sprites/items/revealPotionFull.png");

    public RevealPotionCell(int row, int column, MineField mineField) {
        super(row, column, mineField);
    }

    @Override
    public void reveal(int row, int col) {

        System.out.println("clicked");

        if (!isRevealed) {
            game.playerPanel.itemPanel.revealPotionButton.update(true);
            this.isRevealed = true;
            this.iconSet = true;
            this.checkWinCondition(); 
            super.reveal(row, col);
            this.setIcon(revealIcon);
        }

        super.reveal(row, col);
        
        this.setIcon(revealIcon);
        this.checkWinCondition(); 
    }
    
}
