/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuyilisateur.com.esprit.Service;

import gestionuyilisateur.com.esprit.Entite.User;
import gestionuyilisateur.com.esprit.IService.IServiceUser;
import gestionuyilisateur.com.esprit.Util.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hama Hagui
 */
public class ServiceUser implements IServiceUser<User> {

    private Connection con;
    private Statement ste;

    public ServiceUser() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void register(User t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `pidev3a`.`user` (`idUser` , `nom`, `prenom`, `email` , `password` , `role`) VALUES (NULL, '" + t.getNom() + "' , '" + t.getPrenom() + "' , '" + t.getEmail() + "', '" + t.getPassword() + "', '" + t.getRole() + "');";
        ste.executeUpdate(requeteInsert);

    }

    @Override
    public User login(String email, String password) throws SQLException {
        User u = new User();
        try {
            String sql = "SELECT * from user WHERE email ='" + email + "' AND password='" + password + "'";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next() == true) {
                int idUser = rs.getInt(1);
                String Nom = rs.getString(2);
                String Prenom = rs.getString(3);
                String Email = rs.getString(4);
                String Password = rs.getString(5);
                String role = rs.getString(6);
                u = new User(idUser, Nom, Prenom, Email, Password, role);
                System.out.println(" |||  user  authentifié  |||");
                System.out.println(u);
              
            } else {
                System.out.println("non trouvé");
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(IServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public boolean update(User t, int id) throws SQLException {
    
    String sql = "UPDATE user SET nom=?, prenom=?, email=?,role=? WHERE idUser=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, t.getNom());
        statement.setString(2, t.getPrenom());
        statement.setString(3, t.getEmail());
        
        statement.setString(4, "User");
        statement.setInt(5, id);
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing User was updated successfully!");
        }
        return true;
    }

    @Override
    public boolean delete(User t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `pidev3a`.`user` where idUser =? AND nom =?");
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
        String req = "select * from user where idUser = ?";
            User u = null;
            try {
                PreparedStatement ps = con.prepareStatement(req);
                 ps.setInt(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
               
                
                    u = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return u;
    }

    @Override
    public boolean ResetPassword(String pass, int id) throws SQLException {
         String sql = "UPDATE user SET password=? WHERE idUser=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, pass);
        statement.setInt(2, id);
        
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing User has reseted his password !");
        }
        return true;
    }

   

}
