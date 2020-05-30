package elections;

public class MergedConstituency extends Constituency {

    public MergedConstituency(Constituency first, Constituency second) {
        super(first.electorsNumber + second.electorsNumber,
                first.MPNumber + second.MPNumber,
                first.values);

        this.add(second); // sum filters
        this.id = first.id;
        this.id.addAll(second.id);
    }

    @Override
    public boolean wasMerged() {
        return true;
    }
}
