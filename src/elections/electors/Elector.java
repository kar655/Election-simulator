package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.stream.Stream;

/**
 * Holds information of Elector
 */
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

    // Function to filter candidates that certain elector can vote for
    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        // Candidates are in certain constituency
        return candidates
                .filter(c -> this.constituency.sameId(c.getConstituencyNumber()));
    }


    // Calls all filters for candidates, increase candidate vote counter
    // remembers candidate name
    public void giveVote() {

        // every candidate must give vote
        Candidate candidate =
                voteFilter(constituency.getCandidates().stream()).findFirst().get();

        candidate.increaseVotes();
        this.candidateName = candidate.getName();

        constituency.giveVote(candidate.getPartyName());
    }

    // Weighted sum of characteristic needed in greedy party
    // is 0 if elector is not Average
    public int weightedSum(Candidate c) {
        return 0;
    }

    // Return i-th characteristic value
    public int getIthValue(int i) {
        return 0;
    }

    // If this is average elector
    public boolean isAverage() {
        return false;
    }

    @Override
    public String toString() {
        return name + " " + surname + " voted for " + candidateName;
    }
}
