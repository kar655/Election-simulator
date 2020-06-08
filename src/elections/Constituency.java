package elections;

import elections.electors.Elector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Holds information about constituency
 */
public class Constituency extends Operation {

    protected final int MPNumber;
    protected final int electorsNumber;
    protected int averageElectorsNumber;
    protected ArrayList<Integer> id = new ArrayList<>();
    // <party name, got votes in this Constituency>
    protected LinkedHashMap<String, Integer> votes = new LinkedHashMap<>();
    protected ArrayList<Elector> electors = new ArrayList<>();
    protected ArrayList<Integer> electorsWeightSum;
    protected ArrayList<Candidate> candidates = new ArrayList<>();

    public Constituency(int id, int electorsNumber, int MPNumber,
                        int characteristicNumber,
                        Set<String> partyNames) {
        super(characteristicNumber);
        this.electorsWeightSum =
                new ArrayList<>(Collections.nCopies(characteristicNumber, 0));
        this.id.add(id);
        this.electorsNumber = electorsNumber;
        this.MPNumber = MPNumber;
        loadPartiesNames(partyNames);
    }

    protected Constituency(int electorsNumber, int MPNumber,
                           int[] filter) {
        super(filter);
        this.electorsWeightSum =
                new ArrayList<>(Collections.nCopies(filter.length, 0));
        this.electorsNumber = electorsNumber;
        this.MPNumber = MPNumber;
    }

    // Load party names to keep previous order
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

        for (int i = 0; i < electorsWeightSum.size(); i++)
            electorsWeightSum.set(i,
                    electorsWeightSum.get(i) + elector.getIthValue(i));

        averageElectorsNumber += elector.isAverage() ? 1 : 0;
    }

    // Adds candidate
    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    // Return sum of all electors value at position i
    // + filter for each average elector
    public int getIthSum(int i) {
        return electorsWeightSum.get(i) + get(i) * averageElectorsNumber;
    }

    // Add operation and also updates sum of weights for all electors
    @Override
    public void add(Operation o) {
        super.add(o); // add to filter
        for (int i = 0; i < electorsWeightSum.size(); i++)
            electorsWeightSum.set(i,
                    electorsWeightSum.get(i) + averageElectorsNumber * o.get(i));
    }

    // Same as add(Operation o) but n times
    public void add(Operation o, int n) {
        for (int i = 0; i < electorsWeightSum.size(); i++) {
            values[i] += o.get(i) * n;
            electorsWeightSum.set(i, electorsWeightSum.get(i) +
                    averageElectorsNumber * o.get(i) * n);
        }
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

    // Return number of average electors
    public int getAverageElectorsNumber() {
        return averageElectorsNumber;
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
