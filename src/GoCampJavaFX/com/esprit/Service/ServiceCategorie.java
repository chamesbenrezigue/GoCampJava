/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Service;

import GoCampJavaFX.com.esprit.IService.IService;
import GoCampJavaFX.com.esprit.Entite.Categorie;
import GoCampJavaFX.com.esprit.Util.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ServiceCategorie implements IService<Categorie>{
    Connection cnx = DataBase.getInstance().getConnection();
    @Override
    public void ajouter(Categorie t) {
        try {
            String req = "INSERT INTO categorie (id_cat,titre_cat) VALUES  (?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId_cat());
            pst.setString(2, t.getTitre_cat());
            pst.executeUpdate();
            System.out.println("Catégorie ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Categorie t) {
        try {
            String req = "DELETE FROM categorie WHERE titre_cat=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, t.getTitre_cat());
            pst.executeUpdate();
            System.out.println("Catégorie supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public void supprimerCatById(int id_cat) {
        try {
            String req = "DELETE FROM categorie WHERE id_cat=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id_cat);
            pst.executeUpdate();
             System.out.println("Cat_id " + id_cat +":" + " supprimé !");


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> afficher() {
       List<Categorie> list = new ArrayList<>();

        try {
            String requete = "SELECT titre_cat FROM categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Categorie(rs.getString(1)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void modifier(Categorie t) {
         //To change body of generated methods, choose Tools | Templates.
    }
}
