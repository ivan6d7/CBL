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

public class ItemPanel extends JPanel{

    public HealingPotionButton healingPotionButton;
    public RevealPotionButton revealPotionButton;
    public DisarmKitButton disarmKitButton;

    public ItemPanel() {
        this.setLayout( new GridLayout(1, 3));

        healingPotionButton = new HealingPotionButton();
        this.add(makePanel(healingPotionButton));

        revealPotionButton = new RevealPotionButton();
        this.add(makePanel(revealPotionButton));

        disarmKitButton = new DisarmKitButton();
        this.add(makePanel(disarmKitButton));
    }

    private JPanel makePanel(JButton button) {
        JPanel panel = new JPanel();

        button.setPreferredSize(new Dimension(50, 50));

        panel.add(button);
        return panel;
    }
}
