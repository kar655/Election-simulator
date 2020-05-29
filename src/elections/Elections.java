package elections;

import elections.electors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

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
    private ArrayList<Operation> operations = new ArrayList<>();
    private ArrayList<Constituency> constituencies = new ArrayList<>();

    public Elections(Parser parser) {
        this.parser = parser;
    }

    private void readBasicInfo() {
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

            parties.put(partiesInfo[0][i], party);
        }
    }

    private void mergeConstituencies(int[] pairs) {
        for (int i = 0; i < pairs.length; i += 2) {
            Constituency merged = new MergedConstituency(
                    constituencies.get(pairs[i]),
                    constituencies.get(pairs[i + 1]));

            constituencies.set(pairs[i], merged);
            constituencies.set(pairs[i + 1], merged);
        }
    }

    private void readCandidate(String[] line) {
        // Konrad K 1 PartiaA 1 -72 -73 19 -83 2
        Candidate candidate = new Candidate(line[0],
                line[1],
                Integer.parseInt(line[2]),
                line[3],
                Integer.parseInt(line[4]),
                Arrays.stream(line).skip(5).
                        mapToInt(Integer::parseInt).toArray());

        parties.get(line[3]).addCandidate(candidate);
    }

    private void readCandidates() {
        String[] line = parser.readLine();

        while (parties.containsKey(line[3])) {
            readCandidate(line);
            line = parser.readLine();
        }

        readElector(line);
    }

    private void readElector(String[] line) {
        int type = Integer.parseInt(line[3]);

        Constituency constituency =
                constituencies.get(Integer.parseInt(line[2]) - 1);
        Elector elector;

        if (type == 1) {
            elector = new PartyElector(line[0], line[1],
                    constituency, line[4]);
        } else if (type == 2) {
            elector = new CandidateElector(line[0], line[1],
                    constituency, line[4], Integer.parseInt(line[5]));
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

    private void readOperation() {
        int[] values = Arrays.stream(parser.readLine())
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();

        operations.add(new Operation(values));
    }

    public void readInfo() {
        readBasicInfo();
        int[] pairs = parser.readPairs();
        loadParties();
        initializeConstituencies();
        mergeConstituencies(pairs);
        readCandidates();

        boolean once = false;

//        for (Constituency constituency : constituencies)
        for (int j = 0; j < constituencies.size(); j++) {
            if (constituencies.get(j).sameId(j + 2))
                j++;
            for (int i = 0; i < constituencies.get(j).getElectorsNumber(); i++) {
                // one more was read when reading candidates
                if (!once && constituencies.get(j).sameId(1)) {
                    once = true;
                    continue;
                }
                readElector(parser.readLine());
            }
        }

        for (int i = 0; i < changesNumber; i++)
            readOperation();
    }

    public void simulate() {
        System.out.println("Starting simulation...");

        for (Constituency constituency : constituencies)
            for (Elector elector : constituency.getElectors())
                elector.giveVote(candidates);

//        ConstituencyCollection c = new ConstituencyCollection();
//        c.constituencies = this.constituencies;
//        for (Constituency con : c)
//            System.out.println(con);
        QuotientMethod method = new QuotientMethod("D'Hondt");
        for (Constituency constituency : constituencies)
            method.getMandates(constituency);
    }
}
