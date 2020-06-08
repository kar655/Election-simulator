package elections.parties;


public class GenerousParty extends FinanceParty {

    public GenerousParty(String name, int budget) {
        super(name, budget, 0);
    }

    // Looking for bigger price
    @Override
    protected boolean compare(int tempPrice, int price) {
        return tempPrice > price;
    }
}
