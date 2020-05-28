package elections.electors;

import elections.Candidate;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Elector {

    protected final String name;
    protected final String surname;
    protected final int constituencyNumber; // okreg wyborczy xd todo
    protected String candidateName;

    public Elector(String name, String surname, int constituencyNumber) {
        this.name = name;
        this.surname = surname;
        this.constituencyNumber = constituencyNumber;
    }

    // candidates are in certain constituency
    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        return candidates
                .filter(c -> c.getConstituencyNumber() == this.constituencyNumber);
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
