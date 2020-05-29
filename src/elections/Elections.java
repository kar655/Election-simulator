package elections;

import elections.electors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Elections {

    private int constituenciesNumber;
    private int partiesNumber;
    private int changesNumber;
    private int characteristicsNumber;


    private Parser parser;
    //    private ArrayList<Party> parties = new ArrayList<>();
    private HashMap<String, Party> parties = new HashMap<>();
    //    private ArrayList<Elector> electors = new ArrayList<>();
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
//        // todo nazwę metody przeliczania głosów
//
//        int electorsIndex = 0;
//        int candidateIndex = 0;
//
//        for (Constituency con : constituencies) {
//            System.out.println(con);
//
//            System.out.println(electors.get(electorsIndex++));
//        }
    }

    private void initializeConstituencies() {
        String[] electorsNumbers = parser.readLine();

        for (int i = 0; i < constituenciesNumber; i++) {
            constituencies.add(new Constituency(i + 1,
                    Integer.parseInt(electorsNumbers[i]),
                    Integer.parseInt(electorsNumbers[i]) / 10, // todo
                    characteristicsNumber));
        }
    }

    private void loadParties() {
        // names, budgets, strategies
        String[][] partiesInfo =
                {parser.readLine(), parser.readLine(), parser.readLine()};
        Party party;

        for (int i = 0; i < partiesNumber; i++) {
            if (partiesInfo[2][i].equals("R")) {
                party = new GenerousParty(partiesInfo[0][i],
                        Integer.parseInt(partiesInfo[1][i]));
            } else if (partiesInfo[2][i].equals("S")) {
                party = new MeagreParty(partiesInfo[0][i],
                        Integer.parseInt(partiesInfo[1][i]));
            } else if (partiesInfo[2][i].equals("Z")) {
                party = new GreedyParty(partiesInfo[0][i],
                        Integer.parseInt(partiesInfo[1][i]));
            } else { // partiesInfo[2][i].equals("W")
                party = new GenerousParty(partiesInfo[0][i],
                        Integer.parseInt(partiesInfo[1][i]));
            }

            parties.put(partiesInfo[i][0], party);
        }
    }

    private void mergeConstituencies() {
        int[] pairs = parser.readPairs();

        for (int i = 0; i < pairs.length; i += 2) {
            Constituency merged = new MergedConstituency(
                    constituencies.get(i),
                    constituencies.get(i + 1));

            constituencies.set(i, merged);
            constituencies.set(i + 1, merged);
        }
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

        parties.get(line[3]).addCandidate(candidate);
    }

    private void readElector() {
        String[] line = parser.readLine();
        int type = Integer.parseInt(line[3]);

        Constituency constituency = constituencies.get(Integer.parseInt(line[2]));
        Elector elector;

        if (type == 1) {
            elector = new PartyElector(line[0], line[1],
                    constituency, line[4]);
        } else if (type == 2) {
            elector = new CandidateElector(line[0], line[1],
                    constituency, Integer.parseInt(line[4]));
        } else if (type == 3) {
            elector = new MinElector(line[0], line[1],
                    constituency, Integer.parseInt(line[4]));
        } else if (type == 4) {
            elector = new MaxElector(line[0], line[1],
                    constituency, Integer.parseInt(line[4]));
        } else if (type == 5) {
            elector = new AverageElector(line[0], line[1],
                    constituency,
                    Arrays.stream(line).skip(4)
                            .mapToInt(Integer::parseInt).toArray());
        } else if (type == 6) {
            elector = new MinPartyElector(line[0], line[1],
                    constituency, line[5], Integer.parseInt(line[4]));
        } else if (type == 7) {
            elector = new MaxPartyElector(line[0], line[1],
                    constituency, line[5], Integer.parseInt(line[4]));
        } else { // type == 8
            elector = new AveragePartyElector(line[0], line[1],
                    constituency, line[4 + characteristicsNumber],
                    Arrays.stream(line).skip(4).limit(characteristicsNumber)
                            .mapToInt(Integer::parseInt).toArray());
        }

        constituency.addElector(elector);
    }

    public void readInfo() {

    }
}
