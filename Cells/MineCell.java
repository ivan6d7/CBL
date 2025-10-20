package Cells;

import java.awt.event.*;
import javax.swing.*;

public class MineCell extends Cell {

    String mineIconLocation = "sprites/images.png";
    ImageIcon icon = new ImageIcon(mineIconLocation);

    public MineCell(int row, int column) {
        super(row, column);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reveal(row, column);
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
    void reveal(int row, int column) {
        this.setIcon(icon);
        System.out.println("BOOM");
    }
}