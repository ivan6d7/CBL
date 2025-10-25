package UI.Screens;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import UI.Buttons.DisarmKitButton;
import UI.Buttons.HealingPotionButton;
import UI.Buttons.RevealPotionButton;

/**
 * Panel that hosts the three item buttons used by the player.
 *
 * The panel lays out the healing potion, reveal potion and disarm kit buttons
 * in a single row.
 */
public class ItemPanel extends JPanel{

    /**
     * Button that consumes the healing potion item.
     */
    public HealingPotionButton healingPotionButton;

    /**
     * Button that consumes the reveal potion item.
     */
    public RevealPotionButton revealPotionButton;

    /**
     * Button that consumes the disarm kit item.
     */
    public DisarmKitButton disarmKitButton;

    /**
     * Create an ItemPanel and initialize its three item buttons.
     *
     * The panel uses a single-row GridLayout. Each button is wrapped in a
     * small container so the button size and spacing are controlled.
     */
    public ItemPanel() {
        this.setLayout(new GridLayout(1, 3));

        healingPotionButton = new HealingPotionButton();
        this.add(makePanel(healingPotionButton));

        revealPotionButton = new RevealPotionButton();
        this.add(makePanel(revealPotionButton));

        disarmKitButton = new DisarmKitButton();
        this.add(makePanel(disarmKitButton));
    }

    /**
     * Put a JButton in a JPanel and set the button preferred size.
     *
     * @param button the JButton to wrap
     * @return a JPanel containing the provided button with preferred sizing applied
     */
    private JPanel makePanel(JButton button) {
        JPanel panel = new JPanel();

        button.setPreferredSize(new Dimension(50, 50));

        panel.add(button);
        return panel;
    }
}
