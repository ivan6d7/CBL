package Cells;

import java.awt.event.*;
import javax.swing.*;

import SaveSystem.saveWriter;
import UI.Screens.*;
import main.game;

/**
 * UI component representing a mine cell on the game field.
 *
 * A MineCell displays a mine icon when revealed and reduces the player's life
 * count when clicked. If the player's remaining lives drop below 1 the game
 * ends and the Game Over screen is shown.
 *
 * The class extends Cell and reuses reveal and icon helper methods to match
 * the functional of other cell types (temporary reveal for potions, permanent reveal).
 */
public class MineCell extends Cell {

    /**
     * File system path to the mine icon.
     */
    String mineIconLocation = "sprites/bomb_icon.png";

    /**
     * Icon used to render the mine when revealed.
     */
    ImageIcon icon = new ImageIcon(mineIconLocation);

    /**
     * Create a MineCell with the specified coordinates.
     *
     * The constructor installs an event listener that reveals the mine and
     * updates the player's life count. If lives reach zero the game over
     * UI is shown and saving logic is invoked.
     *
     * @param row the row index of this cell 
     * @param column the column index of this cell 
     */
    public MineCell(int row, int column) {

        super(row, column);

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reveal(row, column);
                if (game.lifeCount > 1) {
                    game.lifeCount -= 1;
                    game.playerPanel.updateData();
                } else {
                    game.lifeCount = 5;
                    game.levelNumber = 1;
                    saveWriter.saveLife();
                    new GameOverPanel();
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right click!");
                }
            }
        });
    }

    /**
     * Change the mine icon to a different image resource.
     *
     * This updates the local ImageIcon instance used when the mine is shown.
     *
     * @param newLocation file system path or resource path to the new icon
     */
    void changeIcon(String newLocation) {
        icon = new ImageIcon(newLocation);
    }

    /**
     * Reveal this mine cell.
     *
     * The method sets the revealed flag for this cell and updates the button
     * icon to the mine sprite. It also logs a simple debug message.
     *
     * @param row the row index being revealed (unused by implementation)
     * @param column the column index being revealed (unused by implementation)
     */
    @Override
    public void reveal(int row, int column) {
        this.isRevealed = true;
        this.setIcon(icon);
        System.out.println("BOOM");
    }

    /**
     * Show the mine value/icon for permanent reveal scenarios.
     *
     * This method sets the button icon to the mine sprite.
     */
    public void showValue() {
        this.setIcon(icon);
    }

    /**
     * Temporarily show the mine icon for potion effects.
     *
     * This method displays the mine icon without necessarily changing the
     * permanent revealed flag.
     */
    public void showForPotion() {
        this.setIcon(icon);
    }

    /**
     * Hide the mine icon after a temporary reveal if the mine has not been
     * permanently revealed.
     *
     * If the cell is not permanently revealed the covered (wall) icon is restored.
     */
    public void hideForPotion() {
        if (!this.isRevealed) {
            this.setIcon(new ImageIcon("sprites/wall_icon.png"));
        }
    }

    /**
     * Set the default (covered) icon for this cell.
     *
     * For MineCell the default implementation is intentionally left empty
     * because the covered icon is handled elsewhere or not required to change.
     */
    @Override
    public void setDefaultIcon() {
    }

}