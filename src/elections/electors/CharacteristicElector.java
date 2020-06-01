package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.Comparator;

/**
 * Elector who has some extra characteristics like min, max, average
 */
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

    // Comparator for Min/Max electors
    @Override
    public int compare(Candidate c1, Candidate c2) {
        return c1.get(characteristics[0] - 1)
                - c2.get(characteristics[0] - 1);
    }

    // Comparator for Average electors
    public int compareAverage(Candidate c1, Candidate c2) {
        return Float.compare(weightedSum(c1), weightedSum(c2));
    }

    // I-th characteristic with filter from constituency
    protected int getCharacteristic(int i) {
        return characteristics[i] + constituency.get(i);
    }

    // Return i-th characteristic value for average elector
    @Override
    public int getIthValue(int i) {
        if (this.characteristics.length < 5)
            return 0;
        else
            return characteristics[i];
    }

    // Calculates weighted sum for AverageElector with Candidate c
    @Override
    public int weightedSum(Candidate c) {
        // minimal number of characteristics is 5
        // so this elector is not Average
        if (this.characteristics.length < 5)
            return 0;

        int sum = 0;
        // todo -1 ?
        for (int i = 0; i < characteristics.length; i++) {
            sum += getCharacteristic(i)
                    * c.get(i);
        }

        return sum;
    }

    @Override
    public boolean isAverage() {
        return this.characteristics.length >= 5;
    }
}
