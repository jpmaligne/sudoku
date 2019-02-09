import java.util.*;
import entities.Matrice;
import entities.Cellule;
import graffities.*;

public class jeu {

    public static void main(String[] args) {
        Matrice matrice = new Matrice();
        matrice.initialize(0);
        MatriceG.drawGridLayout(matrice);

        Cellule[][] matriceCells = matrice.getMatriceCells();

        while(true) {
            int inputs[] = InputG.askInputs();
            int lineNumber = inputs[0];
            int colNumber = inputs[1];
            int value = inputs[2];
            matriceCells[lineNumber][colNumber].setTemporaryValue(value);
            Boolean isOver = MatriceG.drawGridLayout(matrice);
            if (isOver) {
                break;
            }
        }
        MatriceG.congrats();
    }
}
