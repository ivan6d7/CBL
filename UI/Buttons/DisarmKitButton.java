package UI.Buttons;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import Cells.MineCell;
import main.game;

public class DisarmKitButton extends ItemButton{

    String emptyHealingPotionPath = "sprites/items/defuseKitEmpty.png";
    String filledHealingPotionPath = "sprites/items/defuseKitFull.png";


    public DisarmKitButton() {
        this.isActive = (itemStatus[2] == 1);

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

            List<MineCell> mineList = new ArrayList<MineCell>();

            for (Component c: game.panel.getComponents()) {
                if (c instanceof MineCell) {
                    try {
                        mineList.add(((MineCell)c));
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }

            for (MineCell mine: mineList) {
                if (!mine.isRevealed) {
                    mine.reveal(1, 2);
                    break;
                }
            }

            isActive = false;
            update(false);
        }
    }
}
