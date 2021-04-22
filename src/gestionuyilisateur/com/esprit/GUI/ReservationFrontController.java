/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuyilisateur.com.esprit.GUI;
import java.sql.Date;

import gestionuyilisateur.com.esprit.Entite.Material;
import gestionuyilisateur.com.esprit.Entite.Reservation;
import static gestionuyilisateur.com.esprit.GUI.Start.Userconnected;
import gestionuyilisateur.com.esprit.Service.ServiceMaterial;
import gestionuyilisateur.com.esprit.Service.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ReservationFrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
      int M;
        ServiceMaterial sm = new ServiceMaterial();
    @FXML
    private DatePicker date_start;

    @FXML
    private DatePicker date_end;

    @FXML
    private Button btnRent;
     
    
        ServiceReservation sr = new ServiceReservation();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
       public void initData(int m) {
        System.out.println(m);
         M=m;
    }
    
    @FXML
    void AddReservation(ActionEvent event) {
      Reservation r = new Reservation();
              r.setdate_start(Date.valueOf(date_start.getValue()));
              r.setdate_end(Date.valueOf(date_end.getValue()));
              r.setuser_id(Userconnected.getIdUser());
              r.setmaterial_id(M);
              System.out.println(r);
        try {
            
            sr.AddRent(r);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ShowReservationFront.fxml"));
                    Parent root;
                    try {
                        root = LOADER.load();
                  
                    Scene sc = new Scene(root);
                     ShowReservationFrontController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
                    window.setScene(sc);
                    window.show();
                          } catch (IOException ex) {
                        Logger.getLogger(ShowMaterialController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    
    }}
