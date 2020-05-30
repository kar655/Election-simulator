package elections.electors;

import elections.Candidate;

import java.util.stream.Stream;

public interface IPartyFilter {

    // Filters candidates from one Party
    default Stream<Candidate> partyFilter(Stream<Candidate> candidates,
                                          String partyName) {
        return candidates
                .filter(c -> c.getPartyName().equals(partyName));
    }
}
