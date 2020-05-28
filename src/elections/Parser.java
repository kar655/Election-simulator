package elections;

import elections.electors.Elector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

//    public Elector readElector() {
//
//    }
}
