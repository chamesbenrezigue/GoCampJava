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
    private Button btnajouter;
    @FXML
    private Rating rate;
    @FXML
    private Label msg;
    @FXML
    private Button rating_but;
     
    
    EvenementService cr = new EvenementService();
    ObservableList<evenement> data = FXCollections.observableArrayList(cr.getAll());
    
    @FXML
    private TableView<evenement> table;
    @FXML
    private TableColumn<evenement, String> ev_name;
    @FXML
    private TableColumn<evenement, String> ev_descr;
    @FXML
    private TableColumn<evenement, Date> event_date;
    @FXML
    private TableColumn<evenement, Date> event_date_end;
    @FXML
    private TableColumn<evenement, String> event_prix;
    @FXML
    private TableColumn<evenement, String> event_Type;
    @FXML
    private TableColumn<evenement , String> event_image;

    @FXML
    private TextField recherche;

    @FXML
    private Button btnRATE;
    @FXML
    private Button join;
    @FXML
    void GoToEventRes(ActionEvent event) {
        
                                 join.setOnAction(e -> {
                    
            evenement selectedItem = table.getSelectionModel().getSelectedItem();                   
            int nomE=    selectedItem.getId();                    
                    //es.AddJoindre(er);

                    FXMLLoader LOADER = new FXMLLoader(getClass().getResource("AddReservationEvent.fxml"));
                    try {
                        Parent root = LOADER.load();
                        Scene sc = new Scene(root);
                        AddReservationEventController cntr = LOADER.getController();
                       cntr.initData(nomE);
                        Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
                        
                        window.setScene(sc);
                        window.show();
                    } catch (IOException ex) {}
                    
        
    });
    }
        

    
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    System.out.println(data);

        
         // TODO
          ev_name.setCellValueFactory(new PropertyValueFactory("nom_event"));
         ev_descr.setCellValueFactory(new PropertyValueFactory("description_event"));
        event_date.setCellValueFactory(new PropertyValueFactory("date"));
        event_date_end.setCellValueFactory(new PropertyValueFactory("dateEnd"));
          event_prix.setCellValueFactory(new PropertyValueFactory("prix_event"));
         event_Type.setCellValueFactory(new PropertyValueFactory("type"));
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
  
    

