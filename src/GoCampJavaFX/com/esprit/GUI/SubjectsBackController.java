/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.Subject;
import GoCampJavaFX.com.esprit.Service.ServiceSubject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView; 

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.FormatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import javafx.util.converter.NumberStringConverter;
/**
 * FXML Controller class
 *
 * @author khalil
 */
public class SubjectsBackController implements Initializable {

    @FXML
    private TableView<Subject> tableSubjects;
    @FXML
   private TableColumn<Subject, String> userSubject;
    @FXML
    private TableColumn<Subject, String> textSubject;
    @FXML
    private TableColumn<Subject, LocalDateTime> dateSubject; 
     ServiceSubject ls = new ServiceSubject();  
         List<Subject> subjects = new ArrayList<>();

       private final ObservableList<Subject> data = FXCollections.observableArrayList() ; 
                  private final ObservableList<Integer> dataidu = FXCollections.observableArrayList(ls.get_id_user()); 

    @FXML
    private TextField tftext;
    @FXML
    private ComboBox<Integer> combo;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<Subject, Integer> idSubject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        try { 
                    combo.setItems(dataidu); 
            aff(); 
                    RechercheAV(); 

        } catch (SQLException ex) {
            Logger.getLogger(SubjectsBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    
    public void aff() throws SQLException
    { 
        data.clear(); 
        subjects =ls.readAll();
        for (Subject subject : subjects) {
           data.add(subject);
        } 
       idSubject.setCellValueFactory(new PropertyValueFactory<>("id"));
        userSubject.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getUser().getNom())) ;  
        textSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        dateSubject.setCellValueFactory(new PropertyValueFactory<>("time"));  
       tableSubjects.setItems(data);  
         tableSubjects.setEditable(true); 
         idSubject.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())) ; 
         userSubject.setCellFactory(TextFieldTableCell.forTableColumn()) ; 
         textSubject.setCellFactory(TextFieldTableCell.forTableColumn()); 
         dateSubject.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
         
        
        
        }   
    public void RechercheAV(){
        FilteredList<Subject> filteredData = new FilteredList<>(data, b -> true);
		
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(tab_subject -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
			
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(tab_subject.getSubject()).indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
				else if (String.valueOf(tab_subject.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false;
			});
		});
		
		
		SortedList<Subject> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tableSubjects.comparatorProperty());
		
		
		tableSubjects.setItems(sortedData);
   
 } 

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
             List<String> mails = ls.getUsersMails()  ; 

   String tftextS = tftext.getText() ; 
        LocalDateTime d = LocalDateTime.now(); 
                 ComboBox tmpcmb = (ComboBox) combo;  
            Subject s = new Subject(tftextS,d, ls.rechercheu(Integer.parseInt(tmpcmb.getValue().toString())));   
            ls.ajouter(s); 
            aff() ; 
            RechercheAV(); 
             String bd = "Hey !!! We have a new story just for you join us now to check it out we would love to hear your thoughts"; 
         for(String mail : mails) 
         {
          SendMail.sendMail(mail,"Subject",bd);   
         }
            tftext.setText(""); 
            
   }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException { 
        tableSubjects.setItems(data);

             ObservableList<Subject> allDemandes,SingleDemandes ;
             allDemandes=tableSubjects.getItems();
             SingleDemandes=tableSubjects.getSelectionModel().getSelectedItems();
             Subject A = SingleDemandes.get(0);
             ServiceSubject STP = new ServiceSubject();
             STP.delete(A);
             SingleDemandes.forEach(allDemandes::remove); 
             aff(); 
    }

    @FXML
    private void Change_Text(TableColumn.CellEditEvent bb) throws SQLException {  
           Subject tab_Subjectselected =tableSubjects.getSelectionModel().getSelectedItem();
     tab_Subjectselected.setSubject(bb.getNewValue().toString()); 
     Subject s = new Subject(tab_Subjectselected.getId(),tab_Subjectselected.getSubject());
        ls.update(s); 
        
    }
}
