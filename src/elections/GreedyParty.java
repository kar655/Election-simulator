package elections;

import java.util.ArrayList;

public class GreedyParty extends Party {

    public GreedyParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public void makeCampaign(ConstituencyCollection constituencies,
                             ArrayList<Operation> operations) {

        int price = 0;
        float maxWeightedSum = 0;
        int conId = 0, operationId = 0;

        for (int i = 0; i < constituencies.size(); i++) {
            for (int j = 0; j < operations.size(); j++) {

                int tempPrice = constituencies.get(i).getElectorsNumber()
                        * operations.get(j).getAbsSum();

                if (tempPrice <= budget)
                    continue;

                float weightedSum = 0;
                for (Candidate c : this.candidates) {
                    weightedSum += constituencies.get(i)
                            .constituencyWeightedSum(c);
                }

                if (weightedSum > maxWeightedSum) {
                    maxWeightedSum = weightedSum;
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
