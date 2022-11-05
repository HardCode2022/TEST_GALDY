package test;

import entity.CompteUtilisateur;
import exception.CarteExpirationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import repository.TraitementSoldeCadeauServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class TraitementSoldeCadeauServiceImplTest {

    private static TraitementSoldeCadeauServiceImpl traitementSoldeCadeauService;

    private CompteUtilisateur utilisateur;

    @BeforeEach
    public void beforeEach() throws ParseException {
        utilisateur=testUtils.genererUtilisateur();
        traitementSoldeCadeauService= new TraitementSoldeCadeauServiceImpl();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Test: Verification solde cadeau sans expiration")
    void calculSolde() throws ParseException, CarteExpirationException {
        double solde=traitementSoldeCadeauService.calculSoldeCadeau(utilisateur);
        Assertions.assertAll(
                ()->Assertions.assertNotNull(solde),
                ()->Assertions.assertEquals(solde,100.0)
        );
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Test: Verification solde cadeau avec expiration")
    void calculSoldeAvecExpiration()  {
        CarteExpirationException exception = Assertions.assertThrows(CarteExpirationException.class, () -> {
            double solde=traitementSoldeCadeauService.calculSoldeCadeau(testUtils.genererUtilisateurAvecExpiration());
            Assertions.assertAll(
                    ()->Assertions.assertEquals(solde,0.0)
            );
        });
        Assertions.assertEquals("Attention, la valeur du solde cadeau n'est plus comptabilsé", exception.getMessage());
    }


    @org.junit.jupiter.api.Test
    @DisplayName("Test: Verification solde ticket sans expiration")
    void calculSoldeTicketRestaurant() throws CarteExpirationException, ParseException {
        double solde=traitementSoldeCadeauService.calculSoldeTicketRestaurant(utilisateur);
        Assertions.assertAll(
                ()->Assertions.assertNotNull(solde),
                ()->Assertions.assertEquals(solde,50.0)
        );
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Test: Verification du solde Ticket avec expiration")
    void calculSoldeAvecExpirationTicket() {
        CarteExpirationException exception = Assertions.assertThrows(CarteExpirationException.class, () -> {
            double solde=traitementSoldeCadeauService.calculSoldeTicketRestaurant(testUtils.genererUtilisateurAvecExpiration());
            Assertions.assertAll(
                    ()->Assertions.assertEquals(solde,0.0)
            );
        });
        Assertions.assertEquals("Attention, la valeur du solde ticket n'est plus comptabilisé", exception.getMessage());
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Test: Verification solde tout compte Sans expiration")
    void calculSoldeToutCompteSansExpiration() throws CarteExpirationException, ParseException {
        double soldeCadeau=traitementSoldeCadeauService.calculSoldeCadeau(utilisateur);
        double soldeTicket=traitementSoldeCadeauService.calculSoldeTicketRestaurant(utilisateur);
        Assertions.assertAll(
                ()->Assertions.assertNotNull(soldeCadeau),
                ()->Assertions.assertEquals(soldeCadeau,100.0),
                ()->Assertions.assertNotNull(soldeTicket),
                ()->Assertions.assertEquals(soldeTicket,50.0),
                ()->Assertions.assertEquals(soldeTicket+soldeCadeau,150.0)
        );
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Test: Verification solde tout compte Avec expiration sur ticket")
    void calculSoldeToutCompteAvecExpiration() throws CarteExpirationException, ParseException {
        double soldeCadeau=traitementSoldeCadeauService.calculSoldeCadeau(utilisateur);
        CarteExpirationException exception = Assertions.assertThrows(CarteExpirationException.class, () -> {
            double soldeTicket=traitementSoldeCadeauService.calculSoldeTicketRestaurant(testUtils.genererUtilisateurAvecExpiration());
            Assertions.assertAll(
                    ()->Assertions.assertEquals(soldeCadeau,100.0),
                    ()->Assertions.assertEquals(soldeTicket,0.0),
                    ()->Assertions.assertEquals(soldeTicket+soldeCadeau,100.0)
            );
        });
        Assertions.assertEquals("Attention, la valeur du solde ticket n'est plus comptabilisé", exception.getMessage());
    }
}