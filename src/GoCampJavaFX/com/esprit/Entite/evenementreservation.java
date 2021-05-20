/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Entite;

import java.sql.Date;

/**
 *
 * @author Rezigue
 */
public class evenementreservation {
    private int id ;
    private String nom;
    private String prenom;
    private String event;
    private String nbrplace; 
    private String name_evenement,name_user;


    public evenementreservation() {
    }

    public evenementreservation(int id, String nom, String prenom, String event, String nbrplace, String name_evenement, String name_user) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.event = event;
        this.nbrplace = nbrplace;
        this.name_evenement = name_evenement;
        this.name_user = name_user;
    }

    public evenementreservation(String nom, String prenom, String event, String nbrplace, String name_evenement, String name_user) {
        this.nom = nom;
        this.prenom = prenom;
        this.event = event;
        this.nbrplace = nbrplace;
        this.name_evenement = name_evenement;
        this.name_user = name_user;
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(String nbrplace) {
        this.nbrplace = nbrplace;
    }

    public String getName_evenement() {
        return name_evenement;
    }

    public void setName_evenement(String name_evenement) {
        this.name_evenement = name_evenement;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    @Override
    public String toString() {
        return "evenementreservation{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", event=" + event + ", nbrplace=" + nbrplace + ", name_evenement=" + name_evenement + ", name_user=" + name_user + '}';
    }



    
    
    
}
