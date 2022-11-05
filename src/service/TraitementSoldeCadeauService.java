package service;

import entity.CompteUtilisateur;
import exception.CarteExpirationException;
import repository.TraitementSoldeCadeauServiceImpl;

import java.text.ParseException;
import java.util.logging.Logger;

public class TraitementSoldeCadeauService {

    private static Logger LOGGER =Logger.getLogger(String.valueOf(TraitementSoldeCadeauService.class));

    private TraitementSoldeCadeauServiceImpl repository;

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
    public double calculsoldeCadeau(CompteUtilisateur utilisateur) throws ParseException, CarteExpirationException {
        double calculDeSolde = 0;
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
    public double calculValeurToutTicketRestaurant(CompteUtilisateur utilisateur) throws ParseException, CarteExpirationException {
        double calculDeSoldeToutCompte = 0;
        try {
            calculDeSoldeToutCompte = repository.calculSoldeTicketRestaurant( utilisateur);
        } catch (CarteExpirationException e) {
            LOGGER.info(e.getMessage());
        }
        return calculDeSoldeToutCompte;
    }
}
