package elections;

import elections.electors.Elector;
import elections.electors.MaxElector;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello World");
        Parser parser = new Parser();

        Elector elector = new MaxElector("Jan",
                "Kowalski",
                2,
                1);

        Candidate candidate = new Candidate("Donald",
                "Tusk", 2, "SuperPartia",
                69, 20, 3123, 1);

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
