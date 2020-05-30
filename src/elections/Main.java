package elections;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println(
                    "You need to pass exactly one argument - file name");
            return;
        }

        Parser parser;

        try {
            parser = new Parser(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File " + args[0] + " doesn't exist");
            return;
        }

        Elections elections = new Elections(parser);
        elections.readInfo();
        elections.campaigns();
        elections.simulate();
    }
}
