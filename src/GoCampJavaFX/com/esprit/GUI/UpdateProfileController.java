/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.User;
import static GoCampJavaFX.com.esprit.GUI.Start.Userconnected;
import GoCampJavaFX.com.esprit.Service.ServiceUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hama Hagui
 */
public class UpdateProfileController implements Initializable {

    @FXML
    private TextField EmailField;
    @FXML
    private TextField NomField;
    @FXML
    private TextField PrenomField;
    @FXML
    private ImageView ImageUserLogedIn;
    @FXML
    private Label NomPrenom;

    ServiceUser su = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmailField.setText(Userconnected.getEmail());
        NomField.setText(Userconnected.getNom());
        PrenomField.setText(Userconnected.getPrenom());
        NomPrenom.setText(Userconnected.getNom() + " " + Userconnected.getPrenom());
    }

    public void Modifier(ActionEvent event) throws IOException, Exception {

        User u = new User(Userconnected.getIdUser(), NomField.getText(), PrenomField.getText(), EmailField.getText(), Userconnected.getPassword(), "User");
        if (su.update(u, Userconnected.getIdUser())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mr/Mme"+ NomField.getText()+ " "+ PrenomField.getText()+ " Vos donnés personelles sont modifiés !", ButtonType.CLOSE);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, " il ya un petit probleme ressayer plus tard !", ButtonType.CLOSE);
                alert.show();
        }
    }


    public void DeleteAccount(MouseEvent event) throws IOException, Exception {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer Votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (su.delete(Userconnected)) {
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                    LoginUserController cntr = LOADER.getController();
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {

                }
            }

        }

    }

    public void Logout(MouseEvent event) throws IOException, Exception {
        Userconnected.setIdUser(0);
        Userconnected.setNom("");
        Userconnected.setPrenom("");
        Userconnected.setEmail("");
        Userconnected.setPassword("");
        Userconnected.setRole("");
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            LoginUserController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }
    
    public void ResetPassword(MouseEvent event) throws IOException, Exception {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            ResetPasswordController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }

    
    public void UpdateProfile(MouseEvent event) throws IOException, Exception {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("UpdateProfile.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            UpdateProfileController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }
}
