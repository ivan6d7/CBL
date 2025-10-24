package UI.Screens;

import main.game;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PlayerPanel extends JPanel{
    JFrame frame = game.frame;


    JLabel mineCountLabel = new JLabel();
    JLabel levLabel = new JLabel();
    JLabel lifeLabel = new JLabel();

    InfoPanel infoPanel = new InfoPanel();
    public ItemPanel itemPanel = new ItemPanel();

    public PlayerPanel() {

        this.setLayout(new GridLayout(1, 2));

        this.setPreferredSize(new Dimension(100, 60));
        
        this.add(infoPanel);
        this.add(itemPanel);
    }

    public void updateData() {
        infoPanel.updateData();
    }
}
