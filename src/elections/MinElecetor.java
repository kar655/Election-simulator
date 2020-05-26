package elections;

import java.util.Comparator;
import java.util.stream.Stream;

public class MinElecetor extends Elector {

    private final int characteristic;

    public MinElecetor(String name, String surname, int constituencyNumber,
                       int characteristic) {
        super(name, surname, constituencyNumber);
        this.characteristic = characteristic;
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates
                .min(Comparator.comparing(
                        Candidate::getIthCharacteristics (this.characteristic)));
    }
}
