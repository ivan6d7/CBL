package Cells;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import main.game;


public abstract class Cell extends JButton {

    int row;
    int column;

    public boolean iconSet;

    public boolean isRevealed = false;

    boolean isFlagged = false;
 
    EmptyCell[][] cellField = game.cellField;

    String defaultIconLocation = "sprites/wall_icon.png";
    ImageIcon defaultIcon = new ImageIcon(defaultIconLocation);

    String flagIconLocation = "sprites/minesweeper_flag.png";
    ImageIcon flagIcon = new ImageIcon(flagIconLocation);

    abstract void reveal(int row, int column);
    abstract void setDefaultIcon();
    abstract void showValue();

    void setFlag() {
        if (!isFlagged) {
            this.setIcon(flagIcon);
            isFlagged = true;
            game.flagsSet += 1;
            game.playerPanel.updateData();
            
        } else {
            this.setIcon(defaultIcon);
            isFlagged = false;
            this.isRevealed = false;
            game.flagsSet -= 1;
            game.playerPanel.updateData();
        }

    }

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;

        this.setOpaque(true);
        this.setIcon(defaultIcon);
        this.setSize(50, 50);
        this.setMargin(new Insets(-5, -5, -5, -5));

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reveal(row, column);
                isRevealed = true;

            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right click!");
                    setFlag();
                }
            }
        });        
    }
    
}
