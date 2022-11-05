package test;

import entity.CompteUtilisateur;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class testUtils {

    public static CompteUtilisateur genererUtilisateur() throws ParseException {
        CompteUtilisateur utilisateur = new CompteUtilisateur();
        String dateversement="05/11/2022";
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        Date dateInstance = formatDate.parse(dateversement);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateInstance);
        utilisateur.setCarteCadeau(100d);
        utilisateur.setTicketRestaurant(50d);
        utilisateur.setDateVersementCadeau(cal.getTime());
        utilisateur.setDateversementTicketRestaurant(cal.getTime());
         return  utilisateur;
    }
   public static CompteUtilisateur genererUtilisateurAvecExpiration() throws ParseException {
    CompteUtilisateur utilisateur = new CompteUtilisateur();
    String dateversement="05/11/2020";
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    Date dateInstance = formatDate.parse(dateversement);
    Calendar cal = Calendar.getInstance();
    cal.setTime(dateInstance);
    utilisateur.setCarteCadeau(100d);
    utilisateur.setTicketRestaurant(50d);
    utilisateur.setDateVersementCadeau(cal.getTime());
    utilisateur.setDateversementTicketRestaurant(cal.getTime());
    return utilisateur;
}
}
