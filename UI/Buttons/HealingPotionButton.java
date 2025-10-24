package UI.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import SaveSystem.saveWriter;
import UI.Screens.PlayerPanel;
import main.game;

public class HealingPotionButton extends ItemButton{

    String emptyHealingPotionPath = "sprites/items/healingPotionEmpty.png";
    String filledHealingPotionPath = "sprites/items/healingPotionFull.png";


    public HealingPotionButton() {
        this.isActive = (itemStatus[0] == 1);

        if (isActive) {
            this.setIcon(new ImageIcon(filledHealingPotionPath));
        } else {
            this.setIcon(new ImageIcon(emptyHealingPotionPath));
        }
    }

    @Override
    public void update(boolean gotPotion) {
        if (gotPotion) {
            this.setIcon(new ImageIcon(filledHealingPotionPath));
            isActive = true;
        } else {
            this.setIcon(new ImageIcon(emptyHealingPotionPath));
            isActive = false;
        }
        super.update(gotPotion);
    }

    public void apply() {
        if (isActive) {
            game.lifeCount += 1;
            game.playerPanel.updateData();
            isActive = false;
            update(false);
        }
    }

}
