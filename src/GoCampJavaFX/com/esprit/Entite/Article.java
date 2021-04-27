/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Entite;

import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class Article {
    private int id_art;
    private String titre_art;
    private String description_art;
    private int likes;
    private int id_cat;
    private String photo;
//    private ImageView photocov;
    private String nomcat; // titre cat
    private int prix;

//    public void makeImage(){
//        this.photocov = new ImageView(new Image(this.getClass().getResourceAsStream(photo)));
////        this.photocov = new ImageView(this.photo);
//        photocov.setFitWidth(300);
//        photocov.setFitWidth(50);
//    }
//
//    public ImageView getPhotocov() {
//        return photocov;
//    }
    
    public int getId_art() {
        return id_art;
    }

    public void setId_art(int id_art) {
        this.id_art = id_art;
    }

    public String getTitre_art() {
        return titre_art;
    }

    public void setTitre_art(String titre_art) {
        this.titre_art = titre_art;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }


    public String getDescription_art() {
        return description_art;
    }

    public void setDescription_art(String description_art) {
        this.description_art = description_art;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    @Override
    public String toString() {
        return "Article{" + "id_art=" + id_art + ", titre_art=" + titre_art + ", description_art=" + description_art + ", likes=" + likes + ", id_cat=" + id_cat + ", photo=" + photo + ", nomcat=" + nomcat + ", prix=" + prix + '}';
    }


    public Article( String titre_art, String description_art, int likes, int id_cat, String photo,int prix, String nomcat) {
        this.titre_art = titre_art;
        this.description_art = description_art;
        this.likes = likes;
        this.id_cat = id_cat;
        this.photo = photo;
        this.prix = prix;
        this.nomcat = nomcat;
    }
    public Article( String titre_art,String description_art, String nomcat, int likes,String photo,String prix) {
        this.titre_art = titre_art;
        this.description_art = description_art;
        this.likes = likes;
        //this.id_cat = id_cat;
        this.photo = photo;
        this.nomcat = nomcat;
    }
    public Article( String titre_art,String description_art, String nomcat, int likes,String photo,int prix,int id_art) {
        this.id_art = id_art;
        this.titre_art = titre_art;
        this.description_art = description_art;
        this.likes = likes;
        //this.id_cat = id_cat;
        this.photo = photo;
        this.prix = prix;
        this.nomcat = nomcat;
    }
    
    public Article(String titre_art, String description_art, String photo,int prix, String nomcat) {
        this.titre_art = titre_art;
        this.description_art = description_art;
        this.photo = photo;
        this.prix = prix;
        this.nomcat = nomcat;
    }
    public Article(String titre_art, String description_art,int likes, String photo,int prix) {
        this.titre_art = titre_art;
        this.description_art = description_art;
        this.likes = likes;
        this.photo = photo;
        this.prix = prix;
    }

    public Article() {
    }
    
    
}

