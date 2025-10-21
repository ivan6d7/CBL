package UI.Screens;

import UI.Buttons.*;
import java.awt.BorderLayout;
import javax.swing.*;
import main.game;


public class WinScreenPanel extends JPanel{

    JFrame frame = game.frame;

    JLabel label;
    NextLevelButton nextLevelButton;

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