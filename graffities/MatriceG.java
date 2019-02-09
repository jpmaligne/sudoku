package graffities;
import java.util.*;
import entities.Matrice;
import entities.Cellule;

/**
 * Graffities are UI
 * 
 * Matrice graffiti class.
 */
public class MatriceG extends Matrice {

    // Color used in system.out.print functions
    // Won't work for windows prompt, but who cares ?
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static Boolean showMatriceFromCoord(
        int i,
        int j,
        Matrice matriceEl,
        Boolean isOver
    ) {
        Cellule[][] matriceCells = matriceEl.getMatriceCells();
        Cellule zeCell = matriceCells[i][j];

        if (zeCell.isVisible()) {
            System.out.print(
                ANSI_GREEN +
                matriceCells[i][j].getCorrectValue() +
                " " +
                ANSI_RESET
            );
        } else if (zeCell.getTemporaryValue() != 0) {
            int tempVal = zeCell.getTemporaryValue();
            Boolean is_valid = 
                matriceEl.checkLineOnUpdate(i, j, tempVal) &&
                matriceEl.checkColumnOnUpdate(i, j, tempVal) &&
                matriceEl.checkCaseOnUpdate(i, j, tempVal);
            if (is_valid) {
                System.out.print(
                    ANSI_BLUE +
                    tempVal +
                    " " +
                    ANSI_RESET
                );
            } else {  // not valid value because conflit on line / column / case
                isOver = false;
                System.out.print(
                    ANSI_RED +
                    tempVal +
                    " " +
                    ANSI_RESET
                );
            }
        }
         else {
            isOver = false;
            System.out.print("* ");
        }
        if ((j+1) % 3 == 0) {
            System.out.print("|");
        }
        else {
            System.out.print(" ");
        }
        if (j == 8) {
            System.out.print("\n");
        } else {
            System.out.print(" ");
        }
        return isOver;
    }

    public static Boolean drawGridLayout(Matrice matriceEl) {
        System.out.println("");

        Boolean isOver = true;
        for (int i = 0; i < 10; i++) {  // line
            if (i % 3 == 1) {
                System.out.println("  |-----------|-----------|-----------|");
            }
            for (int j = 0; j < 10; j++) {  // column
                if (
                    i == 0 &&
                    j == 0
                ) {
                    System.out.print("  | ");
                }
                else if (i==0) {
                    System.out.print(j + " | ");
                    if (j == 9) {
                        System.out.print("\n");
                    }
                }
                else if (
                    j == 0 &&
                    i > 0
                ) {
                    System.out.print(i + " | ");
                }
                else {
                    isOver = MatriceG.showMatriceFromCoord(
                        i - 1,
                        j - 1,
                        matriceEl,
                        isOver
                    );
                }
            }
        }
        System.out.println(
            "  \\C_O_M_M_I_T_____" +
            ANSI_YELLOW +
            "***" +
            ANSI_RESET +
            "_____S_U_D_O_K_U/"
        );
        System.out.println("");
        return isOver;
    }

    public static void congrats() {
        System.out.println();
        System.out.println("YOU WON !");
        System.out.println("Well played");
    }
}