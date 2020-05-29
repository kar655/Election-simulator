package elections;


public class Operation {

    protected int[] values;
    protected int absSum = 0;

    public Operation(int[] values) {
        this.values = values;

        for (int val : values)
            this.absSum += Math.abs(val);
    }

    public Operation(int size) {
        this.values = new int[size];
    }

    protected Operation(Operation op) {
        this.values = op.values;
        this.absSum = op.absSum;
    }

    public int get(int i) {
        return values[i];
    }

    public int getAbsSum() {
        return this.absSum;
    }

    public void add(Operation o) {
        // todo max z?
        for (int i = 0; i < values.length; i++) {
            values[i] += o.get(i);
        }
    }
}
