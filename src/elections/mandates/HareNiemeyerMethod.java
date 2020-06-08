package elections.mandates;

import elections.Constituency;

import java.util.*;

public class HareNiemeyerMethod extends MandateCounter {

    public HareNiemeyerMethod() {
        super("Hare-Niemeyer method");
    }

    // Returns numbers after decimal point
    private float rest(float x) {
        return x - (float) Math.floor(x);
    }

    private float calculate(float expectingMandates,
                            float allVotes, float gotVotes) {
        return gotVotes * expectingMandates / allVotes;
    }

    @Override
    public void getMandates(Constituency constituency) {
        PriorityQueue<QuotientPair> priorityQueue = new PriorityQueue<>();
        LinkedHashMap<String, Integer> mandates = new LinkedHashMap<>();
        int expectingMandates = constituency.getMPNumber();
        int allVotes = constituency.getElectorsNumber();
        int mandatesNumber = 0;

        for (Map.Entry<String, Integer> entry
                : constituency.getVotes().entrySet()) {

            int floorMandates = entry.getValue() * expectingMandates / allVotes;
            mandatesNumber += floorMandates;
            mandates.put(entry.getKey(), floorMandates);

            priorityQueue.add(new QuotientPair(
                    rest(calculate(expectingMandates, allVotes, entry.getValue())),
                    entry.getKey()));
        }

        while (mandatesNumber < expectingMandates) {
            QuotientPair temp = priorityQueue.poll();

            mandatesNumber += 1;
            mandates.put(temp.second(), mandates.get(temp.second()) + 1);
        }

        printCurrentResult(mandates);
        mergeMandate(mandates);
    }
}
