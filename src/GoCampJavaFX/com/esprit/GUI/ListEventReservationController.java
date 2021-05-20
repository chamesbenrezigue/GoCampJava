/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.Material;
import GoCampJavaFX.com.esprit.Entite.evenementreservation;
import GoCampJavaFX.com.esprit.Service.EvenementService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Rezigue
 */
public class ListEventReservationController implements Initializable {


    
    
        EvenementService ES = new EvenementService();
    


    ObservableList<evenementreservation> List = FXCollections.observableArrayList(
    ES.ShowReservationEvent()
    );
    @FXML
    private VBox pnl_scroll;
    @FXML
    private Button dlt;
    @FXML
    private TableView<evenementreservation> table;
    @FXML
    private TableColumn<String, evenementreservation> C_nom;
    @FXML
    private TableColumn<String, evenementreservation> C_prenom;
    @FXML
    private TableColumn<String, evenementreservation> C_event;
    @FXML
    private TableColumn<String, evenementreservation> C_nbrplace;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    System.out.println(ES.ShowReservationEvent());

        C_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        C_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        C_event.setCellValueFactory(new PropertyValueFactory<>("event"));
        C_nbrplace.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));

         table.setItems(List);
    }    



    @FXML
    private void DeleteReservation(ActionEvent event) {
        
                dlt.setOnAction(e -> {
    evenementreservation selectedItem = table.getSelectionModel().getSelectedItem();
            try {
                DeleteReservation(selectedItem);
            } catch (Exception ex) {
                Logger.getLogger(ShowMaterialController_back.class.getName()).log(Level.SEVERE, null, ex);
            }
    table.getItems().remove(selectedItem);
        System.out.println("Reservation supp");

    });
    }
         public void DeleteReservation(evenementreservation es) throws IOException, Exception {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer la reservation ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           ES.deleteReservation(es.getId());
        
        }}
    
}
