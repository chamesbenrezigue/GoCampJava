/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rezigue
 */
public class HomeFrontController implements Initializable {

    @FXML
    private JFXButton EditProfil;
    @FXML
    private VBox pnl_scroll;
       @FXML
    void GoToRenting(ActionEvent event) {
                   pnl_scroll.getChildren().clear();

        Node [] nodes = new  Node[15];
        
       
            try {
                nodes[0] = (Node)FXMLLoader.load(getClass().getResource("ShowMaterial.fxml"));
                               pnl_scroll.getChildren().add(nodes[0]);


                
            } catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    
    @FXML
    void GoToEvent(ActionEvent event) {
                        pnl_scroll.getChildren().clear();

        Node [] nodes = new  Node[15];
        
       
            try {
                nodes[0] = (Node)FXMLLoader.load(getClass().getResource("evenementfront.fxml"));
                               pnl_scroll.getChildren().add(nodes[0]);


                
            } catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToProfile(ActionEvent event) {
           FXMLLoader LOADER = new FXMLLoader(getClass().getResource("UpdateProfile.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      UpdateProfileController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
    }
    
    @FXML
    void GoToPurchase(ActionEvent event) {
                    pnl_scroll.getChildren().clear();

        Node [] nodes = new  Node[15];
        
       
            try {
                nodes[0] = (Node)FXMLLoader.load(getClass().getResource("ListeArticlesClient.fxml"));
                               pnl_scroll.getChildren().add(nodes[0]);


                
            } catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    
}
