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
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rezigue
 */
public class HomeBackController implements Initializable {


    @FXML
    private JFXButton EditProfil;
     @FXML
    private JFXButton home;
         @FXML
    private JFXButton gestionuser;

    @FXML
    private VBox pnl_scroll;

    @FXML
    void GoToProfile(ActionEvent event) {
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
    void GoToGestionUser(ActionEvent event) {
         pnl_scroll.getChildren().clear();

        Node [] nodes = new  Node[15];
        
       
            try {
                nodes[0] = (Node)FXMLLoader.load(getClass().getResource("ShowUsers.fxml"));
                               pnl_scroll.getChildren().add(nodes[0]);


                
            } catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    
        @FXML
    void GoToCharts(ActionEvent event) {
            pnl_scroll.getChildren().clear();

        Node [] nodes = new  Node[15];
        
       
            try {
                nodes[0] = (Node)FXMLLoader.load(getClass().getResource("Charts.fxml"));
                               pnl_scroll.getChildren().add(nodes[0]);


                
            } catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              pnl_scroll.getChildren().clear();

        Node [] nodes = new  Node[15];
        
       
            try {
                nodes[0] = (Node)FXMLLoader.load(getClass().getResource("Charts.fxml"));
                               pnl_scroll.getChildren().add(nodes[0]);


                
            } catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    
  
    
}
