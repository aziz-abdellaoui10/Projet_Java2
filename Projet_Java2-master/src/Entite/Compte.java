package Entite;

import java.util.Date;
import java.util.Objects;

public class Compte {
    private int id;
    private double solde;
    private double decouvert;
    private double MaxDecouvert;
    private double MaxRetrait;
    private double resteCredit;
    private double paiementCreditParMois;
    private String type;
    private Date ouverture;
    int clientId;


    public Compte(double solde, double maxDecouvert, double maxRetrait, String type, Date ouverture, int clientId) {
        this.solde = solde;
        this.MaxDecouvert = maxDecouvert;
        this.MaxRetrait = maxRetrait;
        this.paiementCreditParMois = 0;
        this.resteCredit = 0;
        this.decouvert = 0;
        this.clientId = clientId;
        this.type = type;
        this.ouverture = ouverture;
    }

    public Compte(int id, double solde, double maxDecouvert, double maxRetrait, String type, Date ouverture, int clientId) {
        this.id = id;
        this.solde = solde;
        this.MaxDecouvert = maxDecouvert;
        this.MaxRetrait = maxRetrait;
        this.paiementCreditParMois = 0;
        this.resteCredit = 0;
        this.decouvert = 0;
        this.clientId = clientId;
        this.type = type;
        this.ouverture = ouverture;
    }

    public Compte() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    public double getMaxDecouvert() {
        return MaxDecouvert;
    }

    public void setMaxDecouvert(double maxDecouvert) {
        MaxDecouvert = maxDecouvert;
    }

    public double getMaxRetrait() {
        return MaxRetrait;
    }

    public void setMaxRetrait(double maxRetrait) {
        MaxRetrait = maxRetrait;
    }

    public double getResteCredit() {
        return resteCredit;
    }

    public void setResteCredit(double resteCredit) {
        this.resteCredit = resteCredit;
    }

    public double getPaiementCreditParMois() {
        return paiementCreditParMois;
    }

    public void setPaiementCreditParMois(double paiementCreditParMois) {
        this.paiementCreditParMois = paiementCreditParMois;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getOuverture() {
        return ouverture;
    }

    public void setOuverture(Date ouverture) {
        this.ouverture = ouverture;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return id == compte.id && Double.compare(compte.solde, solde) == 0 && Double.compare(compte.decouvert, decouvert) == 0 && Double.compare(compte.MaxDecouvert, MaxDecouvert) == 0 && Double.compare(compte.MaxRetrait, MaxRetrait) == 0 && Double.compare(compte.resteCredit, resteCredit) == 0 && Double.compare(compte.paiementCreditParMois, paiementCreditParMois) == 0 && clientId == compte.clientId && Objects.equals(type, compte.type) && Objects.equals(ouverture, compte.ouverture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, solde, decouvert, MaxDecouvert, MaxRetrait, resteCredit, paiementCreditParMois, type, ouverture, clientId);
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", solde=" + solde +
                ", decouvert=" + decouvert +
                ", MaxDecouvert=" + MaxDecouvert +
                ", MaxRetrait=" + MaxRetrait +
                ", resteCredit=" + resteCredit +
                ", paiementCreditParMois=" + paiementCreditParMois +
                ", type='" + type + '\'' +
                ", ouverture=" + ouverture +
                ", clientId=" + clientId +
                '}';
    }
}

