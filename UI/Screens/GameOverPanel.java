package UI.Screens;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import main.game;
import UI.Buttons.*;

public class GameOverPanel extends JPanel{

    JFrame frame = game.frame;

    JLabel label;
    RetryButton retryButton;

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