/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import GoCampJavaFX.com.esprit.Entite.evenement;
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
import GoCampJavaFX.com.esprit.Service.EvenementService;
import GoCampJavaFX.com.esprit.Service.ServiceMaterial;
import GoCampJavaFX.com.esprit.Util.DataBase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author HAMMOUDA
 */
public class EvenementController implements Initializable {
               Connection con = DataBase.getInstance().getConnection();
      ObservableList<String> typeEvent = FXCollections.observableArrayList("Camping","Rando");


    @FXML
    private TextField ev_nom;
    @FXML
    private TextField ev_desc;
    @FXML
    private TextField ev_prix;

    @FXML
    private ChoiceBox <String>ev_type;
    @FXML
    private Button btnajouter;
    @FXML
    private DatePicker ev_date;
    @FXML
    private DatePicker ev_dateend;
    
    
     
    
    EvenementService cr = new EvenementService();
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
    private TableColumn<evenement, String> event_type;
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
   
  
    ObservableList<evenement> data = FXCollections.observableArrayList(cr.getAll());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            System.out.println(data);

                ev_type.setValue("Camping");
                ev_type.setItems(typeEvent);
        
         // TODO
   
        ev_name.setCellValueFactory(new PropertyValueFactory("nom_event"));
        ev_descr.setCellValueFactory(new PropertyValueFactory("description_event"));
        event_date.setCellValueFactory(new PropertyValueFactory("date"));
        event_date_end.setCellValueFactory(new PropertyValueFactory("dateEnd"));
        event_prix.setCellValueFactory(new PropertyValueFactory("prix_event"));
        event_type.setCellValueFactory(new PropertyValueFactory("type"));
         event_image.setCellValueFactory(new PropertyValueFactory("image"));
         
        table.setItems(data);
      

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    evenement e =table.getSelectionModel().getSelectedItem();
                     System.out.println();
                     ev_nom.setText(e.getNom_event());
                     ev_desc.setText(e.getDescription_event());
                     ev_date.setValue(e.getDate().toLocalDate());
                     ev_dateend.setValue(e.getDateEnd().toLocalDate());
                     ev_prix.setText(e.getPrix_event());
                     ev_image.setText(e.getImage());
                     ev_type.setValue(e.getType());
                 
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
            dialogW.setContentText("veuillez inserer la date Start  s'il vous plait!");
            dialogW.showAndWait();
        }   
                    else if (ev_dateend.getValue()== null ){
		Alert dialogW = new Alert(Alert.AlertType.WARNING);
		dialogW.setTitle("A warning");
 		dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez inserer la date End  s'il vous plait!");
            dialogW.showAndWait();
        } 
        
             else if (ev_prix.getText() == null || ev_prix.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de prix s'il vous plait!");
            dialogW.showAndWait();
            } 
              else if (ev_type.getValue()== null || ev_type.getValue().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de nombre de place s'il vous plait!");
            dialogW.showAndWait();
            } 
   
              
              else {
                   evenement e = new evenement(ev_nom.getText(), ev_desc.getText(),Date.valueOf(ev_date.getValue()),Date.valueOf(ev_dateend.getValue()),ev_prix.getText(),ev_type.getValue(),ev_image.getText());
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
        ev_dateend.setValue(null);
        ev_prix.clear();
        ev_type.setValue(null);
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
            cr.update(new GoCampJavaFX.com.esprit.Entite.evenement(ev_nom.getText(), ev_desc.getText(),Date.valueOf(ev_date.getValue()),Date.valueOf(ev_dateend.getValue()),ev_prix.getText(),ev_type.getValue(),ev_image.getText()), table.getSelectionModel().getSelectedItem().getId());
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
 @FXML
    void PDF(ActionEvent event) throws DocumentException, IOException{
        
          Document doc = new Document();
         
    try {
        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Rezigue\\Desktop\\Evenments.pdf"));
        doc.open();
        doc.add(new Paragraph("                        ")) ;  
        doc.add(new Paragraph(" Liste Des Evenements ")) ;   
        doc.add(new Paragraph("  ")) ;   
        
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);  
        PdfPCell cell;
        /////////////////////////////////////////////////////////////////////////////////////////////
        
        cell = new PdfPCell(new Phrase("id",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase("Name",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase("Description",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase("Start",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("End",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase("Prix",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
               cell = new PdfPCell(new Phrase("Type",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
;
        /////////////////////////////////////////////////////////////////////////////////////////////
            String requete = "select * from event";
        try {
            PreparedStatement pst = DataBase.getInstance().getConnection()
                    .prepareStatement(requete);
            Statement st;
            ResultSet rs;
            try {
                st=con.createStatement();
                rs = pst.executeQuery(requete);           
                while (rs.next()) {
                                   cell = new PdfPCell(new Phrase((String.valueOf(rs.getInt(1))),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(rs.getString(2),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(rs.getString(3),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(rs.getDate(4).toString(),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(rs.getDate(5).toString(),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(rs.getString(7),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(String.valueOf(rs.getInt(6)),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
    
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMaterial.class.getName()).log(Level.SEVERE, null, ex);
       }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        doc.add(table);
        
        doc.close();
        Desktop.getDesktop().open(new File("C:\\Users\\Rezigue\\Desktop\\Evenments.pdf"));
                
                
    } catch (FileNotFoundException ex) {
        Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
   

  }
  
    

