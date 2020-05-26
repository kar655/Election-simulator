package elections;

public class MergedConstituency extends Constituency {

    public MergedConstituency(Constituency first, Constituency second) {
        super(first.electorsNumber + second.electorsNumber,
                first.MPNumber + second.MPNumber);

        this.id = first.id;
        this.id.addAll(second.id);
    }

    @Override
    public String getId() {
        return "(" + id.get(0) + "," + id.get(1) + ")";
    }
}
