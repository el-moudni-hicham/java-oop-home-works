package ma.enset.exam.dao.entites;

import java.io.Serializable;

public class Medecin implements Serializable {
    private int id_medecin;
    private String nom;
    private String prenom;
    private String email;
    private String tel;

    public Medecin() {
    }

    public Medecin(int id_patient, String nom, String prenom, String email, String tel) {
        this.id_medecin = id_patient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_patient) {
        this.id_medecin = id_patient;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return prenom + " " + nom ;
    }
}
