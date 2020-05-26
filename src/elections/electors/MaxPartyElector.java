package elections.electors;

import elections.Candidate;

import java.util.stream.Stream;

public class MaxPartyElector extends CharacteristicPartyElector {

    public MaxPartyElector(String name,
                           String surname,
                           int constituencyNumber,
                           String partyName,
                           int... characteristics) {
        super(name, surname, constituencyNumber, partyName, characteristics);
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates.max(this).stream();
    }
}
