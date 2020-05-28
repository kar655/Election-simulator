package elections;

import elections.electors.Elector;
import elections.electors.MinElector;

import java.util.ArrayList;
import java.util.Arrays;

public class Elections {

    private int constituenciesNumber;
    private int partiesNumber;
    private int changesNumber;
    private int characteristicsNumber;


    private Parser parser;
    private ArrayList<Party> parties = new ArrayList<>();
    private ArrayList<Elector> electors = new ArrayList<>();
    private ArrayList<Candidate> candidates = new ArrayList<>();
    private ArrayList<Constituency> constituencies = new ArrayList<>();

    public Elections(Parser parser) {
        this.parser = parser;
    }

    public void readBasicInfo() {
        String[] line = parser.readLine();

        constituenciesNumber = Integer.parseInt(line[0]);
        partiesNumber = Integer.parseInt(line[1]);
        changesNumber = Integer.parseInt(line[2]);
        characteristicsNumber = Integer.parseInt(line[3]);
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

    private void readElector() {

    }

    private void readCandidate() {
        String[] line = parser.readLine();
        // Konrad K 1 PartiaA 1 -72 -73 19 -83 2
        Candidate candidate = new Candidate(line[0],
                line[1],
                Integer.parseInt(line[2]),
                line[3],
                Integer.parseInt(line[3]),
                Arrays.stream(line).skip(5).
                        mapToInt(Integer::parseInt).toArray());
    }

    public void readInfo() {

    }
}
