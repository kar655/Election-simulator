package elections;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Counts mandates
 */
public abstract class MandateCounter {

    protected final String name;
    protected LinkedHashMap<String, Integer> allMandates = new LinkedHashMap<>();

    public MandateCounter(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Using " + name + " to count number of mandates:";
    }

    // Prints pairs (partyName, gotVotes)
    protected void printCurrentResult(LinkedHashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet())
            System.out.println(
                    "(" + entry.getKey() + ", " + entry.getValue() + ")");
    }

    // Prints all pairs (partyName, gotVotes)
    public void printResult() {
        printCurrentResult(allMandates);
    }

    // Merges currently added mandates with all mandates
    protected void mergeMandate(LinkedHashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (allMandates.containsKey(entry.getKey()))
                allMandates.put(entry.getKey(),
                        allMandates.get(entry.getKey()) + entry.getValue());
            else
                allMandates.put(entry.getKey(), entry.getValue());
        }
    }

    // Calculates mandates for constituency
    public abstract void getMandates(Constituency constituency);
}
