/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.Comment;
import GoCampJavaFX.com.esprit.Service.ServiceComments;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
/**
 * FXML Controller class
 *
 * @author khalil
 */
public class CommentsBackController implements Initializable {

    @FXML
    private TextField tftext;
    @FXML
    private ComboBox<Integer> combo;
    @FXML
    private TableView<Comment> tableComment;
    @FXML
    private TableColumn<Comment, String> userComment;
    @FXML
    private TableColumn<Comment, String> textComment;
    @FXML
    private TableColumn<Comment, LocalDateTime> dateComment;
    @FXML
    private TableColumn<Comment, String> SubjectCom;
    @FXML
    private ComboBox<Integer> combos; 
    ServiceComments ls = new ServiceComments();  
         List<Comment> comments = new ArrayList<>();

       private final ObservableList<Comment> data = FXCollections.observableArrayList() ;  
                         private final ObservableList<Integer> dataidu = FXCollections.observableArrayList(ls.get_id_user());  
                            private final ObservableList<Integer> dataidus;  
    @FXML
    private TableColumn<Comment,Integer> idComm;

    public CommentsBackController() throws SQLException {
        this.dataidus = FXCollections.observableArrayList(ls.get_id_sub());
    }



   



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        try { 
            aff() ; 
             combo.setItems(dataidu);   
                          combos.setItems(dataidus);  

             
        } catch (SQLException ex) {
            Logger.getLogger(CommentsBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
 public void aff() throws SQLException
    { 
        data.clear(); 
        comments =ls.readAll();
        for (Comment comment : comments) {
           data.add(comment);
        }
      
               idComm.setCellValueFactory(new PropertyValueFactory<>("id"));

        userComment.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getUser().getNom())) ;  
        textComment.setCellValueFactory(new PropertyValueFactory<>("text"));
        dateComment.setCellValueFactory(new PropertyValueFactory<>("time"));   
        SubjectCom.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getSubject().getSubject())) ; 
       tableComment.setItems(data);  
         tableComment.setEditable(true);
            idComm.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())) ; 
         userComment.setCellFactory(TextFieldTableCell.forTableColumn()) ; 
         textComment.setCellFactory(TextFieldTableCell.forTableColumn()); 
         dateComment.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter())); 
         dateComment.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter())); 
         SubjectCom.setCellFactory(TextFieldTableCell.forTableColumn()) ; 

         
         
        
        
        }      

   @FXML
    private void Change_Text(TableColumn.CellEditEvent bb) throws SQLException { 
        Comment tab_Commentselected =tableComment.getSelectionModel().getSelectedItem();
     tab_Commentselected.setText(bb.getNewValue().toString()); 
     Comment s = new Comment(tab_Commentselected.getId(),tab_Commentselected.getText());
        ls.update(s); 
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {  
         String tftextS = tftext.getText() ; 
        LocalDateTime dt = LocalDateTime.now(); 
                 ComboBox tmpcmb = (ComboBox) combo;   
                 ComboBox tmpcmbs = (ComboBox) combos;   

            Comment s = new Comment(tftextS,dt,ls.rechercheu(Integer.parseInt(tmpcmb.getValue().toString())),ls.recherches(Integer.parseInt(tmpcmbs.getValue().toString())));   
            ls.ajouter(s); 
            aff() ; 
            
            tftext.setText(""); 
            
    }

    @FXML
    private void buttonSupprimer(ActionEvent event) throws SQLException { 
          tableComment.setItems(data);

             ObservableList<Comment> allDemandes,SingleDemandes ;
             allDemandes=tableComment.getItems();
             SingleDemandes=tableComment.getSelectionModel().getSelectedItems();
             Comment A = SingleDemandes.get(0);
             ServiceComments STP = new ServiceComments();
             STP.delete(A);
             SingleDemandes.forEach(allDemandes::remove); 
             aff(); 
    }  
 
    
}
