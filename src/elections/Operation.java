package elections;

import java.util.ArrayList;

public class Operation {
    protected ArrayList<Integer> values;
    protected int absSum;

    public Operation(ArrayList<Integer> values) {
        this.values = values;
        // todo od razu podac sume
        int sum = 0;
        for (int val : values)
            sum += Math.abs(val);
        this.absSum = sum;
    }

    protected Operation(Operation op) {
        this.values = op.values;
        this.absSum = op.absSum;
    }

    public int get(int i) {
        return values.get(i);
    }

    public int getAbsSum() {
        return this.absSum;
    }

    public void add(Operation o) {
        // todo max z?
        for (int i = 0; i < values.size(); i++) {
            this.values.set(i, this.get(i) + o.get(i));
        }
    }
}
