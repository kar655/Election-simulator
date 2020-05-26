package elections;

import java.util.Comparator;
import java.util.stream.Stream;

public class AverageElector extends CharacteristicElector {

    public AverageElector(String name,
                          String surname,
                          int constituencyNumber,
                          int... characteristics) {
        super(name, surname, constituencyNumber, characteristics);
    }

    private float weightedSum(Candidate c) {
        float sum = 0;
        float weightSum = 0;

        for (int i = 0; i < characteristics.length; i++) {
            weightSum += characteristics[i];
            sum += characteristics[i] * c.getIthCharacteristics(i);
        }

        return sum / weightSum;
    }

    @Override
    public int compare(Candidate c1, Candidate c2) {
        return Float.compare(weightedSum(c1), weightedSum(c2));
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates.max(this).stream();
    }
}
