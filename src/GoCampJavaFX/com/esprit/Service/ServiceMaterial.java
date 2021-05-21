/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Service;

import GoCampJavaFX.com.esprit.Entite.Material;
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
public class ServiceMaterial {
    
    private Connection con;
    private Statement ste;
    PreparedStatement stm;


    public ServiceMaterial() {
        con = DataBase.getInstance().getConnection();
    }


    public void AddMaterial(Material m)  throws SQLException{
        ste = con.createStatement();
        String sql="INSERT INTO `gocampdatabase`.`material`(`id`, `name`, `description`, `prix`, `quantity`, `nbrmatrres`,`image`) VALUES (NULL, '" + m.getName() + "' , '" + m.getDescription() + "' , '" + m.getPrice() +"','" + m.getQuantity()+"','" +0+"','" +m.getImage()+"')";
        ste.executeUpdate(sql);
        System.out.println("Materiel Ajouté");
        } 
    
     public List<Material> ShowMaterial(){
        List<Material> materials = new ArrayList<>();
    try {    
    String sql = "select * from material";
    
    stm = con.prepareStatement(sql);
    ResultSet rs = stm.executeQuery();
    while (rs.next()){
        Material m = new Material();
        m.setId(rs.getInt("id"));
        m.setName(rs.getString("name"));
        m.setDescription(rs.getString("description"));
        m.setPrice(rs.getInt("prix"));
        m.setQuantity(rs.getInt("quantity"));
        m.setImage(rs.getString("image"));
        materials.add(m);
    }
    } catch (SQLException ex) {
    System.out.println(ex.getMessage());
    }
        return materials;
    }
     
    public boolean delete(Material t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `gocampdatabase`.`material` where id =? AND name =?");
        pre.setInt(1, t.getId());
        pre.setString(2, t.getName());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Material was deleted successfully!");
        }
        return true;
    }
  
}
