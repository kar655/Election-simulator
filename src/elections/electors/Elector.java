package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Elector {

    protected final String name;
    protected final String surname;
    protected Constituency constituency; // okreg wyborczy xd todo
    protected String candidateName;

    public Elector(String name, String surname, Constituency con) {
        this.name = name;
        this.surname = surname;
        this.constituency = con;
    }

    // candidates are in certain constituency
    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        return candidates
                .filter(c -> this.constituency.sameId(c.getConstituencyNumber()));
    }

    public void giveVote(ArrayList<Candidate> candidates) {

        Optional<Candidate> optional = voteFilter(candidates.stream()).findFirst();
        assert optional.isPresent(); // todo

        // return optional.get().getListPosition();
        optional.get().increaseVotes();
        this.candidateName = optional.get().getName();
    }

    public float weightedSum(Candidate c) {
        return 0;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + candidateName;
    }
}
