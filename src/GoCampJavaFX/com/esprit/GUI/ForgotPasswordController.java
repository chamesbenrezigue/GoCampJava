/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Mailing;
import GoCampJavaFX.com.esprit.Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rezigue
 */
public class ForgotPasswordController implements Initializable {
    Mailing email = new Mailing();
    public static String c;
    public static String cc;
    public static String code;

    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfcode;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private Button btnValider;
ServiceUser crud = new ServiceUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     @FXML
    private void envouyercode(ActionEvent event) throws SQLException {
         cc=tfemail.getText();
        code = email.getPassword();
        if (crud.VerifyUserByEmail(cc)){
            
             email.sendEmail1(cc,code);
             
        }else{
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You Don't Have Account !!!");
        alert.show();
            
        }
    }
       @FXML
    private void getpass(ActionEvent event) {
         cc=tfemail.getText();
        c=tfcode.getText();
        if(code.equals(c)){
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("ChangerPassword.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }
            
        }else{
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Enter Correct Code !!!");
        alert.show();
        }
    }
    
}
