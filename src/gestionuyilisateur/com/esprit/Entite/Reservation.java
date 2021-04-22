/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuyilisateur.com.esprit.Entite;
import java.util.Date;

/**
 *
 * @author chaima
 */
public class Reservation {
    private int id ;
    private Date date_start ,date_end;
    private int user_id, material_id ;

  

    public Reservation() {
    }

      public Reservation(int id, Date date_start, Date date_end, int user_id, int material_id) {
        this.id = id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.user_id = user_id;
        this.material_id = material_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getdate_start() {
        return date_start;
    }

    public void setdate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getdate_end() {
        return date_end;
    }

    public void setdate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getuser_id() {
        return user_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getmaterial_id() {
        return material_id;
    }

    public void setmaterial_id(int material_id) {
        this.material_id = material_id;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_start=" + date_start + ", date_end=" + date_end + ", user_id=" + user_id + ", material_id=" + material_id + "}\n";
    }

 

}