package elections.parties;

import elections.Constituency;
import elections.ConstituencyCollection;
import elections.Operation;

import java.util.ArrayList;

public abstract class FinanceParty extends Party {

    // Lowest price in certain order
    protected final int initialPrice;

    public FinanceParty(String name, int budget, int initialPrice) {
        super(name, budget);
        this.initialPrice = initialPrice;
    }

    // What party want to prioritize
    protected abstract boolean compare(int tempPrice, int price);

    @Override
    public void makeCampaign(ConstituencyCollection constituencies,
                             ArrayList<Operation> operations) {
        int price = initialPrice;
        int conId = -1, operationId = -1;

        for (Constituency constituency : constituencies) {
            for (int i = 0; i < operations.size(); i++) {

                int tempPrice = constituency.getElectorsNumber()
                        * operations.get(i).getAbsSum();

                if (tempPrice <= budget && compare(tempPrice, price)) {
                    price = tempPrice;
                    conId = constituency.getFirstId() - 1;
                    operationId = i;
                }
            }
        }

        if (conId == -1 && operationId == -1)
            makeCampaign = false;
        else {
            int n = budget / price;
            budget -= n * price;

            // update filter in Constituency n times
            constituencies.get(conId).add(operations.get(operationId), n);
        }
    }
}
