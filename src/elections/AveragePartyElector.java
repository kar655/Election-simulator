package elections;

import java.util.stream.Stream;

public class AveragePartyElector extends AverageElector
        implements IPartyFilter {

    protected final String partyName;

    public AveragePartyElector(String name,
                               String surname,
                               int constituencyNumber,
                               String partyName,
                               int... characteristics) {
        super(name, surname, constituencyNumber, characteristics);
        this.partyName = partyName;
    }

    // todo tu chyba nie dziala dobrze bo najpierw max a potem filtruje
    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return partyFilter(candidates, this.partyName);
    }
}
