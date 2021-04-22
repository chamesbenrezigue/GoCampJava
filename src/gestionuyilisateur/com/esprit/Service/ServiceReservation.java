/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuyilisateur.com.esprit.Service;

import gestionuyilisateur.com.esprit.Entite.Material;
import gestionuyilisateur.com.esprit.Entite.Reservation;
import gestionuyilisateur.com.esprit.Util.DataBase;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;



/**
 *
 * @author chaima
 */
public class ServiceReservation {
    
    private Connection con;
    private Statement ste;
    PreparedStatement stm;


    public ServiceReservation() {
        con = DataBase.getInstance().getConnection();
    }


    public void AddRent(Reservation r)  throws SQLException{
        ste = con.createStatement();
        String sql="INSERT INTO `pidev3a`.`reservation`(`id`, `date_start`, `date_end`, `user_id`,`material_id`) VALUES (NULL, '" + r.getdate_start() + "' , '" + r.getdate_end() + "' , '" + r.getuser_id() +"', '" + r.getmaterial_id()+ "');";
        ste.executeUpdate(sql);
        System.out.println("Reservation Ajoutée");
        } 
    
     public List<Reservation> ShowReservation(){
        List<Reservation> reservations = new ArrayList<>();
    try {    
    String sql = "select * from reservation";
    
    stm = con.prepareStatement(sql);
    ResultSet rs = stm.executeQuery();
    while (rs.next()){
        Reservation r = new Reservation();
        r.setId(rs.getInt("id"));
        r.setuser_id(rs.getInt("user_id"));
        r.setmaterial_id(rs.getInt("material_id"));       
        r.setdate_start(rs.getDate("date_start"));
        r.setdate_end(rs.getDate("date_end"));
      
        reservations.add(r);
    }
    } catch (SQLException ex) {
    System.out.println(ex.getMessage());
    }
        return reservations;
     }
   
     public boolean deleteRes(Reservation k) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `pidev3a`.`reservation` where id =?");
        pre.setInt(1, k.getId());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A reservation was deleted successfully!");
        }
        return true;
    }
}
