/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gocamp.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import gocamp.IService.IReservationService;
import gocamp.entity.reservation;
import gocamp.utils.MyConnexion;

/**
 *
 * @author HAMMOUDA
 */
public class ReservationService implements IReservationService{
   Connection cnx;

    public ReservationService() {
        cnx = MyConnexion.getInstance().getCnx();
    }
    
    
    @Override
    public void createReservation(reservation r) {
                   try {
            String requete2 = "INSERT INTO reservation (nbrplace,approuve,utilisateur) VALUES (?,?,?)";
            PreparedStatement pst = MyConnexion.getInstance().cnx.prepareStatement(requete2);
            pst.setString(1, r.getNbrplace());
            pst.setString(2, r.getApprouve());
            pst.setString(3, r.getUtilisateur());
            pst.executeUpdate();
            System.out.println("Reservation  ajoutée ");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<reservation> getAll() {
        
        ObservableList<reservation> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                reservation r = new reservation();
                r.setId(rs.getInt(1));
                r.setNbrplace(rs.getString("nbrplace"));
                  r.setApprouve(rs.getString("approuve"));
                    r.setUtilisateur(rs.getString("utilisateur"));                
                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }

    @Override
    public void update(reservation r, int id) {
        try {
            String requete3 = "UPDATE reservation SET nbrplace=?,approuve=?,utilisateur=? WHERE id=?";
            PreparedStatement pst2 = MyConnexion.getInstance().cnx.prepareStatement(requete3);
            pst2.setInt(6,id);
            pst2.setString(1, r.getNbrplace());
            pst2.setString(2, r.getApprouve());
            pst2.setString(3, r.getUtilisateur());
           

            
            pst2.executeUpdate();
            System.out.println("Reservation updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  

        
    }

    @Override
    public void delete(int id) {
         try {
            String requete7 = "DELETE FROM reservation WHERE id=?";
            PreparedStatement pst7 = MyConnexion.getInstance().cnx.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
}
