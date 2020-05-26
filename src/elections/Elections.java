package elections;

import java.util.ArrayList;

public class Elections {

    private int constituenciesNumber;
    private int partiesNumber;
    private int changesNumber;
    private int charachteristicsNumber;


    private Parser parser = new Parser();
    private ArrayList<Party> parties = new ArrayList<>();
    private ArrayList<Elector> electors = new ArrayList<>();
    private ArrayList<Candidate> candidates = new ArrayList<>();
    private ArrayList<Constituency> constituencies = new ArrayList<>();

    public void readBasicInfo() {

    }

}
