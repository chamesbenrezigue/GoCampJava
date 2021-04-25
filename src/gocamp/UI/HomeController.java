/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gocamp.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane displayArea;

    @FXML
    private Button front;

    @FXML
    private Button back;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("back/Home.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    void Front(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("front/Home.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
