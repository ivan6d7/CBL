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
 
    EmptyCell[][] cellField = game.cellField;

    String flagIconLocation = "sprites/minesweeper_flag.png";
    ImageIcon flagIcon = new ImageIcon(flagIconLocation);

    abstract void reveal(int row, int column);

    void setFlag() {
        this.setIcon(flagIcon);
    }

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;

        this.setOpaque(true);
        this.setSize(50, 50);
        this.setMargin(new Insets(0, 0, 0, 0));

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reveal(row, column);
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
