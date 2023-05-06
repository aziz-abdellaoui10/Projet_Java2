package Entite;

import java.util.ArrayList;
import java.util.Objects;

public class Branche {

    private int id;
    private String codeBranche;
    private String ville;
    private int banqueId;
    private ArrayList<Credit> credits;
    private ArrayList<Compte> comptes;

    public Branche(int id, String codeBranche, String ville, int banqueId, ArrayList<Compte> comptes, ArrayList<Credit> credits) {
        this.id = id;
        this.codeBranche = codeBranche;
        this.ville = ville;
        this.banqueId = banqueId;
        this.comptes = comptes;
        this.credits = credits;
    }
    public Branche(String codeBranche, String ville, int banqueId, ArrayList<Compte> comptes, ArrayList<Credit> credits) {
        this.codeBranche = codeBranche;
        this.ville = ville;
        this.banqueId = banqueId;
        this.comptes = comptes;
        this.credits=credits;
    }

    public Branche(String codeBranche, String ville, int banqueId) {
        this.codeBranche = codeBranche;
        this.ville = ville;
        this.banqueId = banqueId;
    }

    public Branche() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeBranche() {
        return codeBranche;
    }

    public void setCodeBranche(String codeBranche) {
        this.codeBranche = codeBranche;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getBanqueId() {
        return banqueId;
    }

    public void setBanqueId(int banqueId) {
        this.banqueId = banqueId;
    }

    public ArrayList<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(ArrayList<Compte> comptes) {
        this.comptes = comptes;
    }

    public ArrayList<Credit> getCredits() {
        return credits;
    }

    public void setCredits(ArrayList<Credit> credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branche branche = (Branche) o;
        return id == branche.id && banqueId == branche.banqueId && Objects.equals(codeBranche, branche.codeBranche) && Objects.equals(ville, branche.ville) && Objects.equals(credits, branche.credits) && Objects.equals(comptes, branche.comptes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codeBranche, ville, banqueId, credits, comptes);
    }

    @Override
    public String toString() {
        return "Branche{" +
                "id=" + id +
                ", codeBranche='" + codeBranche + '\'' +
                ", ville='" + ville + '\'' +
                ", banqueId=" + banqueId +
                ", credits=" + credits +
                ", comptes=" + comptes +
                '}';
    }
}