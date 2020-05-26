package elections;

public class MergedConstituency extends Constituency {


    public MergedConstituency(Constituency first, Constituency second) {
        super(first.electorsNumber + second.electorsNumber,
                first.MPNumber + second.MPNumber);

        this.id = first.id;
        this.id.addAll(second.id);
    }
}
