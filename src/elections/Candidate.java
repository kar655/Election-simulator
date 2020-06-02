package elections;

/**
 * Class for holding information about candidate
 */
public class Candidate extends Operation {

    private final String name;
    private final String surname;
    private final int constituencyNumber;
    private final String partyName;
    private final int listPosition;
    private int gotVotes = 0;

    public Candidate(String name, String surname, int constituencyNumber,
                     String partyName, int listPosition, int... characteristic) {
        super(characteristic);
        this.name = name;
        this.surname = surname;
        this.constituencyNumber = constituencyNumber;
        this.partyName = partyName;
        this.listPosition = listPosition;
    }

    // Returns constituency number
    public int getConstituencyNumber() {
        return constituencyNumber;
    }

    // Returns list position
    public int getListPosition() {
        return listPosition;
    }

    // Returns party name
    public String getPartyName() {
        return partyName;
    }

    // Increases vote counter
    public void increaseVotes() {
        this.gotVotes++;
    }

    // Returns full name of candidate
    public String getName() {
        return name + " " + surname;
    }

    @Override
    public String toString() {
        return getName() + " " + partyName + " "
                + listPosition + " got " + gotVotes + " votes";
    }
}
