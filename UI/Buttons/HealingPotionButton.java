package UI.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import SaveSystem.saveWriter;
import UI.Screens.PlayerPanel;
import main.game;

/**
 * Button representing the healing potion item.
 *
 * When active this button allows the player to consume a healing potion and
 * restore one life. The button updates its icon to show availability,
 * updates item state when changed, and notifies the PlayerPanel of life
 * changes.
 */
public class HealingPotionButton extends ItemButton{

    /**
     * Path to the icon used when the healing potion is not available.
     */
    String emptyHealingPotionPath = "sprites/items/healingPotionEmpty.png";

    /**
     * Path to the icon used when the healing potion is available.
     */
    String filledHealingPotionPath = "sprites/items/healingPotionFull.png";

    /**
     * Create a HealingPotionButton.
     *
     * Reads saved item status (via the inherited itemStatus array) and sets
     * the initial isActive flag and icon accordingly.
     */
    public HealingPotionButton() {
        this.isActive = (itemStatus[0] == 1);

        if (isActive) {
            this.setIcon(new ImageIcon(filledHealingPotionPath));
        } else {
            this.setIcon(new ImageIcon(emptyHealingPotionPath));
        }
    }

    /**
     * Update the visual state and active flag for this button and save the
     * new state to the save file.
     *
     * @param gotPotion true to mark the potion available, false to mark it used/unavailable
     */
    @Override
    public void update(boolean gotPotion) {
        if (gotPotion) {
            this.setIcon(new ImageIcon(filledHealingPotionPath));
            isActive = true;
        } else {
            this.setIcon(new ImageIcon(emptyHealingPotionPath));
            isActive = false;
        }
        super.update(gotPotion);
    }

    /**
     * Apply the healing potion effect if the button is active.
     *
     * If active, increases the global lifeCount by one, updates the player
     * panel display, deactivates the button and saves the change.
     */
    public void apply() {
        if (isActive) {
            game.lifeCount += 1;
            game.playerPanel.updateData();
            isActive = false;
            update(false);
        }
    }

}
