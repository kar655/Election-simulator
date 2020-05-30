package elections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public abstract class QuotientMethod extends MandateCounter {

    protected final int step;

    public QuotientMethod(String name, int step) {
        super(name);
        this.step = step;
    }

    @Override
    public void getMandates(Constituency constituency) {
        PriorityQueue<QuotientPair> priorityQueue = new PriorityQueue<>();
        HashMap<String, Integer> mandates = new HashMap<>();
        int expectingMandates = constituency.getMPNumber();
        int mandatesNumber = 0;


        for (Map.Entry<String, Integer> entry
                : constituency.getVotes().entrySet()) {
            mandates.put(entry.getKey(), 0);
            priorityQueue.add(
                    new QuotientPair(entry.getValue(), entry.getKey()));
        }


        while (mandatesNumber < expectingMandates) {
            QuotientPair temp = priorityQueue.poll();
            mandates.put(temp.second(), mandates.get(temp.second()) + 1);
            mandatesNumber++;

            temp.update(step);
            priorityQueue.add(temp);
        }

        assert mandatesNumber == expectingMandates;
        printCurrentResult(mandates);
        mergeMandate(mandates);
    }
}
