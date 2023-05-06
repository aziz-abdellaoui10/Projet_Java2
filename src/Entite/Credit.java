package Entite;

import java.util.Objects;

public class Credit {
    private int id;
    private Double montant;
    private Double nbrAnnee;
    private int idCompte;

    private String isApproved;

    public Credit() {
        super();
    }

    public Credit(Double montant, Double nbrAnnee, int idCompte) {
        this.nbrAnnee = nbrAnnee;
        this.montant = montant;
        this.idCompte = idCompte;
        this.isApproved = "N/A";
    }

    public Credit(int id, Double nbrAnnee, Double montant, int idCompte, String isApproved) {
        this.id = id;
        this.nbrAnnee = nbrAnnee;
        this.montant = montant;
        this.idCompte = idCompte;
        this.isApproved = isApproved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public Double getNbrAnnee() {
        return nbrAnnee;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String approved) {
        isApproved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return id == credit.id && idCompte == credit.idCompte && isApproved == credit.isApproved && Objects.equals(montant, credit.montant) && Objects.equals(nbrAnnee, credit.nbrAnnee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, montant, nbrAnnee, idCompte, isApproved);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", montant=" + montant +
                ", nbrAnnee=" + nbrAnnee +
                ", idCompte=" + idCompte +
                ", isApproved=" + isApproved +
                '}';
    }
}
