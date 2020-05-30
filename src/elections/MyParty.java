package elections;

import java.util.ArrayList;

public class MyParty extends Party {

    public MyParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public void useStrategy(ConstituencyCollection constituencies,
                            ArrayList<Operation> operations) {
        System.out.println("NOT IMPLEMENTED - MY CLASS");
    }
}
