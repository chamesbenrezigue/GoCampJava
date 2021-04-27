/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Entite;

/**
 *
 * @author ASUS
 */
public class Categorie {
    private int id_cat;
    private String titre_cat;

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getTitre_cat() {
        return titre_cat;
    }

    public void setTitre_cat(String titre_cat) {
        this.titre_cat = titre_cat;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_cat=" + id_cat + ", titre_cat=" + titre_cat + '}';
    }

    public Categorie(int id_cat, String titre_cat) {
        this.id_cat = id_cat;
        this.titre_cat = titre_cat;
    }

    public Categorie(String titre_cat) {
        this.titre_cat = titre_cat;
    }
    
    
}
