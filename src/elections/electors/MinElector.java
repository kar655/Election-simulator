package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.stream.Stream;

/**
 * Minimizes certain characteristic
 */
public class MinElector extends CharacteristicElector {

    public MinElector(String name, String surname, Constituency con,
                      int... characteristics) {
        super(name, surname, con, characteristics);
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        // using comparator from CharacteristicElector class
        return candidates.min(this).stream();
    }
}
