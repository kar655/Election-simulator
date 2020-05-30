package elections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HareNiemeyerMethod extends MandateCounter {

    public HareNiemeyerMethod() {
        super("Hare-Niemeyer method");
    }

    private float rest(float x) {
        return x - (float) Math.floor(x); // todo mozna castowac?
    }

    private float calculate(float expectingMandates,
                            float allVotes, float gotVotes) {
        return gotVotes * expectingMandates / allVotes;
    }

    @Override
    public void getMandates(Constituency constituency) {
        PriorityQueue<QuotientPair> priorityQueue = new PriorityQueue<>();
        HashMap<String, Integer> mandates = new HashMap<>();
        int expectingMandates = constituency.getMPNumber();
        int allVotes = constituency.getElectorsNumber();
        int mandatesNumber = 0;

        for (Map.Entry<String, Integer> entry
                : constituency.getVotes().entrySet()) {

            // todo expectingMandates / allVotes === 1/10
            int floorMandates = entry.getValue() * expectingMandates / allVotes;
            mandatesNumber += floorMandates;
            mandates.put(entry.getKey(), floorMandates);

            priorityQueue.add(
                    new QuotientPair(
                            rest(calculate(expectingMandates, allVotes, entry.getValue())),
                            entry.getKey()));
        }

        while (mandatesNumber < expectingMandates) {
            QuotientPair temp = priorityQueue.poll();

            mandatesNumber += 1;
            mandates.put(temp.second(), mandates.get(temp.second()) + 1);
        }

        assert mandatesNumber == expectingMandates;
        printCurrentResult(mandates);
        mergeMandate(mandates);
    }
}
