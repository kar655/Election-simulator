package elections;

import elections.electors.Elector;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Holds information about constituency
 */
public class Constituency extends Operation {

    protected final int MPNumber;
    protected final int electorsNumber;
    protected ArrayList<Integer> id = new ArrayList<>();
    protected LinkedHashMap<String, Integer> votes = new LinkedHashMap<>();
    protected ArrayList<Elector> electors = new ArrayList<>();
    protected ArrayList<Candidate> candidates = new ArrayList<>();

    public Constituency(int id, int electorsNumber, int MPNumber,
                        int characteristicNumber,
                        Set<String> partyNames) {
        super(characteristicNumber);
        this.id.add(id);
        this.electorsNumber = electorsNumber;
        this.MPNumber = MPNumber;
        loadPartiesNames(partyNames);
    }

    protected Constituency(int electorsNumber, int MPNumber,
                           int[] filter) {
        super(filter);
        this.electorsNumber = electorsNumber;
        this.MPNumber = MPNumber;
    }

    protected void loadPartiesNames(Set<String> names) {
        for (String name : names)
            votes.put(name, 0);
    }

    // Checks if this is Constituency with that id
    public boolean sameId(int id) {
        return this.id.stream().anyMatch(x -> x == id);
    }

    // Adds elector
    public void addElector(Elector elector) {
        electors.add(elector);
    }

    // Adds candidate
    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    // Calculates sum of weighted sums of electors in this constituency
    // for certain Candidate
    public int constituencyWeightedSum(Candidate c) {
        int output = 0;
        for (Elector elector : electors) {
            output += elector.weightedSum(c);
        }

        return output;
    }

    // Adds one vote for certain party in this constituency
    public void giveVote(String partyName) {
        if (votes.containsKey(partyName))
            votes.put(partyName, votes.get(partyName) + 1);
        else
            votes.put(partyName, 1);
    }

    // Returns map of PartyNames and got votes in this constituency
    public LinkedHashMap<String, Integer> getVotes() {
        return votes;
    }

    // Return electors in this constituency
    public ArrayList<Elector> getElectors() {
        return electors;
    }

    // Return candidates in this constituency
    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    // Return number of electors in this constituency
    public int getElectorsNumber() {
        return electorsNumber;
    }

    // Return number of MP in this constituency
    public int getMPNumber() {
        return MPNumber;
    }

    // Return id of this constituency
    public String getId() {
        return id + "";
    }

    // Return first id of this constituency
    public int getFirstId() {
        return id.get(0);
    }

    // If this constituency was merged
    public boolean wasMerged() {
        return false;
    }

    @Override
    public String toString() {
        return "Votes result in constituency " + getId();
    }
}
