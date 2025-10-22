package UI.Screens;

import SaveSystem.saveWriter;
import main.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel{
    JFrame frame = game.frame;
    private int mineCount = game.mineCount;


    JLabel mineCountLabel = new JLabel();
    JLabel levLabel = new JLabel();
    JLabel lifeLabel = new JLabel();

    public PlayerPanel() {
        this.setPreferredSize(new Dimension(100, 50));
        mineCountLabel.setText(game.flagsSet + " / " + mineCount);
        levLabel.setText("Current level: " + game.levelNumber);
        lifeLabel.setText(game.lifeCount + " lives left");


        this.add(mineCountLabel, BorderLayout.WEST);
        this.add(levLabel, BorderLayout.WEST);
        this.add(lifeLabel, BorderLayout.CENTER);
    }

    public void updateData() {
        mineCountLabel.setText(game.flagsSet + " / " + mineCount);
        lifeLabel.setText(game.lifeCount + " lives left");
        saveWriter.save();
    }
}
