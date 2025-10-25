package UI.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import SaveSystem.saveReader;
import SaveSystem.saveWriter;

/**
 * Base button used by in-game item controls.
 */
public abstract class ItemButton extends JButton{
    
    /**
     * Do the action associated with this item button.
     *
     * Subclasses should implement the logic that applies the item effect and
     * update the UI / game state.
     */
    public abstract void apply();

    Integer[] itemStatus;

    /**
     * In-memory active flag for this button.
     *
     * True when the item is currently available for use, false otherwise.
     */
    public boolean isActive;

    /**
     * Create a new ItemButton instance.
     *
     * The constructor reads saved item information using saveReader, sets the
     * in-memory active flag to false, and installs an action listener that
     * invokes apply() when the button is clicked.
     */
    public ItemButton() {
        itemStatus = saveReader.readItems();
        this.isActive = false;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apply();
            }
        });    
    }

    /**
     * Writes current item state to sve file.
     *
     * @param x unused boolean parameter kept for compatibility with callers
     */
    public void update(boolean x) {
        saveWriter.saveItems();
    }

    /**
     * Return the isActive state as an integer for save formatting.
     *
     * @return 1 if the button is active, 0 otherwise
     */
    public Integer isActive() {
        if (this.isActive) {
            return 1;
        } else {
            return 0;
        }
    }

}
