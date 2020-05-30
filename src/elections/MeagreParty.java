package elections;

import java.util.ArrayList;

public class MeagreParty extends Party {

    public MeagreParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public void useStrategy(ConstituencyCollection constituencies,
                            ArrayList<Operation> operations) {

        int price = Integer.MAX_VALUE;
        int conId = 0, operationId = 0;

        for (int i = 0; i < constituencies.size(); i++) {
            for (int j = 0; j < operations.size(); j++) {

                int tempPrice = constituencies.get(i).getElectorsNumber()
                        * operations.get(j).getAbsSum();

                if (tempPrice <= budget && tempPrice < price) {
                    price = tempPrice;
                    conId = i;
                    operationId = j;
                }
            }
        }

        if (conId == 0 && operationId == 0)
            makeCampaign = false;
        else {
            budget -= price;

            // update filter in Constituency
            constituencies.get(conId).add(operations.get(operationId));
        }
    }
}
