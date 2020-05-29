package elections;

import elections.electors.Elector;

import java.util.ArrayList;
import java.util.HashMap;

public class Constituency extends Operation {

    protected ArrayList<Integer> id = new ArrayList<>();
    protected HashMap<String, Integer> votes = new HashMap<>();
    protected final int electorsNumber;
    protected final int MPNumber;
    protected ArrayList<Elector> electors = new ArrayList<>();

    public Constituency(int id, int electorsNumber, int MPNumber,
                        int characteristicNumber) {
        super(characteristicNumber);
        this.id.add(id);
        this.electorsNumber = electorsNumber;
        this.MPNumber = MPNumber;
    }

    protected Constituency(int electorsNumber, int MPNumber,
                           int[] filter) {
        super(filter);
        this.electorsNumber = electorsNumber;
        this.MPNumber = MPNumber;
    }

    public boolean sameId(int id) {
        return this.id.stream().anyMatch(x -> x == id);
    }

    public void addElector(Elector elector) {
        electors.add(elector);
    }

    public int constituencyWeightedSum(Candidate c) {
        int output = 0;
        for (Elector elector : electors) {
            output += elector.weightedSum(c);
        }

        return output;
    }

    public void giveVote(String partyName) {
        if (votes.containsKey(partyName))
            votes.put(partyName, votes.get(partyName) + 1);
        else
            votes.put(partyName, 1);
    }

    public HashMap<String, Integer> getVotes() {
        //return new HashMap<>(); // todo mapa <nazwa partii, liczba glosow>
        return votes;
    }

    public ArrayList<Elector> getElectors() {
        return electors;
    }

    public int getElectorsNumber() {
        return electorsNumber;
    }

    public int getMPNumber() {
        return MPNumber;
    }

    public String getId() {
        return id.get(0) + "";
    }

    public boolean wasMerged() {
        return false;
    }

    @Override
    public String toString() {
        // todo
        return "" + id;
    }
}
