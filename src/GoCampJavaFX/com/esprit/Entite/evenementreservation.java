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
    private int id_user;
    private int id_evenement;
    private Date date_evenement;
    private String name_evenement,name_user;


    public evenementreservation() {
    }

    public evenementreservation(int id, int id_user, int id_evenement, Date date_evenement) {
        this.id = id;
        this.id_user = id_user;
        this.id_evenement = id_evenement;
        this.date_evenement = date_evenement;
    }

    public evenementreservation(int id, int id_user, int id_evenement, Date date_evenement, String name_evenement, String name_user) {
        this.id = id;
        this.id_user = id_user;
        this.id_evenement = id_evenement;
        this.date_evenement = date_evenement;
        this.name_evenement = name_evenement;
        this.name_user = name_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public String getName_evenement() {
        return name_evenement;
    }

    public void setName_evenement(String name_evenement) {
        this.name_evenement = name_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    
    
    @Override
    public String toString() {
        return "evenementreservation{" + "id=" + id + ", id_user=" + id_user + ", id_evenement=" + id_evenement + ", date_evenement=" + date_evenement + ", name_evenement=" + name_evenement + ", name_user=" + name_user + '}';
    }


    
    
    
}
