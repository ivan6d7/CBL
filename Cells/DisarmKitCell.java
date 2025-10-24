package Cells;

import javax.swing.ImageIcon;

import main.MineField;
import main.game;

public class DisarmKitCell extends EmptyCell{
    
    ImageIcon disarmIcon = new ImageIcon("sprites/items/defuseKitFull.png");

    public DisarmKitCell(int row, int column, MineField mineField) {
        super(row, column, mineField);
    }    

    @Override
    public void reveal(int row, int col) {

        System.out.println("clicked");

        if (!isRevealed) {
            game.playerPanel.itemPanel.disarmKitButton.update(true);
            this.isRevealed = true;
            this.checkWinCondition(); 
            super.reveal(row, col);
            this.setIcon(disarmIcon);
        }
        this.setIcon(disarmIcon);

        super.reveal(row, col);
        
        this.setIcon(disarmIcon);
    }

    @Override
    public void showValue() {
        if (!isRevealed) {
            game.playerPanel.itemPanel.disarmKitButton.update(true);
            this.isRevealed = true;
            this.iconSet = false;
            this.setIcon(disarmIcon);            
        }     

        this.setIcon(disarmIcon); 
        this.checkWinCondition(); 
    }

}
