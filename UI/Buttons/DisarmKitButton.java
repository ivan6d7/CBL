package UI.Buttons;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import Cells.MineCell;
import main.game;

/**
 * Button representing the disarm kit item.
 *
 * When active, this button allows the player to disarm a mine (prevent life
 * loss or remove the mine) according to game rules. The button initializes
 * its visual state from saved data, provides an update method to change and
 * save availability, and implements apply to consume the kit and disarm
 * a single unrevealed mine on the board.
 */
public class DisarmKitButton extends ItemButton{

    /**
     * Icon path used when the disarm kit is not available.
     */
    String emptyHealingPotionPath = "sprites/items/defuseKitEmpty.png";

    /**
     * Icon path used when the disarm kit is available.
     */
    String filledHealingPotionPath = "sprites/items/defuseKitFull.png";


    /**
     * Construct a DisarmKitButton.
     *
     * The constructor reads the saved item status (via the inherited
     * itemStatus array) and sets the initial isActive flag and icon to match
     * saveed availability.
     */
    public DisarmKitButton() {
        this.isActive = (itemStatus[2] == 1);

        if (isActive) {
            this.setIcon(new ImageIcon(filledHealingPotionPath));
        } else {
            this.setIcon(new ImageIcon(emptyHealingPotionPath));
        }
    }

    /**
     * Update the button's active state and icon, then save the change.
     *
     * @param gotPotion true to mark the kit available, false to mark it unavailable
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
     * Apply the disarm kit effect if the button is active.
     *
     * When applied this method:
     * - collects all MineCell instances currently present in the game panel,
     * - finds the first mine that is not yet revealed and calls reveal on it,
     * - deactivates the button and saves the new item state so the kit is
     *   not available until re-awarded.
     *
     * If the button is not active this method has no effect.
     */
    public void apply() {
        if (isActive) {

            List<MineCell> mineList = new ArrayList<MineCell>();

            for (Component c: game.panel.getComponents()) {
                if (c instanceof MineCell) {
                    try {
                        mineList.add(((MineCell)c));
                    } catch (Exception e) {
                        // ignore individual component errors
                    }
                }
            }

            for (MineCell mine: mineList) {
                if (!mine.isRevealed) {
                    mine.reveal(1, 2);
                    break;
                }
            }

            isActive = false;
            update(false);
        }
    }
}
