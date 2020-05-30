package elections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class MandateCounter {

    protected String name;
    // todo poprawic jak bylo wczesniej tylko jakies temporary mandates
    //  i jakies dodawanie do tych aktualnych
    protected HashMap<String, Integer> allMandates = new HashMap<>();

    public MandateCounter(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Using " + name + " to count number of mandates:";
    }

    public void printCurrentResult(HashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet())
            System.out.println(
                    "(" + entry.getKey() + ", " + entry.getValue() + ")");
    }

    public void printResult() {
        printCurrentResult(allMandates);
    }

    protected void mergeMandate(HashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (allMandates.containsKey(entry.getKey()))
                allMandates.put(entry.getKey(),
                        allMandates.get(entry.getKey()) + entry.getValue());
            else
                allMandates.put(entry.getKey(), entry.getValue());
        }
    }

    public abstract void getMandates(Constituency constituency);
}
