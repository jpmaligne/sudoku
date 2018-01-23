package entities;
import java.util.*;
import entities.Cellule;

public class Matrice {
    private Cellule[][] matrice = new Cellule[9][9];

    public void initialize(int lineIndex) {
        int resetCount = 0;
        for (int i = lineIndex; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                Cellule aCell = new Cellule();
                this.matrice[i][j] = aCell;
                Boolean setted = aCell.setCellule(this, i, j);
                if (!setted) {
                    j = -1;
                    this.showPartialMatrice();
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
    }

    public void showMatrice() {
        for (int i = 0; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                System.out.print(this.matrice[i][j].getCorrectValue());
                if (j == 8) {
                    System.out.println("\n");
                }
            }
        }
    }

    public void showPartialMatrice() {
        for (int i = 0; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                if(this.matrice[i][j] != null) {
                    if(j == 0) {
                        System.out.print(i + " | ");
                    }
                    System.out.print(this.matrice[i][j].getCorrectValue());
                }
                if (j == 8) {
                    System.out.println("\n");
                }
            }
        }
    }

    public void resetLine(int lineIndex) {
        this.matrice[lineIndex] = new Cellule[9];
    }

    public Boolean checkLine(int lineIndex, int value) {
        for (Cellule cellule : this.matrice[lineIndex]) {
            if (cellule != null && cellule.correctValue == value){
                return false;
            }
        }
        return true;
    }

    public Boolean checkColumn(int columnIndex, int value) {
        for(int i = 0; i < 9; i++) {
            if(this.matrice[i][columnIndex] != null && this.matrice[i][columnIndex].correctValue == value) {
                return false;
            }
        }
        return true;
    }

    public Boolean checkCase(int lineIndex, int columnIndex, int value) {
        ArrayList<Integer> linIndexes = Matrice.getIndexesToAnalyse(lineIndex);
        ArrayList<Integer> colIndexes = Matrice.getIndexesToAnalyse(columnIndex);
        for (int lIndex: linIndexes) {
            for (int cIndex: colIndexes) {
                if (this.matrice[lIndex][cIndex] != null && this.matrice[lIndex][cIndex].correctValue == value) {
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
            indexes = new ArrayList<Integer>(Arrays.asList(index - 1, index, index + 1));
        }
        else if (modulo == 2) {
            indexes = new ArrayList<Integer>(Arrays.asList(index - 2, index - 1, index));
        }
        else {  // modulo == 0
            indexes = new ArrayList<Integer>(Arrays.asList(index, index + 1, index + 2));
        }
        return indexes;
    }
}