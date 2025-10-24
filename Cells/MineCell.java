package Cells;

import java.awt.event.*;
import javax.swing.*;

import SaveSystem.saveWriter;
import UI.Screens.*;
import main.game;

public class MineCell extends Cell {

    String mineIconLocation = "sprites/bomb_icon.png";
    ImageIcon icon = new ImageIcon(mineIconLocation);

    public MineCell(int row, int column) {

        super(row, column);

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reveal(row, column);
                if (game.lifeCount > 1) {
                    game.lifeCount -= 1;
                    game.playerPanel.updateData();
                } else {
                    game.lifeCount = 5;
                    game.levelNumber = 1;
                    saveWriter.saveLife();
                    new GameOverPanel();
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right click!");
                }
            }
        });
    }

    void changeIcon(String newLocation) {
        icon = new ImageIcon(newLocation);
    }

    @Override
    public void reveal(int row, int column) {
        this.isRevealed = true;
        this.setIcon(icon);
        System.out.println("BOOM");
    }

    public void showValue() {
        this.setIcon(icon);
    }

    public void showForPotion() {
        this.setIcon(icon);
    }

    public void hideForPotion() {
        if (!this.isRevealed) {
            this.setIcon(new ImageIcon("sprites/wall_icon.png"));
        }
    }

    @Override
    public void setDefaultIcon() {
    }

}