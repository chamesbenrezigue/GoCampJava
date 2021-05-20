/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.evenement;
import GoCampJavaFX.com.esprit.Entite.evenementreservation;
import GoCampJavaFX.com.esprit.Service.EvenementService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rezigue
 */
public class AddReservationEventController implements Initializable {
          int E;

        EvenementService es = new EvenementService();

    @FXML
    private JFXTextField TF_nbrplace;
    @FXML
    private JFXTextField TF_nom;
    @FXML
    private JFXTextField TF_prenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
              public void initData(int e) {
                  E=e;
    }

    @FXML
    private void GoToShowReservation(ActionEvent event)  {
        
              
                   if (TF_nbrplace.getText() == null || TF_nbrplace.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de nombre de place s'il vous plait!");
            dialogW.showAndWait();
            } 
           else if (TF_nom.getText() == null || TF_nom.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ du nom s'il vous plait!");
            dialogW.showAndWait();
            } 
            else if (TF_prenom.getText()== null || TF_prenom.getText().trim().isEmpty()){
		Alert dialogW = new Alert(Alert.AlertType.WARNING);
		dialogW.setTitle("A warning");
 		dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ du penom s'il vous plait!");
            dialogW.showAndWait();
        }   
              
              else {
                
                       try {
                           evenementreservation er = new evenementreservation();
                           er.setEvent(es.FindNameEvenemntById(E));
                           er.setNom(TF_nom.getText());
                           er.setPrenom(TF_prenom.getText());
                           er.setNbrplace(TF_nbrplace.getText());

                           es.AddJoindre(er);
                           FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ListEventReservation.fxml"));
                           Parent root = LOADER.load();
                           Scene sc = new Scene(root);
                           ListEventReservationController cntr = LOADER.getController();
                           Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
                           window.setScene(sc);
                           window.show();
                       } catch (SQLException ex) {
                           Logger.getLogger(AddReservationEventController.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (IOException ex) {
                           Logger.getLogger(AddReservationEventController.class.getName()).log(Level.SEVERE, null, ex);
                       }
      
    }
}
}