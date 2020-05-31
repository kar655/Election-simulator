package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.stream.Stream;

/**
 * Minimizes certain characteristic from party
 */
public class MinPartyElector extends CharacteristicPartyElector {

    public MinPartyElector(String name,
                           String surname,
                           Constituency con,
                           String partyName,
                           int... characteristics) {
        super(name, surname, con, partyName, characteristics);
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates.min(this).stream();
    }
}
