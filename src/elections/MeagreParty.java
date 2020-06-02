package elections;


public class MeagreParty extends FinanceParty {

    public MeagreParty(String name, int budget) {
        super(name, budget, Integer.MAX_VALUE);
    }

    // Looking for smaller price
    @Override
    protected boolean compare(int tempPrice, int price) {
        return tempPrice < price;
    }
}
