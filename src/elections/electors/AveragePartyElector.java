package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.stream.Stream;

public class AveragePartyElector extends CharacteristicPartyElector {

    public AveragePartyElector(String name,
                               String surname,
                               Constituency con,
                               String partyName,
                               int... characteristics) {
        super(name, surname, con, partyName, characteristics);
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates.max(this::compareAverage).stream();
    }
}
