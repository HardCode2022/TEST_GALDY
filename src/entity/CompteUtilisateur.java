package entity;

import java.util.Date;

/**
 * Entité Utilisateur
 */
public class CompteUtilisateur {

    private String nom;
    private String prenom;
    private  double carteCadeau;
    private double ticketRestaurant;
    private Date dateVersementCadeau;
    private Date dateversementTicketRestaurant;

    public CompteUtilisateur() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getCarteCadeau() {
        return carteCadeau;
    }

    public void setCarteCadeau(double carteCadeau) {
        this.carteCadeau = carteCadeau;
    }

    public double getTicketRestaurant() {
        return ticketRestaurant;
    }

    public void setTicketRestaurant(double ticketRestaurant) {
        this.ticketRestaurant = ticketRestaurant;
    }

    public Date getDateVersementCadeau() {
        return dateVersementCadeau;
    }

    public void setDateVersementCadeau(Date dateVersementCadeau) {
        this.dateVersementCadeau = dateVersementCadeau;
    }

    public Date getDateversementTicketRestaurant() {
        return dateversementTicketRestaurant;
    }

    public void setDateversementTicketRestaurant(Date dateversementTicketRestaurant) {
        this.dateversementTicketRestaurant = dateversementTicketRestaurant;
    }
}
