import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;



public class game {

    String mineIconLocation = "sprites/images.png";
    String flagIconLocation = "sprites/minesweeper_flag.png";

    int fieldLength = 10;
    int fieldHeight = 10;

    MineField mineField = new MineField(fieldLength, fieldHeight);

    Map<List<Integer>, EmptyCell> emptyCellMap = new HashMap<>();

    int[][] field = mineField.field;

    EmptyCell[][] cellField = new EmptyCell[fieldLength][fieldHeight];


    class MineCell extends JButton{
        int row;
        int column;
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

        void changeIcon(String newLocation){
            icon = new ImageIcon(newLocation);
        }

        void reveal() {
            this.setIcon(icon);
            System.out.println("BOOM");
        }
    }

    // Empty cells and all its functions
    class EmptyCell extends JButton {
        boolean isRevealed = false;
        int row;
        int column;
        int neighborMines;
        ImageIcon flagIcon = new ImageIcon(flagIconLocation);
        
        public EmptyCell (int row, int column) {
            this.row = row;
            this.column = column;
            this.neighborMines = returnNeighboringMines();
            this.setOpaque(true);            
            this.setSize(50, 50);
            this.setMargin(new Insets(0, 0, 0, 0));


            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearNeighborCells(row, column);
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

        int returnNeighboringMines() {
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
            return neighborMines;
        }

        void reveal() {
            this.setText(String.valueOf(neighborMines));
            this.isRevealed = true;
            System.out.println("revealed at " + this.row + "" + this.column);
        }

        void clearNeighborCells(int row, int col) {
            // Safety checks
            if (row < 0 || col < 0 
                || row >= field.length || col >= field[0].length) {
                return;
            }
        
            // If it's not an empty cell, stop
            if (field[row][col] != 0) {
                return;
            }

            EmptyCell cell = cellField[row][col];
        
            // If already revealed, don't process again
            if (cell.isRevealed) {
                return;
            }    
        
            // Reveal this cell
            cell.reveal();
        
            // Explore all neighbors if cell itself is zero
            if (cell.returnNeighboringMines() == 0) {    
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) {
                            continue; // Skip itself
                        }
                        clearNeighborCells(row + i, col + j);
                    }
                }
        
            }   
        }

        void setFlag() {
            this.setIcon(flagIcon);
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

                    cellField[y][x] = emptyCell;

                    panel.add(emptyCell);                    
                }

            }
        }




        frame.add(panel);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
        int[] position = {1, 2};
        System.out.println(emptyCellMap.get(position));
    }

    public static void main(String[] args) {  
        new game().render();
    }
}
