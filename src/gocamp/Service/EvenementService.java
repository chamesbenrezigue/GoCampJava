/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gocamp.Service;

import gocamp.IService.IEvenementService;
import gocamp.entity.evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import gocamp.utils.MyConnexion;

/**
 *
 * @author HAMMOUDA
 */
public class EvenementService implements IEvenementService{
   Connection cnx;

    public EvenementService() {
        cnx = MyConnexion.getInstance().getCnx();}
    @Override
    public void createEvenement(evenement e) {
           try {
            String requete2 = "INSERT INTO evenement (nom_event,description_event,date,prix_event,nbr_place,image) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = MyConnexion.getInstance().cnx.prepareStatement(requete2);
            pst.setString(1, e.getNom_event());
            pst.setString(2, e.getDescription_event());
            pst.setDate(3,e.getDate());
            pst.setString(4, e.getPrix_event());
            pst.setString(5, e.getNbr_place());
            pst.setString(6, e.getImage());
            pst.executeUpdate();
            System.out.println("Event added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

      @Override
    public void update(evenement e,int id) {
 try {
            String requete3 = "UPDATE evenement SET nom_event=?,description_event=?,date=?,prix_event=?,nbr_place=? , image=? WHERE id=?";
            PreparedStatement pst2 = MyConnexion.getInstance().cnx.prepareStatement(requete3);
            pst2.setInt(7,id);
            pst2.setString(1, e.getNom_event());
            pst2.setString(2, e.getDescription_event());
            pst2.setDate(3,e.getDate());
            pst2.setString(4, e.getPrix_event());
            pst2.setString(5, e.getNbr_place());
            pst2.setString(6, e.getImage());

            
            pst2.executeUpdate();
            System.out.println("Event updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void delete(int id) {
 try {
            String requete7 = "DELETE FROM evenement WHERE id=?";
            PreparedStatement pst7 = MyConnexion.getInstance().cnx.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
    
     @Override
    public List<evenement> getAll() {
ObservableList<evenement> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                evenement e = new evenement();
                e.setId(rs.getInt(1));
                e.setNom_event(rs.getString("nom_event"));
                  e.setDescription_event(rs.getString("description_event"));
                    e.setDate(rs.getDate("date"));
                    e.setPrix_event(rs.getString("prix_event"));
                    e.setNbr_place(rs.getString("nbr_place"));
                    e.setImage(rs.getString("image"));

                  

                myList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }
  public ObservableList<evenement> FindEvent() {

        ObservableList<evenement> list = FXCollections.observableArrayList();
        try {
            String requete4 = "SELECT * FROM evenement";
            Statement st5 = MyConnexion.getInstance().cnx.createStatement();
            ResultSet rs = st5.executeQuery(requete4);
            while (rs.next()) {
                evenement e = new evenement();
                e.setId(rs.getInt("id"));
                e.setDate(rs.getDate("date"));
                
                e.setNom_event(
                        rs.getString("nom_event"));
                e.setDescription_event(rs.getString("description_event"));
                e.setPrix_event(rs.getString("prix_event"));
                    e.setNbr_place(rs.getString("nbr_place"));
              e.setImage(rs.getString("image"));
                list.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return list;

    }

   
}
