package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.stream.Stream;

public class AverageElector extends CharacteristicElector {

    public AverageElector(String name,
                          String surname,
                          Constituency con,
                          int... characteristics) {
        super(name, surname, con, characteristics);
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates.max(this::compareAverage).stream();
    }
}
