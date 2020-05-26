package elections;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Elector {

    protected final String name;
    protected final String surname;
    protected final int constituencyNumber; // okreg wyborczy xd todo

    public Elector(String name, String surname, int constituencyNumber) {
        this.name = name;
        this.surname = surname;
        this.constituencyNumber = constituencyNumber;
    }

    // candidates are in certain constituency
    protected Stream<Candidate> voteFilter(Stream<Candidate> candidates) {
        return candidates
                .filter(c -> c.getConstituencyNumber() == this.constituencyNumber);
    }

    public int giveVote(ArrayList<Candidate> candidates) {

        Optional<Candidate> optional = voteFilter(candidates.stream()).findFirst();
        assert optional.isPresent(); // todo

        return optional.get().getListPosition();
    }
}
