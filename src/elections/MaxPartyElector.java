package elections;

import java.util.stream.Stream;

public class MaxPartyElector extends MaxElector implements IPartyFilter {

    private String partyName;

    public MaxPartyElector(String name,
                           String surname,
                           int constituencyNumber,
                           String partyName,
                           int... characteristics) {
        super(name, surname, constituencyNumber, characteristics);

    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return partyFilter(candidates, );
    }

}
