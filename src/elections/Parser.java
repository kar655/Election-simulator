package elections;

import elections.electors.Elector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {

    private Scanner scanner;

    public Parser(String fileName) throws FileNotFoundException {
        this.scanner = new Scanner(new File(fileName));
    }

//    public void read() {
//        Scanner sc = new Scanner(System.in);
//    }

    public String[] readLine() {
        return scanner.nextLine().split(" ");
    }

    public int[] readPairs() {
        return Arrays.stream(scanner.nextLine().split("[(), ]"))
                .skip(1)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .map(x -> x - 1)
                .toArray();
    }

//    public Elector readElector() {
//
//    }
}
