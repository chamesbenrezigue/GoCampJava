/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;


import GoCampJavaFX.com.esprit.Entite.Material;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import GoCampJavaFX.com.esprit.Entite.evenement;
import GoCampJavaFX.com.esprit.Entite.evenementreservation;
import static GoCampJavaFX.com.esprit.GUI.Start.Userconnected;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import GoCampJavaFX.com.esprit.Service.EvenementService;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author HAMMOUDA
 */
public class EvenementControllerfront implements Initializable {
EvenementService es = new EvenementService();
    
    
    @FXML
    private TextField ev_nom;
    @FXML
    private TextField ev_desc;
    @FXML
    private TextField ev_prix;
    @FXML
    private TextField ev_nombr;
    private Button btnajouter;
    @FXML
    private DatePicker ev_date;
       @FXML
    private Rating rate;
    @FXML
    private Label msg;
    @FXML
    private Button rating_but;
     
    
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
    private Button btnsupp;
    private Button btnmodif;
    @FXML
    private TextField recherche;
    @FXML
    private ImageView imgv5;
    private TextField ev_image;
    @FXML
    private TableColumn<evenement , String> event_image;
    private ImageView imview;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private Button btnRATE;
        @FXML
    private Button join;
    @FXML
    void GoToEventRes(ActionEvent event) {
        
                                 join.setOnAction(e -> {
                             
     evenement selectedItem = table.getSelectionModel().getSelectedItem();
     evenementreservation er = new evenementreservation();
      er.setId_user(Userconnected.getIdUser());
      er.setId_evenement(selectedItem.getId());
      er.setDate_evenement(selectedItem.getDate());
                                     try {
                                         es.AddJoindre(er);
                                     } catch (SQLException ex) {
                                         Logger.getLogger(EvenementControllerfront.class.getName()).log(Level.SEVERE, null, ex);
                                     }
                                     
             FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ListEventReservation.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      ListEventReservationController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {}
      

      

        
    });}
        

    
  

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
                   evenement e = new GoCampJavaFX.com.esprit.Entite.evenement(ev_nom.getText(), ev_desc.getText(),Date.valueOf(ev_date.getValue()),ev_prix.getText(),ev_nombr.getText(),ev_image.getText());
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

    private void Modifier(ActionEvent event) {
         if (table.getSelectionModel().getSelectedItem() != null) {
            cr.update(new GoCampJavaFX.com.esprit.Entite.evenement(ev_nom.getText(), ev_desc.getText(),Date.valueOf(ev_date.getValue()),ev_prix.getText(),ev_nombr.getText(),ev_image.getText()), table.getSelectionModel().getSelectedItem().getId());
            data.removeAll(data);
            for (evenement e : FXCollections.observableArrayList(cr.getAll())) {
                data.add(e);
            }
            clear();
            

        }
    }

    private void Clear(ActionEvent event) {
        clear();
       
    }
    
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

    @FXML
    private void Rate(ActionEvent event) {
          final String fromEmail = "GoCamp315@gmail.com"; //requires valid gmail id
		final String password = "xgqbqkchebjhabik"; // correct password for gmail id
		final String toEmail = Userconnected.getEmail(); // can be any email id 
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		GoCampJavaFX.com.esprit.Mailing.sendEmailRate(session, toEmail,"Event Rated", msg.getText());
          
                Notifications notificationBuilder = Notifications.create()
                .title("You have added a new Rating")
                .text("Hope you Liked our service")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showConfirm();
    
       
    }


   

  }
  
    

