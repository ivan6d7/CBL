package UI.Screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import SaveSystem.saveWriter;
import main.game;

public class InfoPanel extends JPanel{

    JLabel mineCountLabel = new JLabel();
    JLabel levLabel = new JLabel();
    JLabel lifeLabel = new JLabel();

    public InfoPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;

        mineCountLabel.setText(game.flagsSet + " / " + game.mineCount);
        levLabel.setText("Current level: " + game.levelNumber);
        lifeLabel.setText(game.lifeCount + " lives left");

        gbc.gridx = 0;
        gbc.weightx = 1;
        this.add(makePanel(mineCountLabel), gbc);

        gbc.gridx = 1;
        gbc.weightx = 2;
        this.add(makePanel(levLabel), gbc);

        gbc.gridx = 2;
        gbc.weightx = 1;
        this.add(makePanel(lifeLabel), gbc);
    }

    private JPanel makePanel(JLabel label) {
        JPanel panel = new JPanel();
        panel.add(label);
        return panel;
    }

    public void updateData() {
        mineCountLabel.setText(game.flagsSet + " / " + game.mineCount);
        lifeLabel.setText(game.lifeCount + " lives left");
        saveWriter.saveLife();
    }
}
