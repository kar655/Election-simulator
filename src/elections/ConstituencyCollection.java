package elections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ArrayList of Constituencies implementing custom iterator
 * which moves over MergedConstituencies
 */
public class ConstituencyCollection implements Iterable<Constituency> {

    ArrayList<Constituency> constituencies = new ArrayList<>();

    public ConstituencyCollection() {
    }

    // Returns ith Constituency
    public Constituency get(int i) {
        return constituencies.get(i);
    }

    // Adds Constituency
    public void add(Constituency constituency) {
        constituencies.add(constituency);
    }

    // Set Constituency at ith index
    public void set(int i, Constituency constituency) {
        constituencies.set(i, constituency);
    }

    @Override
    public Iterator<Constituency> iterator() {
        return new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < constituencies.size();
            }

            // Doesn't enter MergedConstituency two times
            @Override
            public Constituency next() {
                if (get(currentIndex).wasMerged())
                    currentIndex++;

                return get(currentIndex++);
            }
        };
    }


}
