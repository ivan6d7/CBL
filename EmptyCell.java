import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Empty cells and all its functions
class EmptyCell extends JButton {
    boolean isRevealed = false;
    int row;
    int column;
    MineField mineField;
    int neighborMines;


    int fieldLength = game.fieldLength;
    int fieldHeight = game.fieldHeight;
    EmptyCell[][] cellField = game.cellField;


    String flagIconLocation = "sprites/minesweeper_flag.png";
    ImageIcon flagIcon = new ImageIcon(flagIconLocation);
    
    public EmptyCell (int row, int column, MineField mineField) {
        this.row = row;
        this.column = column;
        this.mineField = mineField;
        this.neighborMines = returnNeighboringMines();

        this.setOpaque(true);            
        this.setSize(50, 50);
        this.setMargin(new Insets(0, 0, 0, 0));


        cellField[row][column] = this;

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

        int[][] field = mineField.field;

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

        int[][] field = mineField.field;
        
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