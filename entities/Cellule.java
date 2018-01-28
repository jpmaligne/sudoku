package entities;
import java.util.*;

public class Cellule {
    private int pos; //pos0= first top left, 1=top middle 7=bottom middel
    private int value;
    public int correctValue;

    public boolean isCorrect() {
        return correctValue == value;
    }

    public Boolean setCellule(Matrice matrice, int lineIndex, int columnIndex) {
        ArrayList<Integer> possibleValue = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(possibleValue);
        int shuffleIndex = 0;
        Boolean setted = false;
        int randValue = 0;

        while (setted == false && shuffleIndex < 9) {
            randValue = possibleValue.get(shuffleIndex);
            Boolean isLineOk = matrice.checkLine(lineIndex, randValue);
            Boolean isColumnOk = matrice.checkColumn(columnIndex, randValue);
            Boolean isCaseOk = matrice.checkCase(lineIndex, columnIndex, randValue);
            if (isLineOk && isColumnOk && isCaseOk) {
                setted = true;
                this.correctValue = randValue;
            }
            shuffleIndex = (shuffleIndex + 1);
        }
        return setted;
    }

    public int getCorrectValue() {
        if(this.correctValue != 0) {
            return this.correctValue;
        } else {
            return -1;
        }
    }
}