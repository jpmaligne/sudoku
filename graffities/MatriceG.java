package graffities;
import java.util.*;
import entities.Matrice;
import entities.Cellule;

/**
 * Graffities are UI
 * 
 * Matrice graffiti class.
 */
public class MatriceG extends Matrice{

    public static void showMatrice(Cellule[][] zeMatrice) {
        for (int i = 0; i < 10; i++) {  // line
            for (int j = 0; j < 10; j++) {  // column
                if (i > 0 && j > 0) {
                    Cellule zeCell = zeMatrice[i-1][j-1];
                    if (zeCell.isVisible()){
                        System.out.print(zeMatrice[i-1][j-1].getCorrectValue());
                    } else {
                        System.out.print('*');
                    }
                    if (j == 9) {
                        System.out.println("\n");
                    }
                }
            }
        }
    }

    // LEGACY
    public static void showPartialMatrice(Cellule[][] zeMatrice) {
        for (int i = 0; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                if(zeMatrice[i][j] != null) {
                    if(j == 0) {
                        System.out.print(i + " | ");
                    }
                    System.out.print(zeMatrice[i][j].getTemporaryValue());
                }
                if (j == 8) {
                    System.out.println("\n");
                }
            }
        }
    }

    public static void showMatriceFromCoord(int i, int j, Cellule[][] zeMatrice) {
        Cellule zeCell = zeMatrice[i][j];
        if (zeCell.isVisible()){
            System.out.print(zeMatrice[i][j].getCorrectValue() + "  ");
        } else {
            System.out.print("*  ");
        }
        if (j == 8) {
            System.out.println("\n");
        } else {
            System.out.print(" ");
        }
    }

    public static void drawGridLayout(Cellule[][] zeMatrice) {
        for (int i = 0; i < 10; i++) {  // line
            for (int j = 0; j < 10; j++) {  // column
                if (i == 0 && j == 0) {
                    System.out.print("  | ");
                }
                else if (i==0) {
                    System.out.print(j);
                    if (j != 9) {
                        System.out.print(" | ");
                    } else {
                        System.out.print("\n");
                    }
                }
                else if (j == 0 && i > 0) {
                    System.out.print(i + " | ");
                }
                else if (i == 0 && j == 9) {
                    System.out.println("\n");
                }
                else {
                    MatriceG.showMatriceFromCoord(i-1, j-1, zeMatrice);
                }
            }
        }
    }

}