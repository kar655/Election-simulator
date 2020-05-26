package elections;

import java.util.ArrayList;

public abstract class Party {

    protected String name;
    protected int budget;

    public Party(String name, int budget) {
        this.name = name;
        this.budget = budget;
    }

    protected int operationSum(ArrayList<Integer> operation) {
        return operation.stream().map(Math::abs).reduce(0, Integer::sum);
    }

    protected int operationCost(ArrayList<Integer> operation,
                                Constituency constituency) {
        return operationSum(operation) * constituency.getElectorsNumber();
    }

    // todo protected abstract int operationValue
    //  i wystarczy jedna petla po wsyztskich stanach

    public abstract void useStrategy(ArrayList<Constituency> constituencies,
                                     ArrayList<ArrayList<Integer>> operations);
}
