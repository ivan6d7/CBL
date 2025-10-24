package UI.Screens;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import UI.Buttons.NextLevelButton;
import UI.Buttons.RetryButton;
import main.game;

public class WinPanel extends JPanel{
    
    JFrame frame = game.frame;

    JLabel label;
    RetryButton startOverButton;

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
