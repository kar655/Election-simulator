package elections;

import java.util.Comparator;
import java.util.stream.Stream;

public class CharacteristicElector extends Elector
        implements Comparator<Candidate> {

    protected final int[] characteristics;

    public CharacteristicElector(String name,
                                 String surname,
                                 int constituencyNumber,
                                 int... characteristics) {
        super(name, surname, constituencyNumber);
        this.characteristics = characteristics;
    }

    @Override
    public int compare(Candidate c1, Candidate c2) {
        return c1.getIthCharacteristics(characteristics[0])
                - c2.getIthCharacteristics(characteristics[0]);
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        return super.voteFilter(candidates);
    }
}
