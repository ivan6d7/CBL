package UI.Screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import SaveSystem.saveWriter;
import main.game;

/**
 * Panel that displays brief game information such as flag count, current level
 * and remaining lives.
 *
 * The panel lays out three small sub-panels horizontally: a flag/mine counter,
 * the current level label and the life counter. The labels are backed by
 * references so calling updateData() refreshes their text from the
 * current global game state and persists the life count.
 */
public class InfoPanel extends JPanel{

    /**
     * Label showing number of flags placed vs total mines.
     */
    JLabel mineCountLabel = new JLabel();

    /**
     * Label showing the current level number.
     */
    JLabel levLabel = new JLabel();

    /**
     * Label showing remaining lives.
     */
    JLabel lifeLabel = new JLabel();

    /**
     * Create an InfoPanel and initialize labels from the global game state.
     *
     * The constructor configures a GridBagLayout with three arranged
     * slots and populates each slot with a small panel that contains the
     * corresponding label.
     */
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

    /**
     * Create a simple container panel that holds a single label.
     *
     * @param label the JLabel to be wrapped in a JPanel
     * @return a JPanel containing the provided label
     */
    private JPanel makePanel(JLabel label) {
        JPanel panel = new JPanel();
        panel.add(label);
        return panel;
    }

    /**
     * Refresh the displayed values from the global game state and persist life.
     *
     * This method updates the three labels to reflect the current values of
     * flagsSet, mineCount and lifeCount. It also writes the current life count
     * to disk via saveWriter.saveLife() so the value is saved.
     */
    public void updateData() {
        mineCountLabel.setText(game.flagsSet + " / " + game.mineCount);
        lifeLabel.setText(game.lifeCount + " lives left");
        saveWriter.saveLife();
    }
}
