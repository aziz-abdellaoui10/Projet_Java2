package Entite;

import java.util.Objects;

public class Credit {
    private int id;
    private Double montant;
    private Double nbrAnnee;
    private int clientId;

    public Credit() {
        super();
    }

    public Credit(Double montant, Double nbrAnnee, int clientId) {
        this.nbrAnnee = nbrAnnee;
        this.montant = montant;
        this.clientId = clientId;
    }

    public Credit(int id, Double nbrAnnee, Double montant, int clientId) {
        this.id = id;
        this.nbrAnnee = nbrAnnee;
        this.montant = montant;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getnbrAnnee() {
        return nbrAnnee;
    }

    public void setNbrAnnee(Double nbrAnnee) {
        this.nbrAnnee = nbrAnnee;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
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
        Credit credit = (Credit) o;
        return id == credit.id && nbrAnnee == credit.nbrAnnee && clientId == credit.clientId && Objects.equals(montant, credit.montant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nbrAnnee, montant, clientId);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", nbrAnnee=" + nbrAnnee +
                ", montant=" + montant +
                ", clientId=" + clientId +
                '}';
    }
}
