package elections;

import elections.electors.*;
import elections.mandates.DHondtMethod;
import elections.mandates.HareNiemeyerMethod;
import elections.mandates.MandateCounter;
import elections.mandates.SaintLagueMethod;
import elections.parties.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * Holds all data about elections
 */
public class Elections {

    private int constituenciesNumber;
    private int partiesNumber;
    private int changesNumber;
    private int characteristicsNumber;

    private Parser parser;
    private LinkedHashMap<String, Party> parties = new LinkedHashMap<>();
    private ArrayList<Operation> operations = new ArrayList<>();
    private ConstituencyCollection constituencies = new ConstituencyCollection();
    private ArrayList<MandateCounter> methods = new ArrayList<>() {
        {
            add(new DHondtMethod());
            add(new SaintLagueMethod());
            add(new HareNiemeyerMethod());
        }
    };

    public Elections(Parser parser) {
        this.parser = parser;
    }

    // Reads four basic parameters
    private void readBasicInfo() {
        String[] line = parser.readLine();

        constituenciesNumber = Integer.parseInt(line[0]);
        partiesNumber = Integer.parseInt(line[1]);
        changesNumber = Integer.parseInt(line[2]);
        characteristicsNumber = Integer.parseInt(line[3]);
    }

    // Initializes all constituencies, they are ready to be merged
    private void initializeConstituencies() {
        String[] electorsNumbers = parser.readLine();

        for (int i = 0; i < constituenciesNumber; i++) {
            constituencies.add(new Constituency(i + 1,
                    Integer.parseInt(electorsNumbers[i]),
                    Integer.parseInt(electorsNumbers[i]) / 10,
                    characteristicsNumber,
                    parties.keySet()));
        }
    }

    // Reads parties
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
                party = new MyParty(partiesInfo[0][i],
                        Integer.parseInt(partiesInfo[1][i]));
            }

            parties.put(partiesInfo[0][i], party);
        }
    }

    // Merges constituencies that are listed in pairs at index 2k and 2k + 1
    private void mergeConstituencies(int[] pairs) {
        for (int i = 0; i < pairs.length; i += 2) {
            Constituency merged = new MergedConstituency(
                    constituencies.get(pairs[i]),
                    constituencies.get(pairs[i + 1]));

            constituencies.set(pairs[i], merged);
            constituencies.set(pairs[i + 1], merged);
        }
    }

    // Reads one Candidate
    private void readCandidate(String[] line) {
        int constituencyNumber = Integer.parseInt(line[2]);

        Candidate candidate = new Candidate(line[0],
                line[1],
                constituencyNumber,
                line[3],
                Integer.parseInt(line[4]),
                Arrays.stream(line).skip(5).
                        mapToInt(Integer::parseInt).toArray());

        parties.get(line[3]).addCandidate(candidate);
        constituencies.get(constituencyNumber - 1).addCandidate(candidate);
    }

    // Reads candidates from input while 4th word is some Party name
    // last read line that is not Candidate is passed to readElector
    private void readCandidates() {
        String[] line = parser.readLine();

        while (parties.containsKey(line[3])) {
            readCandidate(line);
            line = parser.readLine();
        }

        readElector(line);
    }

    // Reads Elector adds it to constituency that is belongs to
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

    // Reads vector of values representing operation
    private void readOperation() {
        int[] values = Arrays.stream(parser.readLine())
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();

        operations.add(new Operation(values));
    }

    // Respectively calls reading functions
    public void readInfo() {
        readBasicInfo();
        int[] pairs = parser.readPairs();
        loadParties();
        initializeConstituencies();
        mergeConstituencies(pairs);
        readCandidates();

        // Need to skip one elector from first constituency
        // because one line too much was read when loading candidates
        boolean once = false;

        for (Constituency constituency : constituencies) {
            for (int i = 0; i < constituency.getElectorsNumber(); i++) {
                // one more was read when reading candidates
                if (!once && constituency.sameId(1)) {
                    once = true;
                    continue;
                }
                readElector(parser.readLine());
            }
        }

        for (int i = 0; i < changesNumber; i++)
            readOperation();
    }

    // Each party run its campaign while they have enough money
    public void campaigns() {
        for (Party party : parties.values())
            while (party.canMakeCampaign())
                party.makeCampaign(constituencies, operations);
    }

    // Each Elector votes, result are printed including
    // candidates and number of votes they got
    // and mandates each party got in that constituency
    public void simulate() {

        for (Constituency constituency : constituencies) {
            System.out.println("\n\n" + constituency + "\n\n");

            for (Elector elector : constituency.getElectors()) {
                elector.giveVote();
                System.out.println(elector);
            }

            System.out.println("\nCandidates in this constituency:\n");

            for (Candidate candidate : constituency.getCandidates()) {
                System.out.println(candidate);
            }

            for (MandateCounter method : methods) {
                System.out.println("\n" + method);
                method.getMandates(constituency);
            }
        }

        System.out.println();
    }

    // Prints mandates for all constituencies for each party
    public void countMandates() {
        System.out.println("\nMandates per all constituencies:\n\n");

        for (MandateCounter method : methods) {
            System.out.println(method);
            method.printResult();
            System.out.println();
        }
    }
}
