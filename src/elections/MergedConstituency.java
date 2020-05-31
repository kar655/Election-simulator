package elections;

/**
 * Represent two Constituency merged
 */
public class MergedConstituency extends Constituency {

    public MergedConstituency(Constituency first, Constituency second) {
        super(first.electorsNumber + second.electorsNumber,
                first.MPNumber + second.MPNumber,
                first.values);

        this.add(second); // sum filters
        this.id = first.id;
        this.id.addAll(second.id);
        this.votes = first.votes;
    }

    // If this constituency was merged
    @Override
    public boolean wasMerged() {
        return true;
    }
}
