/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Entite;

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
    private Date dateEnd;
    private String prix_event;
    private String type;
    private String image ; 

    public evenement() {
    }

    public evenement(String nom_event, String description_event, Date date, Date dateEnd, String prix_event, String type, String image) {
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date = date;
        this.dateEnd = dateEnd;
        this.prix_event = prix_event;
        this.type = type;
        this.image = image;
    }

  
 

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public evenement(int id, String nom_event, String description_event, Date date, Date dateEnd, String prix_event, String type, String image) {
        this.id = id;
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date = date;
        this.dateEnd = dateEnd;
        this.prix_event = prix_event;
        this.type = type;
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


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", nom_event=" + nom_event + ", description_event=" + description_event + ", date=" + date + ", dateEnd=" + dateEnd + ", prix_event=" + prix_event + ", type=" + type + ", image=" + image + '}';
    }

  
    
    
}

   
   