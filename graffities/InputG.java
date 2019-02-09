package graffities;
import java.util.*;
import entities.Matrice;

/**
 * Graffities are UI
 * 
 * Input graffiti class used to render text in cli
 * when the console needs player interaction.
 */
public class InputG {

    public static int[] askInputs() {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        // prompt for the grid's line number
        System.out.print("Enter line number (from 1 to 9): ");

        // get line number as an int
        int lineNumber = scanner.nextInt();

        // prompt for the grid's col number
        System.out.print("Enter column number (from 1 to 9): ");

        // get col number as an int
        int colNumber = scanner.nextInt();

        // prompt for the grid's value
        System.out.print("Enter a value: ");

        // get col number as an int
        int value = scanner.nextInt();
        if (
            value > 9 ||
            value < 0
        ) {
            value = 0;
        }
        System.out.println(String.format("%d, %d, %d", lineNumber, colNumber, value));
        int inputs[] = new int[3];
        inputs[0] = lineNumber - 1;
        inputs[1] = colNumber - 1;
        inputs[2] = value;
        return inputs;
    }
}