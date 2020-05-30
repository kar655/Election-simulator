package elections;

import java.util.ArrayList;
import java.util.Iterator;

public class ConstituencyCollection implements Iterable<Constituency> {

    ArrayList<Constituency> constituencies = new ArrayList<>();

    public ConstituencyCollection() {
    }

    public Constituency get(int i) {
        return constituencies.get(i);
    }

    public void add(Constituency constituency) {
        constituencies.add(constituency);
    }

    public void set(int i, Constituency constituency) {
        constituencies.set(i, constituency);
    }

    public int size() {
        return constituencies.size();
    }

    @Override
    public Iterator<Constituency> iterator() {
        return new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < constituencies.size();
            }

            @Override
            public Constituency next() {
                if (get(currentIndex).wasMerged())
                    currentIndex++;

                return get(currentIndex++);
            }
        };
    }


}
