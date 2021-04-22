/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.User;
import static GoCampJavaFX.com.esprit.GUI.Start.Userconnected;
import GoCampJavaFX.com.esprit.Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hama Hagui
 */
public class LoginUserController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    ServiceUser su = new ServiceUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void Login(ActionEvent event) throws IOException, Exception {
        User u = new User();
        u = su.login(emailField.getText(), passwordField.getText());
        if(u.getIdUser()== 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Verifier vos donnés", ButtonType.CLOSE);
            alert.show();
            
        } else {
            
            User user1 = su.login(emailField.getText(), passwordField.getText());
        Userconnected.setIdUser(user1.getIdUser());
        Userconnected.setNom(user1.getNom());
        Userconnected.setPrenom(user1.getPrenom());
        Userconnected.setEmail(user1.getEmail());
        Userconnected.setPassword(user1.getPassword());
        Userconnected.setRole(user1.getRole());
        
       Userconnected=user1;
        System.out.println("this is test");
        System.out.println(Userconnected);
        
        if(u.getRole().equals("Admin"))
        {
         
     
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("HomeBack.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      HomeBackController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
            
        }
        
        
        if(u.getRole().equals("User"))
        {
         
     
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("HomeFront.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      HomeFrontController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
            
        }
                
        }
        
    }
    
    public void GoToRegister(MouseEvent event) throws IOException, Exception {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("RegisterUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      RegisterUserController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
    }
    
    @FXML
    private Button btn_forgot;


    @FXML
    void forgot(ActionEvent event) {
               FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      ForgotPasswordController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }

    }
    
}
