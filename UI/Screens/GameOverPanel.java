package UI.Screens;

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

        add(label);
        add(retryButton);

        frame.getContentPane().removeAll();
        frame.add(this);
        frame.revalidate();
        frame.repaint();
    }  
}