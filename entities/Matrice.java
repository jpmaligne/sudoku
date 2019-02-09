package entities;
import java.util.*;
import entities.Cellule;
import graffities.*;

/**
 * Matrice class is our Sudoku grid.
 * As it contains 9 lines & 9 columns, it's a 2D array of Cellule
 * 
 * See also class Cellule.
 */
public class Matrice {

    // The 2 dimension array of cells
    protected Cellule[][] matrice = new Cellule[9][9];
    
    public Cellule[][] getMatriceCells() {
        return this.matrice;
    }

    // Set the difficulty here.
    // Format : (label, number of hidden cells)
    private HashMap<String, Integer> level = new HashMap<String, Integer>();
    {
        level.put("hard", 71);
        level.put("medium", 65);
        level.put("easy", 51);
    }

    public void initialize(int lineIndex) {
        int resetCount = 0;
        for (int i = lineIndex; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                Cellule aCell = new Cellule();
                this.matrice[i][j] = aCell;
                Boolean setted = aCell.setCellule(this, i, j);
                // Below is ugly, to modify ... one day
                if (!setted) {
                    j = -1;
                    // MatriceG.showPartialMatrice(this.matrice);
                    this.resetLine(i);
                    resetCount ++;
                    if (resetCount > 8) {
                        this.resetLine(i-2);
                        this.resetLine(i-1);
                        i = i - 2;
                        resetCount = 0;
                    }
                }
            }
        }
        this.hideSomeCells("easy");
    }

    private void hideSomeCells(String difficulty) {
        Integer numberCellsToHide = this.level.get(difficulty);
        for (int i = 0; i < numberCellsToHide; i++) {
            int x = (int)(Math.random() * 9);
            int y = (int)(Math.random() * 9);
            if (this.matrice[x][y].isVisible()) {
                this.matrice[x][y].hideCell();
                continue;
            }
            i = i - 1; // avoid duplicates
        }
    }

    public void resetLine(int lineIndex) {
        this.matrice[lineIndex] = new Cellule[9];
    }

    public Boolean checkLineOnInit(int lineIndex, int value) {
        for (Cellule cellule : this.matrice[lineIndex]) {
            if (
                cellule != null &&
                cellule.correctValue == value
            ){
                return false;
            }
        }
        return true;
    }

    public Boolean checkColumnOnInit(int columnIndex, int value) {
        for(int i = 0; i < 9; i++) {
            if(
                this.matrice[i][columnIndex] != null &&
                this.matrice[i][columnIndex].correctValue == value
            ) {
                return false;
            }
        }
        return true;
    }

    public Boolean checkCaseOnInit(int lineIndex, int columnIndex, int value) {
        ArrayList<Integer> linIndexes = Matrice.getIndexesToAnalyse(lineIndex);
        ArrayList<Integer> colIndexes = Matrice.getIndexesToAnalyse(columnIndex);
        for (int lIndex: linIndexes) {
            for (int cIndex: colIndexes) {
                if (
                    this.matrice[lIndex][cIndex] != null &&
                    this.matrice[lIndex][cIndex].correctValue == value
                ) {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean checkLineOnUpdate(int lineIndex, int columnIndex, int value) {
        for (Cellule cellule : this.matrice[lineIndex]) {
            if (
                cellule != this.matrice[lineIndex][columnIndex] &&
                cellule.getTemporaryValue() == value
            ){
                return false;
            }
        }
        return true;
    }

    public Boolean checkColumnOnUpdate(int lineIndex, int columnIndex, int value) {
        for(int i = 0; i < 9; i++) {
            if(
                i != lineIndex &&
                this.matrice[i][columnIndex].getTemporaryValue() == value
            ) {
                return false;
            }
        }
        return true;
    }

    public Boolean checkCaseOnUpdate(int lineIndex, int columnIndex, int value) {
        ArrayList<Integer> linIndexes = Matrice.getIndexesToAnalyse(lineIndex);
        ArrayList<Integer> colIndexes = Matrice.getIndexesToAnalyse(columnIndex);
        for (int lIndex: linIndexes) {
            for (int cIndex: colIndexes) {
                if (
                    lIndex != lineIndex &&
                    cIndex != columnIndex &&
                    this.matrice[lIndex][cIndex].getTemporaryValue() == value
                ) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Create a index list of 3 indexes toAnalyse to determine if the value already exist in a case row
     * each case are composed of 3 rows.
     */
    private static ArrayList<Integer> getIndexesToAnalyse(int index) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int modulo = index % 3;
        if (modulo == 1) {
            indexes = new ArrayList<Integer>(
                Arrays.asList(
                    index - 1,
                    index,
                    index + 1
                )
            );
        }
        else if (modulo == 2) {
            indexes = new ArrayList<Integer>(
                Arrays.asList(
                    index - 2,
                    index - 1,
                    index
                )
            );
        }
        else {  // modulo == 0
            indexes = new ArrayList<Integer>(
                Arrays.asList(
                    index,
                    index + 1,
                    index + 2
                )
            );
        }
        return indexes;
    }
}