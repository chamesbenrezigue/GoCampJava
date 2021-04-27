/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;


import com.jfoenix.validation.NumberValidator;
import GoCampJavaFX.com.esprit.Entite.Article;
import GoCampJavaFX.com.esprit.Entite.Categorie;
import GoCampJavaFX.com.esprit.Service.ServiceArticle;
import GoCampJavaFX.com.esprit.Service.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private TextField ttitre;
    @FXML
    private TableView<Categorie> tabv;
    @FXML
    private Button tbtn;
    @FXML
    private Button tbtn1;
    @FXML
    private Button Menu_Article;
    @FXML
    private TableColumn<Categorie, String> ColCat;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherArt();

        ContextMenu ContArticle = new ContextMenu();
        MenuItem DeleteItem = new MenuItem("Supprimer catégorie");

        DeleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*Publication pub_supp = (Publication) tableView_Publication.getSelectionModel().getSelectedItem();
                CellEditEvent eddited_cell;*/
                Object item = tabv.getSelectionModel().getSelectedItem();
                Categorie art = (Categorie) item;
                ServiceCategorie s = new ServiceCategorie();
                System.out.println(art.toString());
                s.supprimer(art);

                AfficherArt();

            }
        }
        );

        EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                if (ContArticle.isShowing()) {
                    // System.out.println("Showing");
                } else {
                    //System.out.println("Hidden");
                }
            }
        };

        ContArticle.getItems().add(DeleteItem);

        ContArticle.setOnShowing(event);
        ContArticle.setOnHiding(event);
        tabv.setContextMenu(ContArticle);
        //Object item = tableView_Publication.getSelectionModel().getSelectedItem();

    }

    private void AfficherArt() {
        ObservableList<Categorie> ArtList = FXCollections.observableArrayList();
        ColCat.setCellValueFactory(new PropertyValueFactory<>("titre_cat"));
        ServiceCategorie srec = new ServiceCategorie();
        srec.afficher().forEach(e -> {
            ArtList.add(e);
        });
        tabv.setItems(ArtList);
        
        
//        NumberValidator num = new NumberValidator();
//        ttitre.getValidators().add(num);
//        num.setMessage("only numbers are supported!!");
//        ttitre.focusedProperty().addListener(new ChangeListener<Boolean>(){
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//              if(!newValue) {
//              ttitre.validate();
//              }
//            }
//        });
   
    }

    @FXML
    private void AjoutCat(ActionEvent event) throws IOException {
        if (ttitre.getText().isEmpty()) {
            // JOptionPane.showMessageDialog(null, "Remplir le champs vide");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();

        } else if ( ttitre.getText().matches(".*[0-9].*")||ttitre.getText().matches(".*[%-@-.-/-!-;-,-_].*")) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez saisir uniquement des lettres ! ");
            a2.showAndWait();

        } else {

            ServiceCategorie sa = new ServiceCategorie();
            Categorie test = new Categorie(ttitre.getText());
            //AjouterPhoto(event);
            sa.ajouter(test);
            AfficherArt();
//        Stage window = primaryStage;
//        Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuArticle.fxml"));
//        Scene rec2 = new Scene(rootRec2);
//        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        app.setScene(rec2);
//        app.show();
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {
        ttitre.setText("");
    }

    @FXML
    private void Menu(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuArticle.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

}
//     @FXML
//    private void verifNom(java.awt.event.KeyEvent event) {
//        try {
//            char c = event.getKeyChar();
//      if(!(Character.isDigit(c))){
//      event.consume();
//    }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//    @FXML
//    private void verifNom(KeyEvent event) {
////         try {
////            String s=ttitre.getText();  
////char c=s.charAt(0);
////      if((Character.isDigit(c))){
////      event.consume();
////    }
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////        }
////    }
//     
//}

