package Entite;

import java.util.Objects;

public class Client {

    private int id;
    private String nom;
    private String prenom;
    private String adresse;

    public Client(){super();};

    public Client(int id, String nom, String prenom, String adresse){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
    }

    public Client(String nom, String prenom, String adresse){
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(nom, client.nom) && Objects.equals(prenom, client.prenom) && Objects.equals(adresse, client.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, adresse);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
