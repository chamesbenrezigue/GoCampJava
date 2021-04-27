/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.Reservation;
import GoCampJavaFX.com.esprit.Service.ServiceReservation;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ShowReservationBackController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
  System.out.println(sr.ShowReservation());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_userid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_materialid.setCellValueFactory(new PropertyValueFactory<>("material_id"));
        col_date_start.setCellValueFactory(new PropertyValueFactory<>("date_start"));
        col_date_end.setCellValueFactory(new PropertyValueFactory<>("date_end"));

         table.setItems(List); 

    }  
    }

