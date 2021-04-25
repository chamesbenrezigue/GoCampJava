/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gocamp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hamza
 */
public class MyConnexion {
   String url = "jdbc:mysql://localhost:3306/java1";
    String login = "root";
    String mdp = "";
    public Connection cnx;
    public static MyConnexion instance;

    private  MyConnexion() {
        try {
            cnx = DriverManager.getConnection(url, login, mdp);
            System.out.println("Connexion Etablie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static  MyConnexion getInstance(){
        if(instance == null){instance = new  MyConnexion();}
        return instance ;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
}
