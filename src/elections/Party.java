package elections;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class to holds information about party and its candidates
 */
public abstract class Party {

    protected String name;
    protected int budget;
    protected boolean makeCampaign = true;
    protected ArrayList<Candidate> candidates = new ArrayList<>();

    public Party(String name, int budget) {
        this.name = name;
        this.budget = budget;
    }

    // Adds new Candidate
    public void addCandidate(Candidate c) {
        candidates.add(c);
    }

    // Return boolean if Party can make campaign
    public boolean canMakeCampaign() {
        return makeCampaign;
    }

    // Makes new campaign according to party's strategy
    public abstract void makeCampaign(ConstituencyCollection constituencies,
                                      ArrayList<Operation> operations);

    @Override
    public String toString() {
        return name;
    }
}
