/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuyilisateur.com.esprit.GUI;

import gestionuyilisateur.com.esprit.Entite.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hama Hagui
 */
public class Start  extends Application {

    static User Userconnected = new User();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
        
        Scene scene = new Scene(root);
        
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
