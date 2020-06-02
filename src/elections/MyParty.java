package elections;

import java.util.ArrayList;
import java.util.Random;

public class MyParty extends Party {

    private Random random = new Random();

    public MyParty(String name, int budget) {
        super(name, budget);
    }

    /**
     * Every campaign generates random value,
     * chooses operation with closest absolute sum to that random value,
     * choose constituency with most people that can still afford.
     */
    @Override
    public void makeCampaign(ConstituencyCollection constituencies,
                             ArrayList<Operation> operations) {

        // values in Operation are from -10 to 10
        int value = random.nextInt(10 * operations.get(0).size() + 1);
        int difference = Integer.MAX_VALUE;
        int price = 0;
        int conId = -1, operationId = -1;

        for (int i = 0; i < operations.size(); i++) {
            if (difference > Math.abs(value - operations.get(i).getAbsSum())) {
                difference = Math.abs(value - operations.get(i).getAbsSum());
                operationId = i;
            }
        }

        for (Constituency constituency : constituencies) {
            int tempPrice = constituency.getElectorsNumber()
                    * operations.get(operationId).getAbsSum();

            if (tempPrice <= budget && tempPrice > price) {
                price = tempPrice;
                conId = constituency.getFirstId() - 1;
            }
        }

        if (conId == -1)
            makeCampaign = false;
        else {
            budget -= price;
            // update filter in Constituency
            constituencies.get(conId).add(operations.get(operationId));
        }
    }
}
