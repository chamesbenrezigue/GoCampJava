/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuyilisateur.com.esprit.GUI;

import gestionuyilisateur.com.esprit.Entite.Material;
import gestionuyilisateur.com.esprit.Service.ServiceMaterial;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class AddMaterialController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfPrice;
    @FXML
    private Button btnAdd;
    
    @FXML
    private Button choosefile;
    
    @FXML
    private AnchorPane anchorpane;
    
     @FXML
    void FileChoose(ActionEvent event) throws IOException {
         FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open File Dialog");
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        
        File file = filechooser.showOpenDialog(stage);
        if ( file !=null)
        {Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
        }

    }
    
    ServiceMaterial sm = new ServiceMaterial();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddMaterial(ActionEvent event) throws SQLException {
        Material m = new Material();
        m.setName(tfName.getText());
        m.setDescription(tfDescription.getText());
         String k= tfPrice.getText();
        m.setPrice(Float.parseFloat(k));
        sm.AddMaterial(m);
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ShowMaterial_back.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            ShowMaterialController_back cntr = LOADER.getController();
            Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
            
            window.setScene(sc);
            window.show();
        } catch (IOException ex) {
            
        }
    
}
    
}
