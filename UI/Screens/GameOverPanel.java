package UI.Screens;

import UI.Buttons.*;
import java.awt.BorderLayout;
import javax.swing.*;
import main.game;

/**
 * Panel displayed when the player has lost the game.
 *
 * This panel replaces the main application frame content with a simple
 * "GAME OVER" message and a retry control. It obtains the application's
 * JFrame from the global game() class, constructs a centered label
 * and a RetrtyButton, and installs itself into the frame.
 */
public class GameOverPanel extends JPanel{

    /**
     * Reference to the main application frame obtained from game().
     */
    JFrame frame = game.frame;

    /**
     * Label displaying the "GAME OVER" message.
     */
    JLabel label;

    /**
     * Button that restarts the current level or game when pressed.
     */
    RetryButton retryButton;

    /**
     * Create and show the Game Over panel.
     */
    public GameOverPanel() {
        label = new JLabel("GAME OVER");

        retryButton = new RetryButton();

        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label, BorderLayout.NORTH);
        add(retryButton, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();

        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }  
}