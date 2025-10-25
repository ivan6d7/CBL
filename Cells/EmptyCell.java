package Cells;

import UI.Screens.WinPanel;
import UI.Screens.WinScreenPanel;
import javax.swing.ImageIcon;
import main.MineField;
import main.game;

/**
 * UI component representing an empty cell in the game field.
 *
 * EmptyCell inherits class Cell and implements behavior for revealing an
 * empty/tile cell, computing and storing the count of neighboring mines, and
 * recursively revealing adjacent cells when the cell has zero
 * neighboring mines. This class also includes helpers used by potion effects
 * and handles win condition checking when cells are revealed.
 */
public class EmptyCell extends Cell {

    /** Local revealed flag. */
    boolean isRevealed = false;

    /** Reference to the model containing field layout and game logic. */
    MineField mineField;

    /** Number of mines adjacent to this cell. */
    int neighborMines;
    
    /**
     * Create an EmptyCell for the given coordinates and add it to the array on an index of cell's row and column.
     *
     * @param row       the row index of this cell
     * @param column    the column index of this cell
     * @param mineField reference to the MineField model that holds the full field
     */
    public EmptyCell (int row, int column, MineField mineField) {
        
        super(row, column);

        this.mineField = mineField;
        this.neighborMines = returnNeighboringMines();

        /* register this cell in the shared empty-cell matrix so other cells
           and game logic can reference it by coordinates */
        cellField[row][column] = this;

    }

    /**
     * Calculate and return the number of mines adjacent to this cell.
     *
     * The method inspects the 8 neighboring coordinates and counts cells whose value in
     * mineField.field equals 1 (mine).
     *
     * @return number of adjacent mines (0..8)
     */
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

    /**
     * Reveal this cell's visual value (number sprite) and mark it revealed.
     *
     */
    @Override
    public void showValue() {
        this.setIcon(new ImageIcon("sprites/numberSprites/" + this.neighborMines + ".png"));
        this.isRevealed = true;
        System.out.println("revealed at " + this.row + "" + this.column);
    }

    /**
     * Reveal the cell at (row, col). Does safety checks, skips mines, and
     * implements flood-fill for zero-neighbor cells.
     *
     * This method is recursive: when a cell with zero neighboring mines is
     * revealed it recursively reveals the 8 neighbors. Already revealed cells
     * and out-of-bounds coordinates are ignored.
     *
     * @param row the row index to reveal
     * @param col the column index to reveal
     */
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

        checkWinCondition();
    
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

    /**
     * Update global reveal counters and trigger win UI when the final non-mine
     * cell is revealed.
     *
     * Increments game.CellsRevealed and compares against
     * game.target. If the final cell is revealed, shows either the
     * WinPanel or WinScreenPanel depending on the level.
     */
    void checkWinCondition() {

        if (game.cellsRevealed < game.target - 1) {
            game.cellsRevealed += 1;
        } else {
            if (game.levelNumber == 4) {
                new WinPanel();
            } else {
                new WinScreenPanel();
            }
        }
    }

    /**
     * Show this cell's value without marking it permanently revealed.
     */
    public void showForPotion() {
        this.setIcon(new ImageIcon("sprites/numberSprites/" + this.neighborMines + ".png"));
    }

    /**
     * Hide the cell again after a temporary reveal (potion), unless it is
     * already permanently revealed.
     */
    public void hideForPotion() {
        if (!this.isRevealed) {
            this.setIcon(new ImageIcon("sprites/wall_icon.png"));
        }
    }

    /**
     * Set default icon for this cell. For EmptyCell this method delegates to
     * showForPotion so the same sprite logic applies for potion flow.
     */
    @Override
    public void setDefaultIcon() {
        this.showForPotion();
    }

}