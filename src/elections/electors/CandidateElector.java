package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.stream.Stream;

public class CandidateElector extends Elector {

    protected final int candidateListPosition;

    public CandidateElector(String name, String surname,
                            Constituency con,
                            int candidateListPosition) {
        super(name, surname, con);
        this.candidateListPosition = candidateListPosition;
    }

    // exact candidate
    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates
                .filter(c -> c.getListPosition() == this.candidateListPosition);
    }
}
