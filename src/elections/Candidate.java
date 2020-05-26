package elections;

public class Candidate {

    private final String name;
    private final String surname;
    private final int constituencyNumber; // okreg wyborczy xd todo
    private final String partyName;
    private final int listPosition;
    private int[] charachteristics;

    public Candidate(String name, String surname, int constituencyNumber,
                     String partyName, int listPosition, int[] charachtertisics) {
        this.name = name;
        this.surname = surname;
        this.constituencyNumber = constituencyNumber;
        this.partyName = partyName;
        this.listPosition = listPosition;
        this.charachteristics = charachteristics;
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
        return charachteristics[i];
    }
}
