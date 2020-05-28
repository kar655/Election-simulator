package elections;

import java.util.ArrayList;

public abstract class Party {

    protected String name;
    protected int budget;
    protected ArrayList<Candidate> candidates = new ArrayList<>();

    public Party(String name, int budget) {
        this.name = name;
        this.budget = budget;
    }

    public void addCandidate(Candidate c) {
        candidates.add(c);
    }

    protected int operationCost(Operation operation,
                                Constituency constituency) {
        return operation.getAbsSum() * constituency.getElectorsNumber();
    }

    // todo protected abstract int operationValue
    //  i wystarczy jedna petla po wsyztskich stanach

    public abstract void useStrategy(ArrayList<Constituency> constituencies,
                                     ArrayList<Operation> operations);
}
