package UI.Screens;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import UI.Buttons.NextLevelButton;
import UI.Buttons.RetryButton;
import main.game;

/**
 * Panel shown when the player wins the game.
 *
 * The WinPanel replaces the main application frame content with a 
 * victory message and a control to restart or retry.
 */
public class WinPanel extends JPanel{
    
    /**
     * Reference to the main application frame obtained from the global game class.
     */
    JFrame frame = game.frame;

    /**
     * Centered label that displays the victory message.
     */
    JLabel label;

    /**
     * Button presented to the player to restart or retry after winning.
     */
    RetryButton startOverButton;

    /**
     * Create and show the WinPanel.
     *
     * The constructor builds the UI components, clears the frame content,
     * installs this panel into the frame using a BorderLayout, and requests
     * revalidation and repaint so the panel becomes visible immediately.
     */
    public WinPanel() {
        label = new JLabel("YOU WON");

        startOverButton = new RetryButton();

        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label, BorderLayout.NORTH);
        add(startOverButton, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();

        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }  
}
