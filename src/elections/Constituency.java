package elections;

import java.util.ArrayList;

public class Constituency {
    protected ArrayList<Integer> id = new ArrayList<>();
    protected final int electorsNumber;
    protected final int MPNumber;

    public Constituency(int id, int electorsNumber, int MPNumber) {
        this.id.add(id);
        this.electorsNumber = electorsNumber;
        this.MPNumber = MPNumber;
    }

    protected Constituency(int electorsNumber, int MPNumber) {
        this.electorsNumber = electorsNumber;
        this.MPNumber = MPNumber;
    }

    public boolean sameId(int id) {
        return this.id.stream().anyMatch(x -> x == id);
    }
}
