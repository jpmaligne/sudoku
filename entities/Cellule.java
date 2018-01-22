package entities;
import java.util.*;

public class Cellule {
    private int pos; //pos0= first top left, 1=top middle 7=bottom middel
    private int value;
    public int correctValue;

    public boolean isCorrect() {
        return correctValue == value;
    }

    public Cellule setCellule(Cellule[][] matrice, int lineIndex, int columnIndex) {
        ArrayList<Integer> possibleValue = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(possibleValue);
        int shuffleIndex = 0;
        boolean setted = false;
        int randValue = 0;

        while (setted == false) {
            randValue = possibleValue.get(shuffleIndex);
            Boolean isLineOk = Matrice.checkLine(matrice, lineIndex, randValue);
            Boolean isColumnOk = Matrice.checkColumn(matrice, columnIndex, randValue);
            Boolean isCaseOk = Matrice.checkCase(matrice, lineIndex, columnIndex, randValue);
            if (isLineOk && isColumnOk && isCaseOk) {
                setted = true;
                System.out.println("setted");
            } else {
                Matrice.showPartialMatrice(matrice);
                // System.out.print(randValue + ": ");
                // System.out.print(Boolean.toString(isLineOk) + Boolean.toString(isColumnOk) + Boolean.toString(isCaseOk));
                // System.out.println();
            }
            shuffleIndex = (shuffleIndex + 1) % 9;
        }
        this.correctValue = randValue;
        return this;
    }

    protected int getCorrectValue() {
        return this.correctValue;
    }
}