package elections;

import java.util.ArrayList;

public class GreedyParty extends Party {

    private ArrayList<Integer> weightsSum = new ArrayList<>();

    public GreedyParty(String name, int budget) {
        super(name, budget);
    }

    // When adding new Candidate remembers its characteristic also
    @Override
    public void addCandidate(Candidate c) {
        super.addCandidate(c);

        for (int i = 0; i < c.size(); i++) {
            if (weightsSum.size() < c.size())
                weightsSum.add(c.get(i));
            else
                weightsSum.set(i, weightsSum.get(i) + c.get(i));
        }
    }

    // Calculates sum of weighted sums for all candidates in this party for
    // certain constituency
    private long calculateWeightedSum(Constituency constituency) {
        long output = 0;

        for (int i = 0; i < weightsSum.size(); i++)
            output += weightsSum.get(i) * constituency.getIthSum(i);

        return output;
    }

    // Calculates sum of weighted sums for all candidates in this party for
    // certain constituency also applies new characteristic from operation
    private long calculateWeightedSum(Constituency constituency, Operation o) {
        long output = 0;

        for (int i = 0; i < weightsSum.size(); i++)
            output += weightsSum.get(i)
                    * (constituency.getIthSum(i)
                    + o.get(i) * constituency.getAverageElectorsNumber());

        return output;
    }

    @Override
    public void makeCampaign(ConstituencyCollection constituencies,
                             ArrayList<Operation> operations) {

        int price = 0;
        long biggestDifference = 0;
        int conId = -1, operationId = -1;

        for (Constituency constituency : constituencies) {
            for (int i = 0; i < operations.size(); i++) {

                long currentSum = calculateWeightedSum(constituency);

                long newSum = calculateWeightedSum(constituency, operations.get(i));

                int tempPrice = constituency.getElectorsNumber()
                        * operations.get(i).getAbsSum();

                if (newSum - currentSum > biggestDifference && tempPrice <= budget) {
                    conId = constituency.getFirstId() - 1;
                    operationId = i;
                    biggestDifference = newSum - currentSum;
                    price = tempPrice;
                }
            }
        }


        if (conId == -1 && operationId == -1)
            makeCampaign = false;
        else {
            budget -= price;
            // update filter in Constituency
            constituencies.get(conId).add(operations.get(operationId));
        }
    }
}
