package elections;

import java.util.ArrayList;
import java.util.stream.Stream;

public class PartyElector extends Elector implements IPartyFilter {

    protected final String partyName;

    public PartyElector(String name, String surname, int constituencyNumber,
                        String partyName) {
        super(name, surname, constituencyNumber);
        this.partyName = partyName;
    }

    // candidates are in certain Party
    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return partyFilter(candidates, this.partyName);
    }
}
