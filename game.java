import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class game {

    String mineIconLocation = "sprites/images.png";

    int fieldLength = 10;
    int fieldHeight = 10;

    MineField mineField = new MineField(fieldLength, fieldHeight);


    int[][] field = mineField.field;

    class MineCell extends JButton{
        int row;
        int column;
        ImageIcon icon = new ImageIcon(mineIconLocation);

        public MineCell(int row, int colulmn) {

            this.setOpaque(true);
            this.setIcon(icon);
            this.setSize(50, 50);
            this.setMargin(new Insets(0, 0, 0, 0));
            this.row = row;
            this.column = colulmn;

            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reveal();
                }
            });
        }

        void changeIcon(String newLocation){
            icon = new ImageIcon(newLocation);
        }

        void reveal() {
            System.out.println("BOOM");
        }
    }


    class EmptyCell extends JButton {
        int row;
        int column;
        
        public EmptyCell (int row, int column) {
            this.row = row;
            this.column = column;
            this.setSize(50, 50);
            this.setMargin(new Insets(0, 0, 0, 0));

            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    returnNeighboringMines();
                }
            });
        }

        void returnNeighboringMines() {
            int neighborMines = 0;
            int lowerX = column - 1;
            int upperX = column + 2;
            int lowerY = row - 1;
            int upperY = row + 2;

            if (column == 9) {
                upperX = column;
            }
            if (column == 0) {
                lowerX = column;
            }
            if (row == 9) {
                upperY = row;
            }
            if (row == 0) {
                lowerY = row;
            }


            for (int x = lowerX; x < upperX; x++) {
                for (int y = lowerY; y < upperY; y++) {
                    if (field[y][x] == 1) {
                        neighborMines++;
                    }
                }
            }
            System.out.println(neighborMines);
        }

        
    }


    void render() {

        JFrame frame = new JFrame();
        // 400 width and 500 height
        frame.setSize(mineField.length * 50, mineField.height * 50);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(fieldLength, fieldHeight));



        for (int y = 0; y < mineField.height; y++) {
            for (int x = 0; x < mineField.length; x++) {
                if (field[y][x] == 1) {
                    MineCell mineCell = new MineCell(y, x);

                    panel.add(mineCell);
                } else {
                    EmptyCell emptyCell = new EmptyCell(y, x);

                    panel.add(emptyCell);                    
                }

            }
        }




        frame.add(panel);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {  
        new game().render();
    }
}
