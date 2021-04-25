/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gocamp.entity;

/**
 *
 * @author HAMMOUDA
 */
public class reservation {
         private int id ;
        private String nbrplace;
        private String approuve ;
        private String utilisateur;

    public reservation() {
    }

    public reservation(int id, String nbrplace, String approuve, String utilisateur) {
        this.id = id;
        this.nbrplace = nbrplace;
        this.approuve = approuve;
        this.utilisateur = utilisateur;
    }

    public reservation(String nbrplace, String approuve, String utilisateur) {
        this.nbrplace = nbrplace;
        this.approuve = approuve;
        this.utilisateur = utilisateur;
    }

    public reservation(String nbrplace, String approuve) {
        this.nbrplace = nbrplace;
        this.approuve = approuve;
    }
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(String nbrplace) {
        this.nbrplace = nbrplace;
    }

    public String getApprouve() {
        return approuve;
    }

    public void setApprouve(String approuve) {
        this.approuve = approuve;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }
        


}
