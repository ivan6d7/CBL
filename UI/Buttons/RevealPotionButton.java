package UI.Buttons;

import java.awt.Component;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

import Cells.*;
import main.game;

/**
 * Button that represents the reveal potion item.
 *
 * The reveal potion temporarily exposes mines on the board without permanently
 * revealing them. When the button is active and applied, MineCell.showForPotion
 * is called for each mine cell to display the mine icon, and after a short
 * delay the icons are restored by calling MineCell.hideForPotion.
 *
 * The button saves its availability state via the inherited update method.
 */
public class RevealPotionButton extends ItemButton{

    /**
     * Path to the icon shown when the reveal potion is not available.
     */
    String emptyRevealPotionPath = "sprites/items/revealPotionEmpty.png";

    /**
     * Path to the icon shown when the reveal potion is available.
     */
    String filledRevealPotionPath = "sprites/items/revealPotionFull.png";

    /**
     * Construct a RevealPotionButton and initialize visual state from saved data.
     *
     * The constructor reads the saved itemStatus array (inherited) and sets the
     * initial isActive flag and icon accordingly.
     */
    public RevealPotionButton() {
        this.isActive = (itemStatus[1] == 1);

        if (isActive) {
            this.setIcon(new ImageIcon(filledRevealPotionPath));
        } else {
            this.setIcon(new ImageIcon(emptyRevealPotionPath));
        }
    }

    /**
     * Update this button's active state and icon, then save the change.
     *
     * @param gotPotion true to mark the potion available, false to mark it used/unavailable
     */
    @Override
    public void update(boolean gotPotion) {
        if (gotPotion) {
            this.setIcon(new ImageIcon(filledRevealPotionPath));
            isActive = true;
        } else {
            this.setIcon(new ImageIcon(emptyRevealPotionPath));
            isActive = false;
        }
        super.update(gotPotion);
    }

    /**
     * Apply the reveal potion effect if this button is active.
     *
     * When applied:
     * - All MineCell instances in the current game panel will temporarily show
     *   their mine icon by calling showForPotion.
     * - A single-shot Swing Timer restores the covered icons after approximately
     *   one second by calling hideForPotion on each MineCell.
     * - The button is deactivated and the change is saved.
     *
     * If the button is not active this method does nothing.
     */
    public void apply() {
        if (isActive) {

            for (Component c: game.panel.getComponents()) {
                if (c instanceof MineCell) {
                    try {
                        ((MineCell)c).showForPotion();
                    } catch (Exception e) {
                        // ignore individual component errors
                    }
                }
            }

            javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {

                for (Component c: game.panel.getComponents()) {
                    if (c instanceof MineCell) {
                        try {
                            ((MineCell)c).hideForPotion();
                        } catch (Exception r) {
                            // ignore individual component errors
                        }
                    }
                }

                System.out.println("Triggered!");
            });
            timer.setRepeats(false);
            timer.start();

            update(false);
        }
    }
}
