package test;

import Interface.traitementRepository;
import entity.CompteUtilisateur;
import exception.CarteExpirationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import repository.TraitementSoldeCadeauServiceImpl;

import java.text.ParseException;

class TraitementSoldeCadeauServiceImplTest {

    private static traitementRepository traitementSoldeCadeauService;

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
                ()->Assertions.assertEquals(100.0,solde)
        );
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Test: Verification solde cadeau avec expiration")
    void calculSoldeAvecExpiration()  {
        CarteExpirationException exception = Assertions.assertThrows(CarteExpirationException.class, () -> {
            double solde=traitementSoldeCadeauService.calculSoldeCadeau(testUtils.genererUtilisateurAvecExpiration());
            Assertions.assertAll(
                    ()->Assertions.assertEquals(0.0,solde)
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
                ()->Assertions.assertEquals(50.0,solde)
        );
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Test: Verification du solde Ticket avec expiration")
    void calculSoldeAvecExpirationTicket() {
        CarteExpirationException exception = Assertions.assertThrows(CarteExpirationException.class, () -> {
            double solde=traitementSoldeCadeauService.calculSoldeTicketRestaurant(testUtils.genererUtilisateurAvecExpiration());
            Assertions.assertAll(
                    ()->Assertions.assertEquals(0.0,solde)
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
                ()->Assertions.assertEquals(100.0,soldeCadeau),
                ()->Assertions.assertNotNull(soldeTicket),
                ()->Assertions.assertEquals(50.0,soldeTicket),
                ()->Assertions.assertEquals(150.0,soldeTicket+soldeCadeau)
        );
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Test: Verification solde tout compte Avec expiration sur ticket")
    void calculSoldeToutCompteAvecExpiration() throws CarteExpirationException, ParseException {
        double soldeCadeau=traitementSoldeCadeauService.calculSoldeCadeau(utilisateur);
        CarteExpirationException exception = Assertions.assertThrows(CarteExpirationException.class, () -> {
            double soldeTicket=traitementSoldeCadeauService.calculSoldeTicketRestaurant(testUtils.genererUtilisateurAvecExpiration());
            Assertions.assertAll(
                    ()->Assertions.assertEquals(100.0,soldeCadeau),
                    ()->Assertions.assertEquals(0.0,soldeTicket),
                    ()->Assertions.assertEquals(100.0,soldeTicket+soldeCadeau)
            );
        });
        Assertions.assertEquals("Attention, la valeur du solde ticket n'est plus comptabilisé", exception.getMessage());
    }
}