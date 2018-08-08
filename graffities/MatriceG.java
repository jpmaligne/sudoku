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
        for (int i = 0; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                Cellule zeCell = zeMatrice[i][j];
                if (zeCell.isVisible()){
                    System.out.print(zeMatrice[i][j].getCorrectValue());
                } else {
                    System.out.print('*');
                }
                if (j == 8) {
                    System.out.println("\n");
                }
            }
        }
    }

    public static void showPartialMatrice(Cellule[][] zeMatrice) {
        for (int i = 0; i < 9; i++) {  // line
            for (int j = 0; j < 9; j++) {  // column
                if(zeMatrice[i][j] != null) {
                    if(j == 0) {
                        System.out.print(i + " | ");
                    }
                    System.out.print(zeMatrice[i][j].getCorrectValue());
                }
                if (j == 8) {
                    System.out.println("\n");
                }
            }
        }
    }

}