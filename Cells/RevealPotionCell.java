package Cells;

import javax.swing.ImageIcon;

import main.MineField;
import main.game;

/**
 * Cell that grants a temporary reveal potion when uncovered.
 *
 * The cell behaves like an EmptyCell but, when revealed, it marks the
 * player's reveal potion item as available in the item panel and updates
 * its icon to the reveal potion sprite. The cell participates in standard
 * empty-cell reveal logic including flood-fill and win checks.
 */
public class RevealPotionCell extends EmptyCell{

    /**
     * Icon used to represent the reveal potion when exposed.
     */
    ImageIcon revealIcon = new ImageIcon("sprites/items/revealPotionFull.png");

    /**
     * Create a RevealPotionCell at the given coordinates.
     *
     * @param row the row index of this cell (0-based)
     * @param column the column index of this cell (0-based)
     * @param mineField reference to the MineField model that holds the full field
     */
    public RevealPotionCell(int row, int column, MineField mineField) {
        super(row, column, mineField);
    }

    /**
     * Reveal the potion cell at the specified coordinates.
     *
     * If the cell is not already revealed this method:
     * - updates the player's reveal potion button to available,
     * - marks the cell revealed,
     * - sets the local icon flag,
     * - performs win condition checks,
     * - sets the potion icon.
     *
     * Additional calls do not re-award the item. The method always ensures
     * the visual icon is set to the potion sprite after reveal processing.
     *
     * @param row the row index to reveal
     * @param col the column index to reveal
     */
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
