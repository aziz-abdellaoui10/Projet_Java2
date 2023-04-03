package Entite;

import java.util.Objects;

public class Credit {
    private int id;
    private int nCredit;
    private Double montant;
    private String Type;
    private int clientId;

    public Credit() {
        super();
    }

    public Credit(int nCredit, Double montant, String type, int clientId) {
        this.nCredit = nCredit;
        this.montant = montant;
        Type = type;
        this.clientId = clientId;
    }

    public Credit(int id, int nCredit, Double montant, String type, int clientId) {
        this.id = id;
        this.nCredit = nCredit;
        this.montant = montant;
        Type = type;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnCredit() {
        return nCredit;
    }

    public void setnCredit(int nCredit) {
        this.nCredit = nCredit;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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
        return id == credit.id && nCredit == credit.nCredit && clientId == credit.clientId && Objects.equals(montant, credit.montant) && Objects.equals(Type, credit.Type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nCredit, montant, Type, clientId);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", nCredit=" + nCredit +
                ", montant=" + montant +
                ", Type='" + Type + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
