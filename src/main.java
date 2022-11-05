import controller.TraitementSoldeEtCadeauController;
import exception.CarteExpirationException;

import java.text.ParseException;

public class main {

    public static void main(String[] args) throws ParseException, CarteExpirationException {
        //TODO:Lancer l'application et laisser vous guider, saisisez les champs pour determiner les differents soldes
        TraitementSoldeEtCadeauController traitementSoldeEtCadeauController = new TraitementSoldeEtCadeauController();
        traitementSoldeEtCadeauController.calculeSolde();
    }
}
