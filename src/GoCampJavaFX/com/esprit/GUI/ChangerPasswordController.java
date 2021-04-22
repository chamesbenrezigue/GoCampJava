/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import static GoCampJavaFX.com.esprit.GUI.ForgotPasswordController.cc;
import GoCampJavaFX.com.esprit.Service.ServiceUser;
import java.io.IOException;
import static java.lang.System.err;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Rezigue
 */
public class ChangerPasswordController implements Initializable {

    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;
    @FXML
    private Button cha;
        private Label err;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changer(ActionEvent event) throws SQLException {
         String p1 = pass1.getText();
        String p2 = pass2.getText();
        
        ServiceUser crud = new ServiceUser();
        

        
        if(p1.equals(p2)){
            
            crud.updatemdp(cc, p1);
            //closeChangerpwd();
            Notifications.create()
              .title("Welcome To SEIZE-IT")
              .text("Password Was Changed Successfully")
          //    .hideAfter(Duration.seconds(6))
              .position(Pos.TOP_RIGHT)
                    .showInformation();
            FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      LoginUserController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
            //closeChangerpwd;
            
            /** try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/Client.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }**/
            
        }else{
            err.setText("Password False !!");
        }
    
    }
    
     public void closeChangerpwd() {
        Stage Acceuil2Stage = (Stage) pass1.getScene().getWindow();
        Acceuil2Stage.hide();
    }
    
}
