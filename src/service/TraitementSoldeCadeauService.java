package service;

import Interface.traitementRepository;
import com.sun.istack.logging.Logger;
import entity.CompteUtilisateur;
import exception.CarteExpirationException;
import repository.TraitementSoldeCadeauServiceImpl;

import java.text.ParseException;


public class TraitementSoldeCadeauService {

    private static Logger LOGGER = Logger.getLogger(TraitementSoldeCadeauService.class);

    private traitementRepository repository;

    public TraitementSoldeCadeauService( ) {
        this.repository = new  TraitementSoldeCadeauServiceImpl();
    }

    /**
     * Service de cacul du solde carte cadeau
     * @param utilisateur
     * @return
     * @throws ParseException
     * @throws CarteExpirationException
     */
    public double calculsoldeCadeau(CompteUtilisateur utilisateur) throws ParseException {
        double calculDeSolde = 0D;
        try {
            calculDeSolde = repository.calculSoldeCadeau(utilisateur);
        } catch (CarteExpirationException e) {
            LOGGER.info(e.getMessage());
        }
        return calculDeSolde;
    }

    /**
     * Service de calcul de la valeur du carte ticket restaurant
     * @param utilisateur
     * @return
     * @throws ParseException
     * @throws CarteExpirationException
     */
    public double calculValeurToutTicketRestaurant(CompteUtilisateur utilisateur) throws ParseException {
        double calculDeSoldeToutCompte = 0D;
        try {
            calculDeSoldeToutCompte = repository.calculSoldeTicketRestaurant( utilisateur);
        } catch (CarteExpirationException e) {
            LOGGER.info(e.getMessage());
        }
        return calculDeSoldeToutCompte;
    }
}
