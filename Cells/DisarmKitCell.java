package Cells;

import javax.swing.ImageIcon;

import main.MineField;
import main.game;

/**
 * UI component representing a disarm kit item cell.
 *
 * This cell extends EmptyCell and grants the player a disarm kit when revealed.
 * When the cell is revealed the player's disarm kit button in the item panel
 * is updated, the cell is marked revealed and its icon is set to the disarm
 * kit sprite. The cell participates in normal empty-cell reveal logic and win
 * condition checks inherited from EmptyCell.
 */
public class DisarmKitCell extends EmptyCell{
    
    /**
     * Icon shown when the disarm kit is revealed.
     */
    ImageIcon disarmIcon = new ImageIcon("sprites/items/defuseKitFull.png");

    /**
     * Create a DisarmKitCell for the given coordinates.
     *
     * @param row the row index of this cell 
     * @param column the column index of this cell 
     * @param mineField reference to the MineField model that holds the full field
     */
    public DisarmKitCell(int row, int column, MineField mineField) {
        super(row, column, mineField);
    }    

    /**
     * Reveal the disarm kit cell at the specified coordinates.
     *
     * If the cell is not already revealed this method will:
     * - update the player's disarm kit button to available,
     * - mark the cell revealed,
     * - win condition check,
     * - set the cell icon to the disarm kit sprite.
     *
     * Multiple calls will not re-award the item once the cell is revealed.
     *
     * @param row the row index to reveal
     * @param col the column index to reveal
     */
    @Override
    public void reveal(int row, int col) {

        System.out.println("clicked");

        if (!isRevealed) {
            game.playerPanel.itemPanel.disarmKitButton.update(true);
            this.isRevealed = true;
            super.reveal(row, col);
            this.setIcon(disarmIcon);
        }
        this.setIcon(disarmIcon);

        super.reveal(row, col);
        
        this.setIcon(disarmIcon);
    }

    /**
     * Show the disarm kit icon and update player state without additional recursion.
     *
     * If the cell is not already revealed this method will:
     * - update the player's disarm kit button to available,
     * - mark the cell revealed,
     * - set the icon to the disarm kit sprite,
     * - run the win condition check.
     *
     */
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
