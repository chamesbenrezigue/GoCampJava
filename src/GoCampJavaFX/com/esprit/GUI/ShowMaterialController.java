/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;


import GoCampJavaFX.com.esprit.Entite.Material;
import GoCampJavaFX.com.esprit.Service.ServiceMaterial;
import GoCampJavaFX.com.esprit.Service.ServiceReservation;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ShowMaterialController implements Initializable {
    
@FXML
    private TextField filterField;
    @FXML
    private TableView<Material> table;

    @FXML
    private TableColumn<Material, Integer> id;

    @FXML
    private TableColumn<Material, String> name;

    @FXML
    private TableColumn<Material, String> description;

    @FXML
    private TableColumn<Material, Float> price;
    
    @FXML
    private TableColumn<Material, Integer> quantity;
  
 
    @FXML
    private TextField tfShow;
     @FXML
    private Button btnSaveToPDF;
     @FXML
    private Button btnRent;
     Connection con = DataBase.getInstance().getConnection();
    @FXML
    private TableColumn<Material, String> image;

       @FXML
    void AddReservation(ActionEvent event)throws IOException {
        
                         btnRent.setOnAction(e -> {
                             
     Material selectedItem = table.getSelectionModel().getSelectedItem();

int m = selectedItem.getId();
                             try {
                                 if (selectedItem.getQuantity()>sr.countMaterialReserver(selectedItem.getId())){
                                     FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ReservationFront.fxml"));
                                     Parent root;
                                     try {
                                         root = LOADER.load();
                                         
                                         Scene sc = new Scene(root);
                                         ReservationFrontController cntr = LOADER.getController();
                                         cntr.initData(m);
                                         
                                         Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
                                         
                                         window.setScene(sc);
                                         window.show();
                                     } catch (IOException ex) {
                                         Logger.getLogger(ShowMaterialController.class.getName()).log(Level.SEVERE, null, ex);
                                     }}
                                 else{
                                     
                                       Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.setTitle("Reservation ");
                                                alert.setContentText("le nombre de matériel réservé a atteint son maximum");
                                                Optional<ButtonType> result = alert.showAndWait();
                                 }
                             
                             
                             } catch (SQLException ex) {
                                 Logger.getLogger(ShowMaterialController.class.getName()).log(Level.SEVERE, null, ex);
                             }
    });}

   
    
    ServiceMaterial sm = new ServiceMaterial();
    ServiceReservation sr = new ServiceReservation();
    


    ObservableList<Material> List = FXCollections.observableArrayList(
    sm.ShowMaterial()
    );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                image.setCellValueFactory(new PropertyValueFactory<>("image"));


         table.setItems(List);
         
        FilteredList<Material> filteredData = new FilteredList<>(List, b->true);
    filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(Material -> {
            if (newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if ( Material.getName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }else if (Material.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1){
             return true;   
            }
            else if (String.valueOf(Material.getPrice()).indexOf(lowerCaseFilter)!= -1)
                return true ;
            else 
                return false;
        });
    });
SortedList<Material> sortedData = new SortedList<>(filteredData);
     sortedData.comparatorProperty().bind(table.comparatorProperty());
     table.setItems(sortedData);  
       
    } 
  @FXML
    void PDF(ActionEvent event) throws DocumentException, IOException{
        System.out.println(name.getCellValueFactory());
          Document doc = new Document();
         
    try {
        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Rezigue\\Desktop\\Materiel.pdf"));
        doc.open();
        doc.add(new Paragraph("  ")) ;  
        doc.add(new Paragraph(" Liste Des Materials ")) ;   
        doc.add(new Paragraph("  ")) ;   
        
        PdfPTable table = new PdfPTable(5);
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
        
                cell = new PdfPCell(new Phrase("Price",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase("Quantity",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        /////////////////////////////////////////////////////////////////////////////////////////////
            String requete = "select * from material";
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
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(rs.getString(2),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(rs.getString(3),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(String.valueOf(rs.getFloat(4)),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(String.valueOf(rs.getInt(5)),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
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
        Desktop.getDesktop().open(new File("C:\\Users\\Rezigue\\Desktop\\Materiel.pdf"));
                
                
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ShowMaterialController_back.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
     }


