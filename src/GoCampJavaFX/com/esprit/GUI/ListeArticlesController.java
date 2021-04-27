/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

//import com.mysql.cj.protocol.Resultset;

import GoCampJavaFX.com.esprit.Entite.Article;
import GoCampJavaFX.com.esprit.Service.ServiceArticle;
import GoCampJavaFX.com.esprit.Service.ServiceCategorie;
import GoCampJavaFX.com.esprit.Util.DataBase;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListeArticlesController implements Initializable {

    @FXML
    private TextField tfrech;
    @FXML
    private TableView<Article> coltabab;
    @FXML
    private TableColumn<Article, String> ColTitre;
    @FXML
    private TableColumn<Article, String> ColDesc;
    @FXML
    private TableColumn<Article, Integer> ColVue;
    @FXML
    private TableColumn<Article, String> ColCat;
    @FXML
    private TableColumn<Article,String> Colimg;
    @FXML
    private Button btnback;
    @FXML
    private ComboBox<String> tcategorie;
    @FXML
    private TextArea tdescription;
    @FXML
    private Label image;
    @FXML
    private Button btn_ph;
    @FXML
    private ImageView img;
    @FXML
    private TextField ttitre;
    @FXML
    private Label id;
    @FXML
    private TableColumn<Article,Integer> Colid;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("PageArticle.fxml"));
    @FXML
    private TableColumn<Article, String> colprix;
    @FXML
    private TextField tprix;
    
    ObservableList<Article> ArtList = FXCollections.observableArrayList();
    FilteredList<Article> filter = new FilteredList<Article>(ArtList, e -> true);
    SortedList<Article> sort = new SortedList<Article>(filter);
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet res = null;
    
    /**
     * Initializes the controller class.
     */
      
      public void aff() {
        try {
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            Object item = coltabab.getSelectionModel().getSelectedItem();
            Article art = (Article) item;
            stage.setScene(new Scene(root));

            PageArticleController dpc = loader.getController();
            dpc.setLab_tit(art.getTitre_art());
            dpc.setLab_desc(art.getDescription_art());
            dpc.setLab_cat(art.getNomcat());
            dpc.load_pic(art.getPhoto());
            
            
            
            String str = Integer.toString(art.getLikes());
            dpc.setLab_like(str);
            dpc.setLab_img(art.getPhoto());

            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn= DataBase.getInstance().getConnection();
        ArtList= FXCollections.observableArrayList();
          ServiceCategorie cat = new ServiceCategorie();
        cat.afficher().forEach(e -> {
            tcategorie.getItems().add(e.getTitre_cat());
        });

        try {
            // TODO
            AfficherArt();
        } catch (SQLException ex) {
            Logger.getLogger(ListeArticlesController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //click button droit pour supprimer
            ContextMenu ContArticle = new ContextMenu();
            MenuItem DeleteItem = new MenuItem("Supprimer Article");
            DeleteItem.setOnAction(new EventHandler<ActionEvent>() {
                
            @Override
            public void handle(ActionEvent event) {
                /*Publication pub_supp = (Publication) tableView_Publication.getSelectionModel().getSelectedItem();
                CellEditEvent eddited_cell;*/
                Integer item = ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getId_art();
               // Article art = (Article) item;
                ServiceArticle s = new ServiceArticle();
               // System.out.println(art.toString());
                s.supprimer(item);

                try {
                    AfficherArt();
                } catch (SQLException ex) {
                    Logger.getLogger(ListeArticlesController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }});
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
        coltabab.setContextMenu(ContArticle);
        }  
    
    private void AfficherArt() throws SQLException {
        ServiceArticle srec = new ServiceArticle();
        ArtList.clear();
        String query = "select a.titre_art,a.description_art,a.nomcat,a.likes,a.photo,a.prix,a.id_art from articles a";
        pre = conn.prepareStatement(query);
        res = pre.executeQuery();
        while(res.next()){
            ArtList.add(new Article(
            res.getString("titre_art"),
            res.getString("description_art"),
            res.getString("nomcat"),
            res.getInt("likes"),
            res.getString("photo"),
            res.getInt("prix"),
            res.getInt("id_art") ));
            coltabab.setItems(ArtList);   
        }
        coltabab.setOnMouseClicked(event -> {
           id.setText(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getId_art()));
            ttitre.setText(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getTitre_art()));
            tdescription.setText(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getDescription_art()));
            tcategorie.setValue(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getNomcat()));
            String tt =ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getPhoto();
            if(tt.equalsIgnoreCase("Insérer image")){
                img.setImage(null);
                 image.setText("Insérer image");
            }else{
                Image im = new Image(tt);
                img.setImage(im);
                 image.setText(tt);
            }
            tprix.setText(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getPrix()));
        });
        ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre_art"));
        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description_art"));
        ColCat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
        ColVue.setCellValueFactory(new PropertyValueFactory<>("likes"));
        Colimg.setCellValueFactory(new PropertyValueFactory<>("photo"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        Colid.setCellValueFactory(new PropertyValueFactory<>("id_art"));
        
    }
     private void AfficherArt1(String cat) throws SQLException {
        ServiceArticle srec = new ServiceArticle();
        ArtList.clear();
        String query = "select a.titre_art,a.description_art,a.nomcat,a.likes,a.photo,a.prix from articles a where a.nomcat like '%"+cat+"%'";
        pre = conn.prepareStatement(query);
        res = pre.executeQuery();
        while(res.next()){
            ArtList.add(new Article(
            res.getString("titre_art"),
            res.getString("description_art"),
            res.getString("nomcat"),
            res.getInt("likes"),
            res.getString("photo"),
            res.getString("prix")));
            coltabab.setItems(ArtList);   
        }
        coltabab.setOnMouseClicked(event -> {
            id.setText(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getId_art()));
            ttitre.setText(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getTitre_art()));
            tdescription.setText(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getDescription_art()));
            tcategorie.setValue(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getNomcat()));
            String tt =ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getPhoto();
            if(tt.equalsIgnoreCase("Insérer image")){
                img.setImage(null);
                 image.setText("Insérer image");
            }else{
                Image im = new Image(tt);
                img.setImage(im);
                 image.setText(tt);
            }
            tprix.setText(String.valueOf(ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getPrix()));
        });
        ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre_art"));
        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description_art"));
        ColCat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
        ColVue.setCellValueFactory(new PropertyValueFactory<>("likes"));
        Colimg.setCellValueFactory(new PropertyValueFactory<>("photo"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

   

    @FXML
    public void changeTitle(CellEditEvent edittedCell) {
         Article articleSelected = coltabab.getSelectionModel().getSelectedItem();
        articleSelected.setTitre_art(edittedCell.getNewValue().toString());
        ServiceArticle s = new ServiceArticle();
        s.modifier(articleSelected);
    }

    @FXML
    public void changeDesc(CellEditEvent edittedCell) {
        Article articleSelected = coltabab.getSelectionModel().getSelectedItem();
        articleSelected.setDescription_art(edittedCell.getNewValue().toString());
        ServiceArticle s = new ServiceArticle();
        s.modifier(articleSelected);
    }

    private void triDate(ActionEvent event) {
         try {
            ObservableList<Article> ArtList = FXCollections.observableArrayList();
            //ColArt.setCellValueFactory(new PropertyValueFactory<>("id_art"));
            ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre_art"));
            ColDesc.setCellValueFactory(new PropertyValueFactory<>("description_art"));
            ColVue.setCellValueFactory(new PropertyValueFactory<>("likes"));
            ColCat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
            Colimg.setCellValueFactory(new PropertyValueFactory<>("photo"));
            

//            ServiceArticle srec = new ServiceArticle();
//            srec.trierArticleByDate().forEach(e -> {
//                ArtList.add(e);
//            });
            coltabab.setItems(ArtList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void backtomenu(ActionEvent event) throws IOException {
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuArticle.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void rechh(MouseEvent event) {
        tfrech.textProperty().addListener((obsevable,oldValue,newValue)->{
            try {
                AfficherArt1(tfrech.getText());
            } catch (SQLException ex) {
               
            }
    });
    }

    @FXML
    private void AjouterPhoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            //this.image.setText(selectedFile.getName());
            this.image.setText(selectedFile.toURI().toURL().toString());
            img.setImage(image);
        }
    }

    @FXML
    private void modif(ActionEvent event) throws SQLException {
        if (ttitre.getText().isEmpty() || tdescription.getText().isEmpty() || tcategorie.getValue() == null ) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();

        } else if (ttitre.getText().matches(".*[0-9].*") || tdescription.getText().matches(".*[0-9].*") || tdescription.getText().matches(".*[%-@-.-/-!-;-,-_].*") || ttitre.getText().matches(".*[%-@-.-/-!-;-,-_].*")) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez saisir uniquement des lettres ! ");
            a2.showAndWait();

        } else {
             ServiceArticle sa = new ServiceArticle();
             
             
            //Article test = new Article(ttitre.getText(), tauteur.getText(), Date.valueOf(d), tdescription.getText(), (String) tcategorie.getValue());
           Article test = new Article(ttitre.getText(),tdescription.getText(), (String) tcategorie.getValue(),0,image.getText(),Integer.parseInt(tprix.getText()),Integer.parseInt(id.getText()));
           sa.modifier(test);
           AfficherArt();
           
           Notifications notificationBuilder = Notifications.create()
           .title("Succes").text("An article had Changed !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
           .position(Pos.BOTTOM_RIGHT)
           .onAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            System.out.println("clicked ON ");
                       }
                   });
           notificationBuilder.darkStyle();
           notificationBuilder.showInformation();
           notificationBuilder.show();
           JOptionPane.showMessageDialog(null, "Article Modifié! ☺ ");
            ttitre.setText("");
            tdescription.setText("");
            image.setText("");
            img.setImage(null);
            id.setText("");
            tcategorie.setValue("Categorie");
             
        
        
    }
    }

    @FXML
    private void ajout(ActionEvent event) throws SQLException {
        if (ttitre.getText().isEmpty() || tdescription.getText().isEmpty() || tcategorie.getValue() == null || tprix.getText().isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();

        } else if (ttitre.getText().matches(".*[0-9].*") || tdescription.getText().matches(".*[0-9].*") || tdescription.getText().matches(".*[%-@-.-/-!-;-,-_].*") || ttitre.getText().matches(".*[%-@-.-/-!-;-,-_].*")) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez saisir uniquement des lettres ! ");
            a2.showAndWait();

        } else {
             ServiceArticle sa = new ServiceArticle();
             
             
            //Article test = new Article(ttitre.getText(), tauteur.getText(), Date.valueOf(d), tdescription.getText(), (String) tcategorie.getValue());
           Article test = new Article(ttitre.getText(),tdescription.getText(), (String) tcategorie.getValue(),0,image.getText(),tprix.getText());
           sa.ajouter(test);
           Notifications notificationBuilder = Notifications.create()
           .title("Succes").text("An article has been added !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
           .position(Pos.BOTTOM_RIGHT)
           .onAction(new EventHandler<ActionEvent>() {
             public void handle(ActionEvent event) {
            System.out.println("clicked ON ");
                       }
                   });
           notificationBuilder.darkStyle();
           notificationBuilder.showInformation();
            notificationBuilder.show();
            JOptionPane.showMessageDialog(null, "Article Ajouté! ☺ ");
            ttitre.setText("");
            tdescription.setText("");
            image.setText("");
            id.setText("");
            img.setImage(null);
            tcategorie.setValue("Categorie");
            AfficherArt();
    }
    
}
}
