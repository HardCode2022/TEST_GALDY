package controller;

import entity.CompteUtilisateur;
import exception.CarteExpirationException;
import service.TraitementSoldeCadeauService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TraitementSoldeEtCadeauController {

    private TraitementSoldeCadeauService TraitementSoldeCadeauService;

    public TraitementSoldeEtCadeauController() {
        this.TraitementSoldeCadeauService = new TraitementSoldeCadeauService();
    }

    public void calculeSolde() throws ParseException, CarteExpirationException {
        Scanner scanner = new Scanner(System.in);
        CompteUtilisateur utilisateur = new CompteUtilisateur();
        System.out.println("Veuillez saisir le nom de l'utilisateur beneficiaire:");
        utilisateur.setNom( scanner.nextLine());

        System.out.println("Veuillez saisir le prenom de l'utilisateur beneficiaire:");
        utilisateur.setPrenom( scanner.nextLine());

        System.out.println("Veuillez saisir la date de versement de la  carte cadeau au format:dd/MM/yyyy");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = scanner.nextLine();
        Date dateInstance = formatDate.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateInstance);
        Date dateversementCadeau=cal.getTime();
        utilisateur.setDateVersementCadeau(dateversementCadeau);

        System.out.println("Veuillez saisir la date de versement du ticket restaurant au format:dd/MM/yyyy");
        String dateTicket = scanner.nextLine();
        Date dateInstanceTicket = formatDate.parse(dateTicket);
        cal.setTime(dateInstanceTicket);
        Date dateversementTicket=cal.getTime();
        utilisateur.setDateversementTicketRestaurant(dateversementTicket);

        System.out.println("Veuillez saisir la valeur de votre carte cadeau pour l'utilisateur:");
        utilisateur.setCarteCadeau(scanner.nextDouble());
        System.out.println("Veuillez saisir la valeur de depot pour le ticket restaurant:");
        utilisateur.setTicketRestaurant(scanner.nextDouble());

        double soldeCadeau = TraitementSoldeCadeauService.calculsoldeCadeau(utilisateur);
        double soldeTicket = TraitementSoldeCadeauService.calculValeurToutTicketRestaurant(utilisateur);

        double soldeToutCompte=soldeTicket+soldeCadeau;

        System.out.println("Le solde de la carte pour l'utilisateur " + utilisateur.getNom() + "  " +  utilisateur.getPrenom() + " est de : " + soldeToutCompte) ;
    }
}
