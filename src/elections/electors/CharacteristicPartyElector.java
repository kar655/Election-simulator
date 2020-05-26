package elections.electors;

import elections.Candidate;

import java.util.stream.Stream;

public class CharacteristicPartyElector extends CharacteristicElector
        implements IPartyFilter {

    protected final String partyName;

    public CharacteristicPartyElector(String name,
                                      String surname,
                                      int constituencyNumber,
                                      String partyName,
                                      int... characteristics) {
        super(name, surname, constituencyNumber, characteristics);
        this.partyName = partyName;
    }

    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        candidates = super.voteFilter(candidates);
        return partyFilter(candidates, this.partyName);
    }
}