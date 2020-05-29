package elections;

import elections.electors.Elector;
import elections.electors.MaxElector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

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
//        elections.simulate();
        elections.printResults();


        Constituency con = new Constituency(2, 1, 3, 1);

        Elector elector = new MaxElector("Jan",
                "Kowalski",
                con,
                1);

        Candidate candidate = new Candidate("Donald",
                "Tusk", 2, "SuperPartia",
                69, 20);

        ArrayList<Candidate> ac = new ArrayList<>();
        ac.add(candidate);
        ac.add(new Candidate("KOZAK", "x", 2,
                "XD", 127, -200));

        ac.add(new Candidate("KOZAK21", "x", 3,
                "XD", 127, 200));

        elector.giveVote(ac);
        System.out.println(elector);
    }
}
