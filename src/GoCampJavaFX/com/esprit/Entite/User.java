/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Entite;

/**
 *
 * @author Hama Hagui
 */
public class User {

    int idUser;
    String nom;
    String prenom;
    String email;
    String password;
    String role;
    String sexe;


    public User() {
    }
   public User(int idUser) {
        this.idUser = idUser;
    }

    public User(int idUser, String nom, String prenom, String email, String password, String role,String sexe) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.sexe=sexe;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getIdUser() {
        return idUser;
    }
  
    

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public User(String nom, String prenom, String email, String password, String role, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.sexe = sexe;
    }

    public User(String nom, String prenom, String password, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.sexe = sexe;
    }

  

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

  

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", role=" + role +'}';
    }
    
    

}
