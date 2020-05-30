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
    public ArrayList<Integer> getMandates(Constituency constituency) {
        HashMap<String, Integer> votes = new HashMap<>();
        PriorityQueue<QuotientPair> priorityQueue = new PriorityQueue<>();
        int expectingMandates = constituency.getMPNumber();
        int mandates = 0;


        for (Map.Entry<String, Integer> entry
                : constituency.getVotes().entrySet()) {
            votes.put(entry.getKey(), 0);
            priorityQueue.add(
                    new QuotientPair(entry.getValue(), entry.getKey()));
        }


        while (mandates < expectingMandates) {
            QuotientPair temp = priorityQueue.poll();
            votes.put(temp.second(), votes.get(temp.second()) + 1);
            mandates++;

            temp.update(step);
            priorityQueue.add(temp);
        }

        System.out.println(votes);

        return new ArrayList<>();
    }
}
