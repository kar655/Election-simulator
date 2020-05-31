package elections.electors;

import elections.Candidate;
import elections.Constituency;

import java.util.stream.Stream;

/**
 * Characteristic elector voting for certain Party
 */
public class CharacteristicPartyElector extends CharacteristicElector
        implements IPartyFilter {

    protected final String partyName;

    public CharacteristicPartyElector(String name,
                                      String surname,
                                      Constituency con,
                                      String partyName,
                                      int... characteristics) {
        super(name, surname, con, characteristics);
        this.partyName = partyName;
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return partyFilter(candidates, this.partyName);
    }
}