package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.stream.Stream;

/**
 * Chooses exact candidate
 */
public class CandidateElector extends PartyElector {

    protected final int candidateListPosition;

    public CandidateElector(String name, String surname,
                            Constituency con,
                            String partyName,
                            int candidateListPosition) {
        super(name, surname, con, partyName);
        this.candidateListPosition = candidateListPosition;
    }

    // Exact Candidate
    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return candidates
                .filter(c -> c.getListPosition() == this.candidateListPosition);
    }
}
