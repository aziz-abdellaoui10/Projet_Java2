package Entite;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Banque {
    private int id;
    private String nom;
    private String code;
    private ArrayList<Branche> branches;

    public Banque() {
        super();
    }

    public Banque(String nom, String code, ArrayList<Branche> branches) {
        this.nom = nom;
        this.code = code;
        this.branches = branches;
    }

    public Banque(int id, String nom, String code, ArrayList<Branche> branches) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.branches = branches;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Branche> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branche> branches) {
        this.branches = branches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banque banque = (Banque) o;
        return id == banque.id && Objects.equals(nom, banque.nom) && Objects.equals(code, banque.code) && Objects.equals(branches, banque.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, code, branches);
    }

    @Override
    public String toString() {
        return "Banque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", code='" + code + '\'' +
                ", branches=" + branches +
                '}';
    }
}
