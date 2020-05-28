package elections;

import java.util.ArrayList;
import java.util.Comparator;

public class GenerousParty extends Party {

    public GenerousParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public void useStrategy(ArrayList<Constituency> constituencies,
                            ArrayList<Operation> operations) {

        int price = 0;
        int conId = 0, operationId = 0;

        for (int i = 0; i < constituencies.size(); i++) {
            for (int j = 0; j < operations.size(); j++) {

                int tempPrice = constituencies.get(i).getElectorsNumber()
                        * operations.get(j).getAbsSum();

                if (tempPrice <= budget && tempPrice > price) {
                    price = tempPrice;
                    conId = i;
                    operationId = j;
                }
            }
        }

        // update filter in Constituency
        constituencies.get(conId).add(operations.get(operationId));
    }
}
