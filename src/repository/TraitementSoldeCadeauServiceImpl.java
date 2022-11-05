package repository;

import entity.CompteUtilisateur;
import exception.CarteExpirationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Implementation des traitements pour le calcul du solde et la valeur des cadeaux
 */
public class TraitementSoldeCadeauServiceImpl {

    private static String pattern="dd/MM/yyyy";

    private static Logger LOGGER =Logger.getLogger(String.valueOf(TraitementSoldeCadeauServiceImpl.class));

    /**
     * Determination du solde cadeau
     * @param utilisateur
     * @return
     * @throws ParseException
     * @throws CarteExpirationException
     */
    public Double calculSoldeCadeau(CompteUtilisateur utilisateur) throws ParseException, CarteExpirationException {
        LOGGER.info("Entrez dans le traitement solde cadeau");
        double soldeUtilisateur=0d;
        if (utilisateur.getCarteCadeau()!=0d){
            Date dateExpiration = determinerdateExpirationCadeau(utilisateur.getDateVersementCadeau());
            Date datePresent=dateJoursimpleFormat();
            if (datePresent.after(dateExpiration)){
                throw new CarteExpirationException("Attention, la valeur du solde cadeau n'est plus comptabilsé");
            }else {
                soldeUtilisateur=utilisateur.getCarteCadeau();
            }
        }
        return soldeUtilisateur;
    }

    /**
     * Determination du solde Ticket restaurant
     * @param utilisateur
     * @return
     * @throws ParseException
     * @throws CarteExpirationException
     */
    public Double calculSoldeTicketRestaurant(CompteUtilisateur utilisateur) throws ParseException,CarteExpirationException {
        LOGGER.info("Entrez dans le traitement du solde ticket restaurant");
        double soldeTicketrestaurant=0d;
        if (utilisateur.getTicketRestaurant()!=0){
          Date dateExpirationTicket = determinerdateExpirationSoldeTicket(utilisateur.getDateversementTicketRestaurant());
          Date datePresent=dateJoursimpleFormat();
                if (datePresent.after(dateExpirationTicket)){
                    throw new CarteExpirationException("Attention, la valeur du solde ticket n'est plus comptabilisé");
                }else {
                    soldeTicketrestaurant=utilisateur.getTicketRestaurant();
                }
            }
        return soldeTicketrestaurant;
    }

    /**
     * Determination de la date d'expiration de la carte en fonction de la reception du montant en "DD/MM/YYYY"
     * @param dateSaisie
     * @return
     * @throws ParseException
     */
    private Date determinerdateExpirationCadeau(Date dateSaisie) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
        String date = formatDate.format(dateSaisie);
        int nombresJrsAvantExpiration = 365;
        Date instanceDate = formatDate.parse(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(instanceDate);
        cal.add(Calendar.DATE,+ nombresJrsAvantExpiration-1);
        return cal.getTime();
    }

    /**
     *Determination de la date d'xpiration du solde ticket restaurant
     * @param dateSaisie
     * @return
     * @throws ParseException
     */
    private Date determinerdateExpirationSoldeTicket(Date dateSaisie) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
        String date = formatDate.format(dateSaisie);
        Date instanceDate = formatDate.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanceDate);
        cal.set(cal.get(Calendar.YEAR)+1,Calendar.FEBRUARY,28);
        return cal.getTime();
    }

    /**
     * Determination de la date du jour au format "dd/MM/YYYY"
     * @return
     * @throws ParseException
     */
    private Date dateJoursimpleFormat() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String date = dateFormat.format(new Date());
        Date dateInstance = dateFormat.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateInstance);

        return cal.getTime();
    }
}
