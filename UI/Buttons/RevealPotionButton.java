package UI.Buttons;

import java.awt.Component;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

import Cells.*;
import main.game;

public class RevealPotionButton extends ItemButton{

    String emptyRevealPotionPath = "sprites/items/revealPotionEmpty.png";
    String filledRevealPotionPath = "sprites/items/revealPotionFull.png";



    public RevealPotionButton() {
        this.isActive = (itemStatus[1] == 1);

        if (isActive) {
            this.setIcon(new ImageIcon(filledRevealPotionPath));
        } else {
            this.setIcon(new ImageIcon(emptyRevealPotionPath));
        }
    }

    @Override
    public void update(boolean gotPotion) {
        if (gotPotion) {
            this.setIcon(new ImageIcon(filledRevealPotionPath));
            isActive = true;
        } else {
            this.setIcon(new ImageIcon(emptyRevealPotionPath));
            isActive = false;
        }
        super.update(gotPotion);
    }

    public void apply() {
        if (isActive) {

            for (Component c: game.panel.getComponents()) {
                if (c instanceof MineCell) {
                    try {
                        ((MineCell)c).showForPotion();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }

            javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {

                for (Component c: game.panel.getComponents()) {
                    if (c instanceof MineCell) {
                        try {
                            ((MineCell)c).hideForPotion();
                        } catch (Exception r) {
                            // TODO: handle exception
                        }
                    }
                }   

                System.out.println("Triggered!");
            });
            timer.setRepeats(false);
            timer.start(); // <-- You must start it manually         


            update(false);
        }
    }
}
