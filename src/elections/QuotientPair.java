package elections;

/**
 * Holds quotient information, implements Comparable
 */
public class QuotientPair implements Comparable<QuotientPair> {

    private float quot;
    private final float firstValue;
    private int lastDivisor = 1;
    private final String partyName;

    public QuotientPair(float quot, String partyName) {
        this.firstValue = this.quot = quot;
        this.partyName = partyName;
    }

    // Returns quot
    public float first() {
        return quot;
    }

    // Returns party name
    public String second() {
        return partyName;
    }

    // Updates quot value by dividing with increased lastDivisor by step
    public void update(int step) {
        lastDivisor += step;
        quot = firstValue / lastDivisor;
    }

    // Allow to sort
    @Override
    public int compareTo(QuotientPair o) {
        // '-' for reversed sorting
        return -Float.compare(this.first(), o.first());
    }
}
