package elections.electors;

import elections.Candidate;

import java.util.stream.Stream;

public class MaxElector extends CharacteristicElector {

    public MaxElector(String name, String surname, int constituencyNumber,
                      int... characteristics) {
        super(name, surname, constituencyNumber, characteristics);
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        // using comparator from CharacteristicElector class
        return candidates.max(this).stream();
    }
}
