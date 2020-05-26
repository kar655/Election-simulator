package elections;

import java.util.stream.Stream;

public interface IPartyFilter {

    default Stream<Candidate> partyFilter(Stream<Candidate> candidates,
                                          String partyName) {
        return candidates
                .filter(c -> c.getPartyName().equals(partyName));
    }
}
