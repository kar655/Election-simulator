package elections.electors;

import elections.Candidate;

import java.util.stream.Stream;

public class CandidateElector extends Elector {

    protected final int candidateListPosition;

    public CandidateElector(String name, String surname, int constituencyNumber,
                            int candidateListPosition) {
        super(name, surname, constituencyNumber);
        this.candidateListPosition = candidateListPosition;
    }

    // exact candidate
    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates
                .filter(c -> c.getListPosition() == this.candidateListPosition);
    }
}
