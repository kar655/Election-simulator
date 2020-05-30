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
    public ArrayList<Integer> getMandates(Constituency constituency) {
        HashMap<String, Integer> votes = new HashMap<>();
        PriorityQueue<QuotientPair> priorityQueue = new PriorityQueue<>();
        int expectingMandates = constituency.getMPNumber();
        int allVotes = constituency.getElectorsNumber();
        int mandates = 0;

        for (Map.Entry<String, Integer> entry
                : constituency.getVotes().entrySet()) {

            // todo expectingMandates / allVotes === 1/10
            int floorMandates = entry.getValue() * expectingMandates / allVotes;
            mandates += floorMandates;
            votes.put(entry.getKey(), floorMandates);

            priorityQueue.add(
                    new QuotientPair(
                            rest(calculate(expectingMandates, allVotes, entry.getValue())),
                            entry.getKey()));
        }

        while (mandates < expectingMandates) {
            QuotientPair temp = priorityQueue.poll();

            mandates += 1;
            votes.put(temp.second(), votes.get(temp.second()) + 1);
        }


        assert mandates == expectingMandates;

        System.out.println(votes);
        return null;
    }
}
