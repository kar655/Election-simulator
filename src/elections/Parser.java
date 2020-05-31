package elections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads and format lines from file
 */
public class Parser {

    private final Scanner scanner;

    public Parser(String fileName) throws FileNotFoundException {
        this.scanner = new Scanner(new File(fileName));
    }

    // Reads line from file, split it by spaces
    public String[] readLine() {
        return scanner.nextLine().split(" ");
    }

    // Reads line corresponding to constituency going to be merged
    public int[] readPairs() {
        return Arrays.stream(scanner.nextLine().split("[(), ]"))
                .skip(1) // how many pairs - can be skipped
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .map(x -> x - 1) // to get position in arrays
                .toArray();
    }
}
