package Cells;

import main.MineField;
import main.game;
import javax.swing.ImageIcon;

/**
 * A cell that represents a healing potion on the game field.
 *
 * This cell inherits from EmptyCell and provides behavior for a healing potion
 * pickup. When revealed the cell marks the healing potion as available in
 * the player's item panel, updates internal revealed state and icon, and
 * participates in win condition checks.
 */
public class HealPotionCell extends EmptyCell {

    /**
     * Flag indicating whether the potion icon has been explicitly set.
     */
    public boolean iconSet;

    /**
     * File system path to the healing potion icon.
     */
    String pathToIcon = "sprites/healing_potion_icon.png";

    /**
     * ImageIcon instance used to render the healing potion.
     */
    public ImageIcon potionIcon = new ImageIcon(pathToIcon);

    /**
     * Create a HealPotionCell for the given coordinates.
     *
     * The cell is registered in the shared empty cell matrix by the parent
     * constructor and starts in an unrevealed state.
     *
     * @param row the row index of this cell
     * @param column the column index of this cell
     * @param mineField reference to the MineField model that holds the full field
     */
    public HealPotionCell(int row, int column, MineField mineField) {
        super(row, column, mineField);

        this.iconSet = false;
        this.isRevealed = false;
    }

    /**
     * Reveal the potion cell.
     *
     * If the cell is not already revealed this method:
     * - marks the healing potion button in the player's item panel as available,
     * - updates internal state and icon,
     * - invokes win condition checks,
     * - delegates to the parent reveal logic to propagate any flood-fill behavior.
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

    /**
     * Show the potion value/icon when the cell is revealed.
     *
     * This method ensures the player's item panel is updated to reflect the
     * available healing potion, sets the cell as revealed and updates the icon,
     * and runs the win condition check.
     */
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

    /**
     * Set the default icon for this cell.
     *
     * If the icon has been explicitly set via iconSet this method calls the
     * parent showValue to ensure the visual state is consistent; otherwise no
     * change is performed.
     */
    @Override
    public void setDefaultIcon() {
        if (this.iconSet) {
            super.showValue();
        }
    }
}
