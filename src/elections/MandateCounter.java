package elections;

import java.util.ArrayList;

public abstract class MandateCounter {

    protected String name;

    public MandateCounter(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract ArrayList<Integer> getMandates(Constituency constituency);
}
