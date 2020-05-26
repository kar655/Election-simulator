package elections;

import elections.electors.Elector;

import java.util.ArrayList;

public class Elections {

    private int constituenciesNumber;
    private int partiesNumber;
    private int changesNumber;
    private int characteristicsNumber;


    private Parser parser = new Parser();
    private ArrayList<Party> parties = new ArrayList<>();
    private ArrayList<Elector> electors = new ArrayList<>();
    private ArrayList<Candidate> candidates = new ArrayList<>();
    private ArrayList<Constituency> constituencies = new ArrayList<>();

    public void readBasicInfo() {

    }

    public void printResults() {
        // todo nazwę metody przeliczania głosów

        int electorsIndex = 0;
        int candidateIndex = 0;

        for (Constituency con : constituencies) {
            System.out.println(con);

            System.out.println(electors.get(electorsIndex++));
        }
    }
}
