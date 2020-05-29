package elections;

import java.util.ArrayList;

public interface IMandates {

    ArrayList<Integer> getMandates(Constituency constituency);

    // TODO sumowanie glosow w obszarach z podzialami na partie
    //
    // dla obszarow musze znac ilosc glosow dla kazdej z partii
}
