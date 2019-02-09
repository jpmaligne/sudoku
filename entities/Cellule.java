package entities;
import java.util.*;

public class Cellule {
    private int temporaryValue;
    private Boolean hidden = false;
    public int correctValue;

    public boolean isCorrect() {
        if (this.hidden) {
            return false;
        }
        return this.correctValue == this.temporaryValue;
    }

    public Boolean setCellule(Matrice matrice, int lineIndex, int columnIndex) {
        ArrayList<Integer> possibleValue = new ArrayList<Integer>(
            Arrays.asList(
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            )
        );
        Collections.shuffle(possibleValue);
        int shuffleIndex = 0;
        Boolean setted = false;
        int randValue = 0;

        while (setted == false && shuffleIndex < 9) {
            randValue = possibleValue.get(shuffleIndex);
            Boolean isLineOk = matrice.checkLineOnInit(lineIndex, randValue);
            Boolean isColumnOk = matrice.checkColumnOnInit(columnIndex, randValue);
            Boolean isCaseOk = matrice.checkCaseOnInit(lineIndex, columnIndex, randValue);
            if (isLineOk && isColumnOk && isCaseOk) {
                setted = true;
                this.correctValue = randValue;
                this.temporaryValue = this.correctValue;
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

    public int getTemporaryValue() {
        return this.temporaryValue;
    }

    public void setTemporaryValue(int tempVal) {
        this.temporaryValue = tempVal;
    }

    public Boolean isVisible() {
        return !this.hidden;
    }
    public void hideCell() {
        this.temporaryValue = 0;
        this.hidden = true;
    }
}