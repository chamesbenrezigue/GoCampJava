/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.User;
import GoCampJavaFX.com.esprit.Mailing;
import GoCampJavaFX.com.esprit.Service.ServiceUser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Hama Hagui
 */
public class RegisterUserController implements Initializable {

    
      ObservableList<String> sexeList = FXCollections.observableArrayList("Men","Women");

    
    
    String path;
    private String Imguser;
     @FXML
    private ImageView UserImg;
    @FXML
    private TextField NomField;
    @FXML
    private TextField PrenomField;
    @FXML
    private TextField EmailField;
    @FXML
    private Label ImageName;
        @FXML
    private Label Labelsexe;
   
    @FXML
    private PasswordField PasswordField;
        @FXML
    private ChoiceBox <String>sexe;
    
    Random r = new Random();
    static int nb_valider;
    ServiceUser su = new ServiceUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sexe.setValue("Men");
        sexe.setItems(sexeList);
        // TODO
    }   
    
    public void register(ActionEvent event) throws IOException, Exception {
        User u = new User();
        u.setNom(NomField.getText());
        u.setPrenom(PrenomField.getText());
        u.setEmail(EmailField.getText());
            String generatedSecuredPasswordHash = BCrypt.hashpw(PasswordField.getText(), BCrypt.gensalt(12));

        u.setPassword(generatedSecuredPasswordHash);
        //u.setRole("User");
        u.setSexe(sexe.getValue());
        nb_valider = r.nextInt(10000);
        Mailing.mailingValider(EmailField.getText(), nb_valider);
        
        
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        String txt_CodeConfirmation = jop.showInputDialog(null, "Merci de saisir le code de verification !", "Verification Adresse Mail", JOptionPane.QUESTION_MESSAGE);
            
            if (Integer.parseInt(txt_CodeConfirmation) == nb_valider) {
  
                try {
                    
                     su.register(u);
                     //sendSMS(u);
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bienvenue Mr(s) "+ NomField.getText() , ButtonType.CLOSE);
                      
            alert.show();
            
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      LoginUserController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
                    // redirection vers la page d'accueil
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
//                lblResultat.setText("Inscription valide !!");
            }else {
              Alert alert = new Alert(Alert.AlertType.ERROR, "Code incorrect", ButtonType.CLOSE);
                alert.show();
            }
        
    }
    
    
     public void GoToLogin(MouseEvent event) throws IOException, Exception {
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      LoginUserController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
                
     }
      private void sendSMS(User u)
    {
        
        try {
            JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        String txt_CodeConfirmation = jop.showInputDialog(null, "Merci de saisir votre numero " ,JOptionPane.QUESTION_MESSAGE);
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  https://www.bulksms.com/developer/eapi/submission/character-encoding/
             */
            data += "username=" + URLEncoder.encode("islem", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("Azerty123", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode("Votre compte a été bien crée \n Email : ".concat(u.getEmail()).concat("\n").concat("Password :").concat(u.getPassword()),"ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=+216"+Integer.parseInt(txt_CodeConfirmation);

            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            BufferedReader rd;
            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
                wr.write(data);
                wr.flush();
                // Get the response
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    // Print the response output...
                    System.out.println(line);
                }
            }
            rd.close();
        } catch (IOException e) {
            System.out.println("message non envoyé");
        }

    
    
    
}
}
