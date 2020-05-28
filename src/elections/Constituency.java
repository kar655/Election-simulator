package elections;

import elections.electors.Elector;

import java.util.ArrayList;

public class Constituency extends Operation {

    protected ArrayList<Integer> id = new ArrayList<>();
    protected final int electorsNumber;
    protected final int MPNumber;
    protected ArrayList<Elector> electors = new ArrayList<>();
    protected Operation filter; // what campaign changed

    public Constituency(int id, int electorsNumber, int MPNumber,
                        int characteristicNumber) {
        super(new ArrayList<>(characteristicNumber));
        this.id.add(id);
        this.electorsNumber = electorsNumber;
        this.MPNumber = MPNumber;
    }

    protected Constituency(int electorsNumber, int MPNumber,
                           Operation filter) {
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

    public int getElectorsNumber() {
        return electorsNumber;
    }

    public String getId() {
        return id.get(0) + "";
    }

    @Override
    public String toString() {
        // todo
        return "";
    }
}
