/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Entite;

/**
 *
 * @author chaima
 */
public class Reservation {
    private int id ;
    private String date_start ,date_end;
    private int user_id, material_id ;
    private String name_material,name_user;

  

    public Reservation() {
    }
    public Reservation(int id, String date_start, String date_end, int user_id, int material_id, String name_material, String name_user) {
        this.id = id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.user_id = user_id;
        this.material_id = material_id;
        this.name_material = name_material;
        this.name_user = name_user;
    }
      public Reservation(int id, String date_start, String date_end, int user_id, int material_id, String name_material) {
        this.id = id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.user_id = user_id;
        this.material_id = material_id;
        this.name_material = name_material;
    }
      public Reservation(int id, String date_start, String date_end, int user_id, int material_id) {
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

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName_material() {
        return name_material;
    }

    public void setName_material(String name_material) {
        this.name_material = name_material;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_start=" + date_start + ", date_end=" + date_end + ", user_id=" + user_id + ", material_id=" + material_id + ", name_material=" + name_material + ", name_user=" + name_user + '}';
    }


   


 

}