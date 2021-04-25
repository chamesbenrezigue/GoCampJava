/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Service;

import GoCampJavaFX.com.esprit.Entite.Material;
import GoCampJavaFX.com.esprit.Entite.Reservation;
import GoCampJavaFX.com.esprit.Util.DataBase;
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
        String sql="INSERT INTO `pidev3a`.`reservation`(`id`, `date_start`, `date_end`, `user_id`,`material_id`) VALUES (NULL, '" + r.getDate_start()+ "' , '" + r.getDate_end()+ "' , '" + r.getUser_id() +"', '" + r.getMaterial_id()+ "');";
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
        r.setUser_id(rs.getInt("user_id"));
        r.setMaterial_id(rs.getInt("material_id"));   
        r.setDate_start(rs.getDate("date_start").toString());
        r.setDate_end(rs.getDate("date_end").toString());      
        r.setName_material(FindNameMaterialById(rs.getInt("material_id")));
        r.setName_user(FindNameUserById(rs.getInt("user_id")));
                
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
            public String FindNameMaterialById(int id) throws SQLException{
       String req = "SELECT  `Name` FROM `material` WHERE `id` = ?";
            String u ="";
                PreparedStatement ps = con.prepareStatement(req);
                 ps.setInt(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
               
                
                    u = resultSet.getString(1);
                }
            return u;
           
       }
               public String FindNameUserById(int id) throws SQLException{
       String req = "SELECT  `Nom` FROM `user` WHERE `idUser` = ?";
            String u ="";
                PreparedStatement ps = con.prepareStatement(req);
                 ps.setInt(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
               
                
                    u = resultSet.getString(1);
                }
            return u;
           
       }
                       public int countMaterialReserver(int id) throws SQLException{
         int count=0;
       Statement stmt3 = con.createStatement();
ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM Reservation WHERE `material_id` ="+id);
    while(rs3.next()){
    count = rs3.getInt(1);
    }
            return count ; 
     }
}
