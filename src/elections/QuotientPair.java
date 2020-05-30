package elections;

public class QuotientPair implements Comparable<QuotientPair> {

    private float quot;
    private final float firstValue;
    private int lastDivisor = 1;
    private final String partyName;

    public QuotientPair(float quot, String partyName) {
        this.firstValue = this.quot = quot;
        this.partyName = partyName;
    }

    public float first() {
        return quot;
    }

    public String second() {
        return partyName;
    }

    public void update(int step) {
        lastDivisor += step;
        quot = firstValue / lastDivisor;
    }

    @Override
    public int compareTo(QuotientPair o) {
        // todo '-' for reversed sorting
        return -Float.compare(this.first(), o.first());
    }
}
