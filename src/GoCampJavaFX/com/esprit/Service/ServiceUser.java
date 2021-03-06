/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Service;

import GoCampJavaFX.com.esprit.Entite.Material;
import GoCampJavaFX.com.esprit.Entite.User;
import GoCampJavaFX.com.esprit.IService.IServiceUser;
import GoCampJavaFX.com.esprit.Util.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Hama Hagui
 */
public class ServiceUser implements IServiceUser<User> {

    private Connection con;
    private Statement ste;
    PreparedStatement stm;


    public ServiceUser() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void register(User t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `gocampdatabase`.`user` (`id` , `first_name`, `last_name`, `email` , `password` , `roles`, `sexe`) VALUES (NULL, '" + t.getNom() + "' , '" + t.getPrenom() + "' , '" + t.getEmail() + "', '" + t.getPassword() + "', '" + "[\"ROLE_USER\"]" + "', '" + t.getSexe() + "');";
        ste.executeUpdate(requeteInsert);

    }

    @Override
    public User login(String email, String password) throws SQLException {
        User u = new User();
        try {
            String sql = "SELECT * from user WHERE email ='"+ email +"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next() == true) {
            if(BCrypt.checkpw(password, rs.getString(6))){
                int idUser = rs.getInt(1);
                String Nom = rs.getString(3);
                String Prenom = rs.getString(4);
                String Email = rs.getString(5);
                String Password = rs.getString(6);
                String role = rs.getString(2);

                String sexe = rs.getString(9);

                u = new User(idUser, Nom, Prenom, Email, Password, role,sexe);}

                //System.out.println(" |||  user  authentifi??  |||");
              
            } else {
                System.out.println("non trouv??");
            }
            
            

        } catch (SQLException ex) {
            Logger.getLogger(IServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public boolean update(User t, int id) throws SQLException {
    
    String sql = "UPDATE user SET first_name=?, last_name=?, email=?,roles=? WHERE id=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, t.getNom());
        statement.setString(2, t.getPrenom());
        statement.setString(3, t.getEmail());
        statement.setString(4, t.getRole());
        statement.setInt(5, id);
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing User was updated successfully!");
        }
        return true;
    }

    @Override
    public boolean delete(User t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `gocampdatabase`.`user` where id =? AND first_name=?");
        pre.setInt(1, t.getIdUser());
        pre.setString(2, t.getNom());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A User was deleted successfully!");
        }
        return true;
    }

    @Override
    public User FindById(int id) throws SQLException {
        String req = "select * from user where id = ?";
            User u = null;
            try {
                PreparedStatement ps = con.prepareStatement(req);
                 ps.setInt(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
               
                
                    u = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return u;
    }

    @Override
    public boolean ResetPassword(String pass, int id) throws SQLException {
         String sql = "UPDATE user SET password=? WHERE id=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, pass);
        statement.setInt(2, id);
        
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing User has reseted his password !");
        }
        return true;
    }
     public List<User> ShowUser(){
        List<User> users = new ArrayList<>();
    try {    
    String sql = "select * from user";
    
    stm = con.prepareStatement(sql);
    ResultSet rs = stm.executeQuery();
    while (rs.next()){
        User u = new User();
        u.setIdUser(rs.getInt("id"));
        u.setNom(rs.getString("first_name"));
        u.setPrenom(rs.getString("last_name"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setRole(rs.getString("roles"));
        u.setSexe(rs.getString("sexe"));


        users.add(u);
    }
    } catch (SQLException ex) {
    System.out.println(ex.getMessage());
    }
        return users;
    }
     
     public int countMen() throws SQLException{
         int count=0;
       Statement stmt3 = con.createStatement();
ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM user WHERE `sexe` = \"Men\"");
    while(rs3.next()){
    count = rs3.getInt(1);
    }
            return count ; 
     }
       public int countWomen() throws SQLException{
         int count=0;
       Statement stmt3 = con.createStatement();
ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM user WHERE `sexe` = \"Women\"");
    while(rs3.next()){
    count = rs3.getInt(1);
    }
            return count ; 
     }
    public int countRservationMateriel() throws SQLException{
         int count=0;
       Statement stmt3 = con.createStatement();
ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM material_reservation");
    while(rs3.next()){
    count = rs3.getInt(1);
    }
            return count ; 
     }
    
      public int countRservationEvent() throws SQLException{
         int count=0;
       Statement stmt3 = con.createStatement();
ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM reservation");
    while(rs3.next()){
    count = rs3.getInt(1);
    }
            return count ; 
     }
    
       
       
        public void modifier(User u) {
         try {

            String requete = "UPDATE user SET first_name=?,last_name=?,email=? ,password=?,roles=?,sexe=?WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
           
            pst.setString(3, u.getEmail());
            pst.setString(5, u.getRole());
            pst.setString(4,u.getPassword());
            pst.setString(1, u.getNom());
            pst.setString(6, u.getSexe());
            pst.setString(2, u.getPrenom());
           pst.setInt(7, u.getIdUser());
           

          
            
            pst.executeUpdate();
            System.out.println("suggestion modifi??e !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
         public Boolean VerifyUserByEmail(String email) throws SQLException {
		User u = new User();
		//Boolean found = false;  Statement stm = conn.createStatement();
            
                 Statement stm = con.createStatement();

            
		
		String query = "select * from user where email = '" + email + "'";
		try {
			 ResultSet rst = stm.executeQuery(query);
			if (rst.next()){ 
			return true;
                        }
		} catch (SQLException ex) {
                         System.out.println("erreur" + ex.getMessage());
                }
       return false;
	};
         public void updatemdp(String email, String mdp) throws SQLException {
         Statement stm = con.createStatement();
         
        String query = "UPDATE user SET password= '"+mdp+"' WHERE email='"+email+"'";
        stm.executeUpdate(query); 
        
    }
  
}
