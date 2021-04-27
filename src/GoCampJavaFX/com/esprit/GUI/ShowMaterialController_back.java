/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;


import GoCampJavaFX.com.esprit.Entite.Material;
import GoCampJavaFX.com.esprit.Entite.Reservation;
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
import java.io.*;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import static javax.print.DocFlavor.INPUT_STREAM.PDF;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ShowMaterialController_back implements Initializable {
    
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
    private Button delete;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField tfShow;
     @FXML
    private Button btnSaveToPDF;
         @FXML
    private Button ref;
   

    @FXML
    void RedirectAdd(ActionEvent event) throws IOException {
  FXMLLoader LOADER = new FXMLLoader(getClass().getResource("AddMaterial.fxml"));
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      AddMaterialController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
   
    }
    
    @FXML
    void bouttonDel(ActionEvent event) {
         delete.setOnAction(e -> {
    Material selectedItem = table.getSelectionModel().getSelectedItem();
            try {
                DeleteMaterial(selectedItem);
            } catch (Exception ex) {
                Logger.getLogger(ShowMaterialController_back.class.getName()).log(Level.SEVERE, null, ex);
            }
    table.getItems().remove(selectedItem);
        System.out.println("Materiel supp");

    });
         }
    
    ServiceMaterial sm = new ServiceMaterial();
    ServiceReservation sr = new ServiceReservation();

Connection con = DataBase.getInstance().getConnection();

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Material> List = FXCollections.observableArrayList(
    sm.ShowMaterial()
    );
    

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

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
  
       
     public void DeleteMaterial(Material m) throws IOException, Exception {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer le matériel ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           sm.delete(m);
        
        }}
        
        @FXML
    void refrechOnAction(ActionEvent event) {
ObservableList<Material> List = FXCollections.observableArrayList();
        try {
            
            String requete = "select  * from material ";
           
              PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
             List.add(new Material( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),rs.getInt(5)));
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
  id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
         table.setItems(List);}
           
    
      
     

//    @FXML
//    void PDF(ActionEvent event)  throws DocumentException {
//    System.out.println("PDF");
//             try {
//            String file_name = ("LISTC.pdf");
//            Document document = new Document();
//           
//            PdfWriter.getInstance(document, new FileOutputStream(file_name));
//           
//            document.open();
//           
//            document.addTitle("Liste Materials ");
//            Paragraph paraHeader2= new Paragraph("                                                                   ".concat("Vos Materials "));
//             document.add(paraHeader2);
//             Paragraph paraHeader3= new Paragraph("             "
//                     + ""
//                     + ""
//             );
//               document.add(paraHeader3);
//                 Paragraph paraHeader1 = new Paragraph((("id".concat("            ")).concat("Name".concat("            ")).concat("Description".concat("           ")).concat("Price".concat("            ")).concat("quantity".concat("            "))));
//            document.add(paraHeader1);
//                    ObservableList<Message> list =pcr.getMessage();
//                   while(list)
//
//           
//                  ObservableList<Message> ProductList = FXCollections.observableArrayList();
//        String requete = "select * from material";
//        try {
//            PreparedStatement pst = DataBase.getInstance().getConnection()
//                    .prepareStatement(requete);
//            Statement st;
//            ResultSet rs;
//            try {
//                System.out.println("AHAYYYAA!!!!");
//                st=conn.createStatement();
//                System.out.println("AHAYYYAA222!!!!");
//                rs = pst.executeQuery(requete);
//             
//                Message p;
//
//                while (rs.next()) {
//                    Message msg = new Message(rs.getInt("id_msg"),rs.getString("message"), rs.getString("id_posteur"),rs.getDate("date_heure_post"));
//                    ProductList.add(msg);
//                       Paragraph paraHeader11 = new Paragraph((rs.getInt(1))+("            ").concat(rs.getString(2)).concat("              ")+(rs.getString(3))+("              ")+(String.valueOf(rs.getFloat(4)))+("              ")+(rs.getInt(5)));
//            document.add(paraHeader11);
//                }
//
//            } catch (Exception ex) {
//                System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
//                ex.printStackTrace();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceMaterial.class.getName()).log(Level.SEVERE, null, ex);
//        }
//                 
//   
//           
//           
//            document.close();
//            Desktop.getDesktop().open(new File(file_name));
//        } catch (IOException ex) {
//            Logger.getLogger(ShowMaterialController_back.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }}
//                    
     
    
    
    
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
    
    
    
    
        @FXML
    void showRS(ActionEvent event) throws IOException {

 FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ShowReservationBack.fxml"));
 Parent root;
                    try {
                        root = LOADER.load();
                  
                    Scene sc = new Scene(root);
                     ShowReservationBackController cntr = LOADER.getController();
                    
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                          } catch (IOException ex) {
                        Logger.getLogger(ShowMaterialController.class.getName()).log(Level.SEVERE, null, ex);
                    }
}  

}
            
     

 

