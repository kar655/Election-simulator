package elections;

public class Candidate {

    private final String name;
    private final String surname;
    private final int constituencyNumber; // okreg wyborczy xd todo
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

    public int getConstituencyNumber() {
        return constituencyNumber;
    }

    public int getListPosition() {
        return listPosition;
    }

    public String getPartyName() {
        return partyName;
    }

    public int getIthCharacteristics(int i) {
        return characteristic[i];
    }

    public void increaseVotes() {
        this.gotVotes++;
    }

    public String getName() {
        return name + " " + surname;
    }

    @Override
    public String toString() {
        return getName() + " " + partyName + " "
                + listPosition + " " + gotVotes;
    }
}
