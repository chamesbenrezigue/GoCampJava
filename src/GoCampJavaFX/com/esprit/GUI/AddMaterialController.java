/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.Material;
import GoCampJavaFX.com.esprit.Service.ServiceMaterial;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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
    private TextField quantity;
    @FXML
    private Button choosefile;
     @FXML
    private TextField tfimg;
    @FXML
    private Button btnupload;
    @FXML
    private ImageView photo_view;
    
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
        m.setImage(tfimg.getText());
         String k= tfPrice.getText();
        m.setPrice(Integer.parseInt(k));
        m.setQuantity(Integer.parseInt(quantity.getText()));
       System.out.println(m);

        sm.AddMaterial(m);

        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("HomeBack.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            HomeBackController cntr = LOADER.getController();
            Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
            
            window.setScene(sc);
            window.show();
        } catch (IOException ex) {
            
        }
    
}
      @FXML
    private void uploadimage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("\\wamp64\\www\\GoCamp\\public\\uploads\\images\\products"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                tfimg.setText(file.getName());
                photo_view.setImage(image);
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
    }
    
}
