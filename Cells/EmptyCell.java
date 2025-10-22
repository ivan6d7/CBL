package Cells;

import main.MineField;
import main.game;
import UI.Screens.WinScreenPanel;

// Empty cells and all its functions
public class EmptyCell extends Cell {

    boolean isRevealed = false;
    MineField mineField;
    int neighborMines;
    
    public EmptyCell (int row, int column, MineField mineField) {
        
        super(row, column);

        this.mineField = mineField;
        this.neighborMines = returnNeighboringMines();


        cellField[row][column] = this;

        
    }

    int returnNeighboringMines() {

        int[][] field = game.field;

        int neighborMines = 0;
        int lowerX = column - 1;
        int upperX = column + 2;
        int lowerY = row - 1;
        int upperY = row + 2;
        if (column == game.fieldLength - 1) {
            upperX = column;
        }
        if (column == 0) {
            lowerX = column;
        }
        if (row == game.fieldHeight - 1) {
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
    void showValue() {
        this.setIcon(null);
        this.setText(String.valueOf(neighborMines));
        this.isRevealed = true;
        System.out.println("revealed at " + this.row + "" + this.column);
    }

    @Override
    public void reveal(int row, int col) {

        int[][] field = mineField.field;
        
        // Safety checks
        if (row < 0 || col < 0 
            || row >= field.length || col >= field[0].length) {
            return;
        }
    
        // If it's not an empty cell, stop
        if (field[row][col] == 1) {
            return;
        }
        EmptyCell cell = cellField[row][col];
    
        // If already revealed, don't process again
        if (cell.isRevealed) {
            return;
        }    
    
        // Reveal this cell
        cell.showValue();

        if (game.cellsRevealed < game.target - 1) {
            game.cellsRevealed += 1;
        } else {
            new WinScreenPanel();
        }
    
        // Explore all neighbors if cell itself is zero
        if (cell.neighborMines == 0) {    
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue; // Skip itself
                    }
                    reveal(row + i, col + j);
                }
            }
    
        }   
    }

}