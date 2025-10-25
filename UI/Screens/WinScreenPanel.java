package UI.Screens;

import UI.Buttons.*;
import java.awt.BorderLayout;
import javax.swing.*;
import main.game;

/**
 * Panel shown when the player wins the game and should proceed to the next level.
 *
 * The WinScreenPanel replaces the main application frame content with a
 * victory message and a control to advance to the next level.
 */
public class WinScreenPanel extends JPanel{

    /**
     * Reference to the main application frame obtained from the global game class.
     */
    JFrame frame = game.frame;

    /**
     * Centered label that displays the victory message.
     */
    JLabel label;

    /**
     * Button that advances the game to the next level when pressed.
     */
    NextLevelButton nextLevelButton;

    /**
     * Create and show the WinScreenPanel.
     */
    public WinScreenPanel() {
        label = new JLabel("YOU WON");

        nextLevelButton = new NextLevelButton();

        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label, BorderLayout.NORTH);
        add(nextLevelButton, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();

        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }  
}