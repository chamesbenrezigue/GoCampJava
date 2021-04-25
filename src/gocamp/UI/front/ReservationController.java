/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gocamp.UI.front;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import gocamp.Service.ReservationService;

import gocamp.entity.reservation;

/**
 * FXML Controller class
 *
 * @author HAMMOUDA
 */
public class ReservationController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private TableView<reservation> table;
    @FXML
    private TextField r_nbrplace;
    @FXML
    private TextField r_approuve;
    @FXML
    private TextField r_utilisateur;
    @FXML
    private TableColumn<reservation, String> rt_nbrplace;
    @FXML
    private TableColumn<reservation, String> rt_approuve;
    @FXML
    private TableColumn<reservation, String> rt_utilisateur;
        gocamp.Service.ReservationService cr = new ReservationService();
    ObservableList<reservation> data = FXCollections.observableArrayList(cr.getAll());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                 // TODO
            rt_nbrplace.setCellValueFactory(new PropertyValueFactory("nbrplace"));
            rt_approuve.setCellValueFactory(new PropertyValueFactory("approuve"));
            rt_utilisateur.setCellValueFactory(new PropertyValueFactory("utilisateur"));
      
            table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    gocamp.entity.reservation r = (gocamp.entity.reservation) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                      r_nbrplace.setText(r.getNbrplace());
                      r_approuve.setText(r.getApprouve());
                      r_utilisateur.setText(r.getUtilisateur());
                    
                 
                    btnajouter.setDisable(true);
                    
                }
            }
            
        });
        
    }    
        @FXML
    private void Ajout(ActionEvent event) {
   
           if (r_nbrplace.getText() == null || r_nbrplace.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de nom s'il vous plait!");
            dialogW.showAndWait();
            } 
           else if (r_nbrplace.getText() == null || r_nbrplace.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de discription s'il vous plait!");
            dialogW.showAndWait();
            } 

        
             else if (r_utilisateur.getText() == null || r_utilisateur.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de prix s'il vous plait!");
            dialogW.showAndWait();
            } 
        
              
              else {
                   reservation r = new gocamp.entity.reservation(r_nbrplace.getText(), r_approuve.getText(),r_utilisateur.getText());
       cr.createReservation(r);
              }
           
     data.removeAll(data);
         for (reservation ev : FXCollections.observableArrayList(cr.getAll())) {
            data.add(ev);

        }
         clear();
        

        
    }
       private void clear() {
        table.getSelectionModel().clearSelection();
        r_nbrplace.clear();
        r_approuve.clear();
        r_utilisateur.clear();
         btnajouter.setDisable(false);
   
    }
    
}
