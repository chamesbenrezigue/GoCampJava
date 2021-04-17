/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import static GoCampJavaFX.com.esprit.GUI.Start.Userconnected;
import GoCampJavaFX.com.esprit.Service.ServiceUser;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hama Hagui
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private TextField pass1;
    @FXML
    private TextField pass2;
    @FXML
    private TextField pass3;
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
        // TODO
        NomPrenom.setText(Userconnected.getNom() + " " + Userconnected.getPrenom());
        
    }

    public void Reset(ActionEvent event) throws IOException, Exception {
        if (pass1.getText().equals(Userconnected.getPassword()) && pass2.getText().equals(pass3.getText())) {
            if (su.ResetPassword(pass2.getText(), Userconnected.getIdUser())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mr/Mme" + Userconnected.getNom() + " " + Userconnected.getPrenom() + " Votre mot de passe a été bien modifier !", ButtonType.CLOSE);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, " il ya un petit probleme ressayer plus tard !", ButtonType.CLOSE);
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, " l'une des mot de passes ne correspand pas merci de ressayer !", ButtonType.CLOSE);
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
