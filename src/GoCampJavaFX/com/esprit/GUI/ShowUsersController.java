/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Entite.User;
import GoCampJavaFX.com.esprit.Service.ServiceMaterial;
import GoCampJavaFX.com.esprit.Service.ServiceUser;
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
import static java.lang.Integer.parseInt;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Rezigue
 */
public class ShowUsersController implements Initializable {
    private Statement ste;
    PreparedStatement stm;
           Connection con = DataBase.getInstance().getConnection();
@FXML
    private TextField tfnom;

    @FXML
    private TextField tfpassword;

    @FXML
    private TextField tfrole;

    @FXML
    private TextField tfsexe;

    @FXML
    private TextField tfemail;
     @FXML
    private TextField tfid;

    @FXML
    private TextField tfprenom;
      @FXML
    private TextField tfrch;

    @FXML
    private Button btnr;

    @FXML
    void rchOnAction(ActionEvent event) {
       ObservableList<User> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select  * from user   where email like '%"+tfrch.getText()+"%' or sexe like '%"+tfrch.getText()+"%' or role like '%"+tfrch.getText()+"%'  " ;
           
                     
              PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
             list.add(new User( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
                 table.setItems(list);

         idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
         password.setCellValueFactory(new PropertyValueFactory<>("password"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));


    }
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> idUser;

    @FXML
    private TableColumn<User, String> nom;

    @FXML
    private TableColumn<User, String> prenom;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, String> sexe;
    @FXML
    private TableColumn<User, String> role;
       @FXML
    private Button btnDelete;
       
     @FXML
    private void refrechOnAction(ActionEvent event) {
          ObservableList<User> List = FXCollections.observableArrayList();
        try {
            
            String requete = "select  * from user ";
           
              PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
             List.add(new User( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
       idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
         password.setCellValueFactory(new PropertyValueFactory<>("password"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
          sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));


         table.setItems(List);
    
    }

    
        ServiceUser su = new ServiceUser();


    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<User> List = FXCollections.observableArrayList(
    su.ShowUser()
    );
     idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
         password.setCellValueFactory(new PropertyValueFactory<>("password"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));


         table.setItems(List);
              btnDelete.setOnAction(e -> {
    User selectedItem = table.getSelectionModel().getSelectedItem();
            try {
                DeleteUser(selectedItem);
            } catch (Exception ex) {
                Logger.getLogger(ShowUsersController.class.getName()).log(Level.SEVERE, null, ex);
            }
    table.getItems().remove(selectedItem);
        System.out.println("User supp");
});
    }    
    
    
    
    
       public void DeleteUser(User u) throws IOException, Exception {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer Votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           su.delete(u);
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ShowUsers.fxml"));
                

        }

    }
         @FXML
    private void getselected(MouseEvent event) {
         User u = table.getSelectionModel().getSelectedItem();
        int index = table.getSelectionModel().getSelectedIndex();
        if (u == null) {
            return;
        }
        if (index <= -1) {

            return;

        } 
        
       tfid.setText(idUser.getCellData(index).toString());

        tfnom.setText(nom.getCellData(index).toString());
        tfprenom.setText(prenom.getCellData(index).toString());
        tfemail.setText(email.getCellData(index).toString());
        tfpassword.setText(password.getCellData(index).toString());
        tfrole.setText(role.getCellData(index).toString());
        tfsexe.setText(sexe.getCellData(index).toString());

      
    }
        @FXML
    private Button btnmodifier;

    @FXML
    void modifierOnAction(ActionEvent event) {
        int idUser=parseInt(tfid.getText());
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String email=tfemail.getText();
        String password = tfpassword.getText();
        String role = tfrole.getText();
        String sexe = tfsexe.getText();

        User u1=new User(idUser, nom,prenom,email,password,role,sexe );
       
        su.modifier(u1); 
        refrechOnAction(event);

    }
    
@FXML
    void PDF(ActionEvent event) throws DocumentException, IOException{
        
          Document doc = new Document();
         
    try {
        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Rezigue\\Desktop\\Utilisateurs.pdf"));
        doc.open();
        doc.add(new Paragraph("                        ")) ;  
        doc.add(new Paragraph(" Liste Des Uitlisateur ")) ;   
        doc.add(new Paragraph("  ")) ;   
        
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);  
        PdfPCell cell;
        /////////////////////////////////////////////////////////////////////////////////////////////
        
        cell = new PdfPCell(new Phrase("id",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase("nom",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase("prenom",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase("email",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase("password",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
               cell = new PdfPCell(new Phrase("role",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
               cell = new PdfPCell(new Phrase("sexe",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        /////////////////////////////////////////////////////////////////////////////////////////////
            String requete = "select * from user";
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
        
                cell = new PdfPCell(new Phrase(rs.getString(4),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(rs.getString(5),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(rs.getString(6),FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(rs.getString(7),FontFactory.getFont("Comic Sans MS",12)));
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
        Desktop.getDesktop().open(new File("C:\\Users\\Rezigue\\Desktop\\Utilisateurs.pdf"));
                
                
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ShowMaterialController_back.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}       
