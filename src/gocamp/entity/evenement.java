/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gocamp.entity;

import java.sql.Date;

/**
 *
 * @author HAMMOUDA
 */
public class evenement {
    private int id ;
    private String nom_event;
    private String description_event;
    private Date date;
    private String prix_event;
    private String nbr_place;
    private String image ; 

    public evenement() {
    }

    public evenement(String nom_event, String description_event, Date date, String prix_event, String nbr_place, String image) {
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date = date;
        this.prix_event = prix_event;
        this.nbr_place = nbr_place;
        this.image = image;
    }

    public evenement(int id, String nom_event, String description_event, Date date, String prix_event, String nbr_place, String image) {
        this.id = id;
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date = date;
        this.prix_event = prix_event;
        this.nbr_place = nbr_place;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrix_event() {
        return prix_event;
    }

    public void setPrix_event(String prix_event) {
        this.prix_event = prix_event;
    }

    public String getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(String nbr_place) {
        this.nbr_place = nbr_place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", nom_event=" + nom_event + ", description_event=" + description_event + ", date=" + date + ", prix_event=" + prix_event + ", nbr_place=" + nbr_place + ", image=" + image + '}';
    }
    
    
}

   
   