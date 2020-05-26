package elections;

import java.util.Comparator;
import java.util.stream.Stream;

public class MinElector extends CharacteristicElector {


    public MinElector(String name, String surname, int constituencyNumber,
                      int... characteristics) {
        super(name, surname, constituencyNumber, characteristics);
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        // using comparator from CharacteristicElector class
        return candidates.min(this).stream();
    }
}
