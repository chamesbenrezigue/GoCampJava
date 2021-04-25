/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gocamp.UI.back;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import gocamp.entity.evenement;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import gocamp.Service.EvenementService;

/**
 * FXML Controller class
 *
 * @author HAMMOUDA
 */
public class EvenementController implements Initializable {

    @FXML
    private TextField ev_nom;
    @FXML
    private TextField ev_desc;
    @FXML
    private TextField ev_prix;
    @FXML
    private TextField ev_nombr;
    @FXML
    private Button btnajouter;
    @FXML
    private DatePicker ev_date;
     
    
    EvenementService cr = new EvenementService();
    ObservableList<evenement> data = FXCollections.observableArrayList(cr.getAll());;
    @FXML
    private TableColumn<evenement, String> ev_name;
    @FXML
    private TableColumn<evenement, String> ev_descr;
    @FXML
    private TableColumn<evenement, Date> event_date;
    @FXML
    private TableColumn<evenement, String> event_prix;
    @FXML
    private TableColumn<evenement, String> event_amount;
    @FXML
    private TableView<evenement> table;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmodif;
    @FXML
    private TextField recherche;
    @FXML
    private ImageView imgv5;
    @FXML
    private TextField ev_image;
    @FXML
    private TableColumn<evenement , String> event_image;
    @FXML
    private ImageView imview;
    @FXML
    private Button btnclear;
    @FXML
    private ImageView imgv;
    @FXML
    private ImageView imgv2;
    @FXML
    private Button upload;
   
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         // TODO
          ev_name.setCellValueFactory(new PropertyValueFactory("nom_event"));
         ev_descr.setCellValueFactory(new PropertyValueFactory("description_event"));
        event_date.setCellValueFactory(new PropertyValueFactory("date"));
          event_prix.setCellValueFactory(new PropertyValueFactory("prix_event"));
         event_amount.setCellValueFactory(new PropertyValueFactory("nbr_place"));
         event_image.setCellValueFactory(new PropertyValueFactory("image"));
        table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    gocamp.entity.evenement e = (gocamp.entity.evenement) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                      ev_nom.setText(e.getNom_event());
                      ev_desc.setText(e.getDescription_event());
                     ev_date.setValue(e.getDate().toLocalDate());
                      ev_prix.setText(e.getPrix_event());
                       ev_nombr.setText(e.getNbr_place());
                     ev_image.setText(e.getImage());
                 
                    btnajouter.setDisable(true);
                    
                }
            }
        });
        recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               filtrerEventList((String) oldValue, (String) newValue);
            }

        });
    }    
   
  void filtrerEventList(String oldValue, String newValue) {
        EvenementService evs = new EvenementService();
        ObservableList<evenement> filteredList = FXCollections.observableArrayList();
        if (recherche.getText() == null || newValue == null) {
            table.setItems((ObservableList<evenement>) evs.FindEvent());
        } else {
            table.setItems((ObservableList<evenement>) evs.FindEvent());
            newValue = newValue.toUpperCase();

            for (evenement e : table.getItems()) {

                String filterEventName = e.getNom_event();
               

                if (filterEventName.toUpperCase().contains(newValue)) {
                    filteredList.add(e);
                }
            }
            table.setItems(filteredList);
        }
    }

    @FXML
    private void Ajout(ActionEvent event) {
   
           if (ev_nom.getText() == null || ev_nom.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de nom s'il vous plait!");
            dialogW.showAndWait();
            } 
           else if (ev_desc.getText() == null || ev_desc.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de discription s'il vous plait!");
            dialogW.showAndWait();
            } 
            else if (ev_date.getValue()== null ){
		Alert dialogW = new Alert(Alert.AlertType.WARNING);
		dialogW.setTitle("A warning");
 		dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez inserer la date  s'il vous plait!");
            dialogW.showAndWait();
        }     
        
             else if (ev_prix.getText() == null || ev_prix.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de prix s'il vous plait!");
            dialogW.showAndWait();
            } 
              else if (ev_nombr.getText() == null || ev_nombr.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de nombre de place s'il vous plait!");
            dialogW.showAndWait();
            } 
              
              else {
                   evenement e = new gocamp.entity.evenement(ev_nom.getText(), ev_desc.getText(),Date.valueOf(ev_date.getValue()),ev_prix.getText(),ev_nombr.getText(),ev_image.getText());
       cr.createEvenement(e);
              }
           
     data.removeAll(data);
         for (evenement ev : FXCollections.observableArrayList(cr.getAll())) {
            data.add(ev);

        }
         clear();
        

        
    }

    private void clear() {
        table.getSelectionModel().clearSelection();
        ev_nom.clear();
        ev_desc.clear();
        ev_date.setValue(null);
        ev_prix.clear();
        ev_nombr.clear();
         btnajouter.setDisable(false);
        btnsupp.setDisable(false);
        btnmodif.setDisable(false);
        btnajouter.setDisable(false);
    }
    @FXML
    private void Delete(ActionEvent event) {
         ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous supprimé cet evenement !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Evenement Supprimee");
                cr.delete(table.getSelectionModel().getSelectedItem().getId());
                data.removeAll(data);
                for (evenement e : FXCollections.observableArrayList(cr.getAll())) {
                    data.add(e);
                }

            }
            clear();
        } else {
            System.out.println("Cancel");
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
         if (table.getSelectionModel().getSelectedItem() != null) {
            cr.update(new gocamp.entity.evenement(ev_nom.getText(), ev_desc.getText(),Date.valueOf(ev_date.getValue()),ev_prix.getText(),ev_nombr.getText(),ev_image.getText()), table.getSelectionModel().getSelectedItem().getId());
            data.removeAll(data);
            for (evenement e : FXCollections.observableArrayList(cr.getAll())) {
                data.add(e);
            }
            clear();
            

        }
    }

    @FXML
    private void Clear(ActionEvent event) {
        clear();
       
    }
    
    @FXML
    private void AjouterPhoto(ActionEvent event) throws FileNotFoundException, IOException {
        

        FileChooser fileChooser = new FileChooser();
       

        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif" , "*.mp4"));
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            
            //this.image.setText(selectedFile.getName());
            this.ev_image.setText(selectedFile.toURI().toURL().toString());
            imview.setImage(image);
        }

    }
    
 public void load_pic(String links) {

        Image img = new Image(links);
        imview.setImage(img);

    }
   

  }
  
    

