package UI.Screens;

import UI.Buttons.*;
import java.awt.BorderLayout;
import javax.swing.*;
import main.game;


public class WinScreenPanel extends JPanel{

    JFrame frame = game.frame;

    JLabel label;
    RetryButton retryButton;

    public WinScreenPanel() {
        label = new JLabel("YOU WON");

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