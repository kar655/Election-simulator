package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Elector {

    protected final String name;
    protected final String surname;
    protected Constituency constituency;
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


    // Calls all filters for candidates, increase candidate vote counter
    public void giveVote() {

        Optional<Candidate> optional =
                voteFilter(constituency.getCandidates().stream()).findFirst();
        assert optional.isPresent(); // todo

        Candidate candidate = optional.get();

        // return optional.get().getListPosition();
        candidate.increaseVotes();
        this.candidateName = candidate.getName();

        constituency.giveVote(candidate.getPartyName());
    }

    public float weightedSum(Candidate c) {
        return 0;
    }

    @Override
    public String toString() {
        return name + " " + surname + " voted for " + candidateName;
    }
}
