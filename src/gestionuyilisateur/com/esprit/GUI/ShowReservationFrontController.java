/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuyilisateur.com.esprit.GUI;

import gestionuyilisateur.com.esprit.Entite.Material;
import gestionuyilisateur.com.esprit.Entite.Reservation;
import gestionuyilisateur.com.esprit.Service.ServiceReservation;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ShowReservationFrontController implements Initializable {

    @FXML
    private Pagination pagination;
private final static int dataSize = 100 ;
private final static int rowsPerPage = 4 ;

    @FXML
    private Button deleteRes;
    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, Integer> id;
    @FXML
    private TableColumn<Reservation, Integer> col_userid;
    @FXML
    private TableColumn<Reservation, Integer> col_materialid;
    @FXML
    private TableColumn<Reservation, Date> col_date_start;
    @FXML
    private TableColumn<Reservation, Date> col_date_end;
    
        ServiceReservation sr = new ServiceReservation();

  ObservableList<Reservation> List = FXCollections.observableArrayList(
    sr.ShowReservation()
    );
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                System.out.println(sr.ShowReservation());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_userid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_materialid.setCellValueFactory(new PropertyValueFactory<>("material_id"));
        col_date_start.setCellValueFactory(new PropertyValueFactory<>("date_start"));
        col_date_end.setCellValueFactory(new PropertyValueFactory<>("date_end"));

         table.setItems(List); 
                  pagination.setPageFactory(this::createPage);

    }  
   
     private Node createPage(int pageIndex){
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, List.size());
        table.setItems(FXCollections.observableArrayList(List.subList(fromIndex, toIndex)));
    return table;
    }
    
     
     
       @FXML
    void Delres(ActionEvent event) {
 deleteRes.setOnAction(e -> {
    Reservation selectedItem = table.getSelectionModel().getSelectedItem();
            try {
                DeleteReservation(selectedItem);
            } catch (Exception ex) {
                Logger.getLogger(ShowReservationFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
    table.getItems().remove(selectedItem);
        System.out.println("Reservation supprimée");

    });}
   
     public void DeleteReservation(Reservation r) throws IOException, Exception {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer la réservation ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           sr.deleteRes(r);
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ShowReservationFront.fxml"));
                

        }
}
}