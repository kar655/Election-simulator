package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.Comparator;
import java.util.stream.Stream;

public class CharacteristicElector extends Elector
        implements Comparator<Candidate> {

    protected final int[] characteristics;

    public CharacteristicElector(String name,
                                 String surname,
                                 Constituency con,
                                 int... characteristics) {
        super(name, surname, con);
        this.characteristics = characteristics;
    }

    @Override
    public int compare(Candidate c1, Candidate c2) {
        return c1.getIthCharacteristics(characteristics[0] - 1)
                - c2.getIthCharacteristics(characteristics[0] - 1);
    }

    public int compareAverage(Candidate c1, Candidate c2) {
        return Float.compare(weightedSum(c1), weightedSum(c2));
    }

    @Override
    public float weightedSum(Candidate c) {
        if (this.characteristics.length < 5)
            return 0;

        float sum = 0;
        float weightSum = 0;
        // todo -1 ?
        for (int i = 0; i < characteristics.length; i++) {
            weightSum += characteristics[i];
            sum += characteristics[i] * c.getIthCharacteristics(i);
        }

        return sum / weightSum;
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        return super.voteFilter(candidates);
    }
}
