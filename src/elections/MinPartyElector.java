package elections;

import java.util.stream.Stream;

public class MinPartyElector extends CharacteristicPartyElector {

    public MinPartyElector(String name,
                           String surname,
                           int constituencyNumber,
                           String partyName,
                           int... characteristics) {
        super(name, surname, constituencyNumber, partyName, characteristics);
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates.min(this).stream();
    }
}
