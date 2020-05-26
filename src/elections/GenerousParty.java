package elections;

import java.util.ArrayList;
import java.util.Comparator;

public class GenerousParty extends Party {

    public GenerousParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public void useStrategy(ArrayList<Constituency> constituencies,
                            ArrayList<ArrayList<Integer>> operations) {
        // todo jakas tablica zmian w rejonie i gdy licze w electorze pobieram
        // poprawke z tego rejun

        // todo klasa operacja?


        int price = 0;
        int conId = 0, operationId = 0;

        for (int i = 0; i < constituencies.size(); i++) {
            for (int j = 0; j < operations.size(); j++) {

                int tempPrice = constituencies.get(i).getElectorsNumber()
                        * operationSum(operations.get(j));

                if (tempPrice <= budget && tempPrice > price) {
                    price = tempPrice;
                    conId = i;
                    operationId = j;
                }
            }
        }

        // todo tutaj jakis filtr na characteristics w constituency
    }
}
