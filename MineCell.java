import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MineCell extends JButton {
    int row;
    int column;
    String mineIconLocation = "sprites/images.png";
    ImageIcon icon = new ImageIcon(mineIconLocation);

    public MineCell(int row, int colulmn) {
        this.setOpaque(true);
        this.setSize(50, 50);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.row = row;
        this.column = colulmn;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reveal();
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

    void reveal() {
        this.setIcon(icon);
        System.out.println("BOOM");
    }
}