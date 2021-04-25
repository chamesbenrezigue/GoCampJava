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
         table.setItems(List);
           
    }
      
     }
//     public void PDF() throws FileNotFoundException, Exception{
//           File out = new File ("Sample.pdf");
//            FileOutputStream fos = new FileOutputStream(out);
//            PDF pdf = new PDF(fos);
//            Page page = new Page(pdf, A4.PORTRAIT);
//            Font f1 = new Font(pdf,CoreFont.HELVETICA_BOLD);
//            Font f2 = new Font(pdf,CoreFont.HELVETICA);
//            Table tablee = new Table();
//            List<List<Cell>> tableData = new ArrayList<List<Cell>>();
//            List<Cell> tableeRow = new ArrayList<Cell>();
//            
//            Cell cell = new Cell(f1,id.getText());
//            tableeRow.add(cell);
//            
//             cell = new Cell(f1,name.getText());
//            tableeRow.add(cell);
//            
//            cell = new Cell(f1,description.getText());          
//            tableeRow.add(cell);
//            
//            cell = new Cell(f1,price.getText());
//            tableeRow.add(cell);
//            
//            tableData.add(tableeRow);
//            List<Material> items = table.getItems();
//            
//                for(Material material : items){
//                       Cell id=new Cell(f2,String.valueOf(material.getId()));
//                       String s =String.valueOf(material.getPrice());
//                       Cell price=new Cell(f2,s);
//
//                    Cell name=new Cell(f2,material.getName());
//                    Cell description=new Cell(f2,material.getDescription());
//                    tableeRow=new ArrayList<Cell>();
//                                        tableeRow.add(id);
//                    tableeRow.add(price);
//
//                    tableeRow.add(name);
//                    tableeRow.add(description);
//                            tableData.add(tableeRow);
//                }
//                tablee.setData(tableData);
//                tablee.setPosition(70f,60f);
//                tablee.setColumnWidth(0,100f);
//                 tablee.setColumnWidth(0,100f);
//                tablee.setColumnWidth(0,100f);
//                tablee.setColumnWidth(0,100f);
//                while(true){
//                    tablee.drawOn(page);
//                    if(!tablee.hasMoreData()){
//                        tablee.resetRenderedPagesCount();
//                        break;
//                    }
//                    page=new Page(pdf,A4.PORTRAIT);
//                }
//                    pdf.flush();
//                    fos.close();
//                    System.out.println("Saved to"+out.getAbsolutePath());
//                    
//            
//            
//            
//     }
// 

