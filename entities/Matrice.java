package entities;
import java.util.*;
import entities.Cellule;

public class Matrice {
    private Cellule[][] matrice = new Cellule[9][9];

    public void initialize() {
        for (int i = 0; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                Cellule aCell = new Cellule();
                this.matrice[i][j] = aCell.setCellule(this.matrice, i, j);
            }
        }
    }

    public void showMatrice() {
        // System.out.println(this.matrice[1][1].correctValue);
        for (int i = 0; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                System.out.print(this.matrice[i][j].getCorrectValue());
                if (j == 8) {
                    System.out.println("\n");
                }
            }
        }
    }

    public static void showPartialMatrice(Cellule[][] matrice) {
        for (int i = 0; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                if(matrice[i][j] != null) {
                    if(j == 0) {
                        System.out.print(i + " | ");
                    }
                    System.out.print(matrice[i][j].getCorrectValue());
                }
                if (j == 8) {
                    System.out.println("\n");
                }
            }
        }
    }

    public static boolean checkLine(Cellule[][] matrice, int lineIndex, int value) {
        for (Cellule cellule : matrice[lineIndex]) {
            if (cellule != null && cellule.correctValue == value){
                return false;
            }
        }
        return true;
    }

    public static boolean checkColumn(Cellule[][] matrice, int columnIndex, int value) {
        for(int i = 0; i < 9; i++) {
            if(matrice[i][columnIndex] != null && matrice[i][columnIndex].correctValue == value) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkCase(Cellule[][] matrice, int lineIndex, int columnIndex, int value) {
        ArrayList<Integer> linIndexes = Matrice.getIndexesToAnalyse(lineIndex);
        ArrayList<Integer> colIndexes = Matrice.getIndexesToAnalyse(columnIndex);
        for (int lIndex: linIndexes) {
            for (int cIndex: colIndexes) {
                if (matrice[lIndex][cIndex] != null && matrice[lIndex][cIndex].correctValue == value) {
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