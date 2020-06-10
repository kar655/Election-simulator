package elections;

/**
 * Represent vector of characteristic values
 */
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

    // Returns ith element
    public int get(int i) {
        return values[i];
    }

    // Returns absSum of initial values
    public int getAbsSum() {
        return this.absSum;
    }

    // Return size
    public int size() {
        return values.length;
    }

    // Adds in place values from other Operation with same size n times
    // Doesn't update absSum
    public void add(Operation o, int n) {
        for (int i = 0; i < values.length; i++)
            values[i] += o.get(i) * n;
    }

    public void add(Operation o) {
        add(o, 1);
    }
}
