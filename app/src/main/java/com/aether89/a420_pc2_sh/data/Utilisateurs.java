package com.aether89.a420_pc2_sh.data;

import java.util.Objects;

public class Utilisateurs {

    private String nomFamille;
    private String prenom;
    private String courriel;

    public Utilisateurs(String prenom, String nomFamille, String courriel) {
        this.prenom = prenom;
        this.nomFamille = nomFamille;
        this.courriel = courriel;
    }

    public String getNomComplet() {
        return this.prenom + " " + this.nomFamille;
    }

    public String getCourriel() {
        return this.courriel;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    public void setNomComplet(String prenom, String nomFamille) {
        this.prenom = prenom;
        this.nomFamille = nomFamille;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Utilisateurs other = (Utilisateurs) obj;
        return Objects.equals(nomFamille, other.nomFamille) &&
                Objects.equals(prenom, other.prenom) &&
                Objects.equals(courriel, other.courriel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomFamille, prenom, courriel);
    }
}

