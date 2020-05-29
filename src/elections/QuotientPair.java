package elections;

public class QuotientPair implements Comparable<QuotientPair> {

    private float quot;
    private final String partyName;

    public QuotientPair(float quot, String partyName) {
        this.quot = quot;
        this.partyName = partyName;
    }

    public float first() {
        return quot;
    }

    public String second() {
        return partyName;
    }

    public void update(int step) {
        quot /= step;
    }

    @Override
    public int compareTo(QuotientPair o) {
        return Float.compare(this.first(), o.first());
    }
}
