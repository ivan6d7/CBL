package Cells;

import javax.swing.ImageIcon;

import UI.Screens.ItemPanel;
import UI.Screens.PlayerPanel;
import main.MineField;
import main.game;

public class HealPotionCell extends EmptyCell{

    public boolean iconSet;

    String pathToIcon = "sprites/healing_potion_icon.png";
    public ImageIcon potionIcon = new ImageIcon(pathToIcon);
    
    public HealPotionCell(int row, int column, MineField mineField) {
        super(row, column, mineField);

        this.iconSet = false;
        this.isRevealed = false;
    }


    @Override
    public void reveal(int row, int col) {

        System.out.println("clicked");

        if (!isRevealed) {
            game.playerPanel.itemPanel.healingPotionButton.update(true);
            this.isRevealed = true;
            this.iconSet = false;
            this.checkWinCondition(); 
            super.reveal(row, col);
            this.setIcon(potionIcon);   
        }
        this.setIcon(potionIcon); 

        super.reveal(row, col);
        
        this.setIcon(potionIcon); 

    }

    @Override
    public void showValue() {
        if (!isRevealed) {
            game.playerPanel.itemPanel.healingPotionButton.update(true);
            this.isRevealed = true;
            this.iconSet = false;
            this.setIcon(potionIcon);            
        }     

        this.setIcon(potionIcon);
        this.checkWinCondition(); 
    }


    @Override
    public void setDefaultIcon() {
        if (this.iconSet) {
            super.showValue();
        }
    }
}
