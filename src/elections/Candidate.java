package elections;

/**
 * Class for holding information about candidate
 */
public class Candidate {

    private final String name;
    private final String surname;
    private final int constituencyNumber;
    private final String partyName;
    private final int listPosition;
    private int[] characteristic;
    private int gotVotes = 0;

    public Candidate(String name, String surname, int constituencyNumber,
                     String partyName, int listPosition, int... characteristic) {
        this.name = name;
        this.surname = surname;
        this.constituencyNumber = constituencyNumber;
        this.partyName = partyName;
        this.listPosition = listPosition;
        this.characteristic = characteristic;
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

    // todo zamienic na Operation
    public int getIthCharacteristics(int i) {
        return characteristic[i];
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
                + listPosition + " " + gotVotes;
    }
}
