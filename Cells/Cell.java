package Cells;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import main.game;

/**
 * Abstract base class for all cell UI components in the game field.
 *
 * Inherits class JButton and defines common state and behavior for
 * different cell types (empty, mine, potion, etc.). A Cell knows its row and
 * column in the field, its revealed/flagged state, and provides abstract
 * methods that cell classes must implement to reveal themselves,
 * reset their icon, and display their value.
 *
 * Left click on the button calls abstract method reveal(); right click calls method setFlag(), which is 
 * implemented here and the same for all subclasses of Cell.
 */
public abstract class Cell extends JButton {

    /** Row index of this cell in the game field. */
    int row;

    /** Column index of this cell in the game field. */
    int column;

    /** Whether an icon has been explicitly set for this cell. */
    public boolean iconSet;

    /** Whether this cell has been revealed to the player. */
    public boolean isRevealed = false;

    /** Whether this cell is currently flagged by the player. */
    boolean isFlagged = false;
 
    /** Reference to the shared EmptyCell matrix from the main game. */
    EmptyCell[][] cellField = game.cellField;

    /** File-system path for the default (covered) icon. */
    String defaultIconLocation = "sprites/wall_icon.png";

    /** Default icon shown for a covered cell. */
    ImageIcon defaultIcon = new ImageIcon(defaultIconLocation);

    /** File-system path for the flag icon. */
    String flagIconLocation = "sprites/minesweeper_flag.png";

    /** Icon used to indicate a flagged cell. */
    ImageIcon flagIcon = new ImageIcon(flagIconLocation);

    /**
     * Reveal this cell. Each subclass should implement this method.
     * @param row the row index of the cell being revealed
     * @param column the column index of the cell being revealed
     */
    abstract void reveal(int row, int column);

    /**
     * Reset or set the default (covered) icon for this cell.
     */
    abstract void setDefaultIcon();

    /**
     * Display the cell's value (for example, adjacent mine count or mine icon) when revealed.
     *
     * Method is different from reveal(), which is why it is iplemented in subclasses differently.
     */
    abstract void showValue();

    /**
     * Toggle a flag on this cell. When flagged, the flag icon is shown and the
     * global flag counter (in the game state) is incremented; when unflagged the default icon is
     * restored and the counter is decremented. The player's UI panel is updated.
     */
    void setFlag() {
        if (!isFlagged) {
            this.setIcon(flagIcon);
            isFlagged = true;
            game.flagsSet += 1;
            game.playerPanel.updateData();
            
        } else {
            this.setIcon(defaultIcon);
            isFlagged = false;
            this.isRevealed = false;
            game.flagsSet -= 1;
            game.playerPanel.updateData();
        }

    }

    /**
     * Construct a new Cell instance for the specified coordinates.
     *
     * The constructor defines the JButton appearance and listeners:
     * left-click triggers reveal(row, column) and marks the cell revealed;
     * right-click toggles a flag.
     *
     * @param row the row index of this cell in the field
     * @param column the column index of this cell in the field
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;

        this.setOpaque(true);
        this.setIcon(defaultIcon);
        this.setSize(50, 50);
        this.setMargin(new Insets(-5, -5, -5, -5));

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reveal(row, column);
                isRevealed = true;

            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right click!");
                    setFlag();
                }
            }
        });        
    }
    
}
