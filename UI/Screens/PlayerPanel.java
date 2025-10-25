package UI.Screens;

import main.game;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Composite panel that displays player status and item controls.
 *
 * The PlayerPanel composes an InfoPanel (shows flags, level, lives) and an
 * ItemPanel (healing, reveal and disarm item buttons) into a single horizontal
 * row.
 */
public class PlayerPanel extends JPanel{
    /**
     * Reference to the main application frame obtained from the global game class.
     */
    JFrame frame = game.frame;

    /**
     * JLabel used by the InfoPanel for flag/mine counter display.
     */
    JLabel mineCountLabel = new JLabel();

    /**
     * JLabel used by the InfoPanel for current level display.
     */
    JLabel levLabel = new JLabel();

    /**
     * JLabel used by the InfoPanel for remaining lives display.
     */
    JLabel lifeLabel = new JLabel();

    /**
     * Subpanel that shows informational labels (flags, level, lives).
     */
    InfoPanel infoPanel = new InfoPanel();

    /**
     * Subpanel that hosts item buttons (healing, reveal, disarm).
     */
    public ItemPanel itemPanel = new ItemPanel();

    /**
     * Create a PlayerPanel instance.
     *
     * The constructor configures a two-column GridLayout, sets a preferred size,
     * and adds the InfoPanel and ItemPanel children in left-to-right order.
     */
    public PlayerPanel() {

        this.setLayout(new GridLayout(1, 2));

        this.setPreferredSize(new Dimension(100, 60));
        
        this.add(infoPanel);
        this.add(itemPanel);
    }

    /**
     * Refresh displayed player data.
     *
     * This method updates the info subpanel to reflect current global game
     * state (flags placed, mine count and remaining lives) and delegates
     * persistence of life count to the InfoPanel implementation.
     */
    public void updateData() {
        infoPanel.updateData();
    }
}
