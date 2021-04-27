/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;


import GoCampJavaFX.com.esprit.Entite.Comment;
import GoCampJavaFX.com.esprit.Entite.Subject;
import GoCampJavaFX.com.esprit.Entite.User;
import GoCampJavaFX.com.esprit.Service.ServiceComments;
import GoCampJavaFX.com.esprit.Service.ServiceSubject;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class SubjectsFrontController implements Initializable{

    @FXML
    private ScrollPane displaySubjects;
    
    @FXML
    private AnchorPane pannel;
    
    @FXML
    private VBox printItems;
    
    @FXML
    private Button backButton;

    @FXML
    private Label titleLabel;
    
    @FXML
    private Button addButton;
    
    ServiceSubject serviceSubject = new ServiceSubject();
    ServiceComments serviceComments = new ServiceComments();
    List<Subject> subjects = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();
    
    User curr_user = new User(1);
    
    private final ObservableList<Subject> dataSubjects = FXCollections.observableArrayList();
    private final ObservableList<Comment> dataComments = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            afficherSubjects(); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(SubjectsFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addComment(Subject subject) throws SQLException{
        printItems.setPadding(new Insets(0, 0, 0, 50));
        printItems.setSpacing(30);
        addButton.setVisible(false);
        backButton.setVisible(true);
        backButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    printItems.getChildren().clear();
                    try {
                        afficherSubjects();
                    } catch (SQLException ex) {
                        Logger.getLogger(SubjectsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        printItems.getChildren().clear();
        titleLabel.setText("Comments");
        dataComments.clear();
        comments = serviceComments.FindBySubjectId(subject.getId());
        dataComments.addAll(comments);
        VBox user_text_date = new VBox();
        user_text_date.setSpacing(20);
        HBox textzone_addbutton = new HBox();
        textzone_addbutton.setSpacing(20);
        Label username = new Label(subject.getUser().getNom() + " " + subject.getUser().getPrenom());
        username.setStyle("-fx-font-size : 18;"
                    + "-fx-font-weight : bold;"
                    + "-fx-text-fill : #077187");
        Label text = new Label(subject.getSubject());
        text.setStyle("-fx-font-size : 15");
        Label date = new Label(subject.getTime().format(DateTimeFormatter.ISO_DATE) + " " + subject.getTime().format(DateTimeFormatter.ISO_TIME));
        TextField comment_zone = new TextField();
        Button addbutton = new Button("Comment");
        addbutton.setStyle("-fx-background-color: #9ECE9A;\n" +
                                        "-fx-fill: #02182B;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: transparent;\n" +
                                        "-fx-font-family: Quicksand;\n" +
                                        "-fx-background-radius: 0;\n" +
                                        "-fx-border-radius: 0;\n" +
                                        "-fx-pref-width: 80;\n" +
                                        "-fx-pref-height: 30;\n" +
                                        "-fx-opacity: 1.0;");
        addbutton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Comment new_comment = new Comment(comment_zone.getText(), curr_user, subject);
                        serviceComments.ajouter(new_comment);
                        addComment(subject);
                    } catch (SQLException ex) {
                        Logger.getLogger(SubjectsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        textzone_addbutton.getChildren().addAll(comment_zone,addbutton);
        user_text_date.getChildren().addAll(username, text, date,textzone_addbutton);
        printItems.getChildren().add(user_text_date);
        for(Comment comment:comments){
            HBox options_details = new HBox();
            options_details.setSpacing(150);
            VBox comments_options = new VBox();
            comments_options.setSpacing(10);
            VBox comment_details = new VBox();
            comment_details.setSpacing(10);
            Label username_commentor = new Label(comment.getUser().getNom() + " " + comment.getUser().getPrenom());
            username_commentor.setStyle("-fx-font-size : 18;"
                    + "-fx-font-weight : bold;"
                    + "-fx-text-fill : #077187");
            Label text_comment = new Label(comment.getText());
            text_comment.setStyle("-fx-font-size : 15");
            Label date_comment = new Label(comment.getTime().format(DateTimeFormatter.ISO_DATE) + " " + comment.getTime().format(DateTimeFormatter.ISO_TIME));

            if (curr_user.getIdUser() == comment.getUser().getIdUser()){
                Button delete_comment = new Button("Delete");
                Button edit_comment = new Button("Edit");
                delete_comment.setStyle("-fx-background-color: #D7263D;\n" +
                                        "-fx-fill: #02182B;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: transparent;\n" +
                                        "-fx-font-family: Quicksand;\n" +
                                        "-fx-background-radius: 0;\n" +
                                        "-fx-border-radius: 0;\n" +
                                        "-fx-pref-width: 80;\n" +
                                        "-fx-pref-height: 30;\n" +
                                        "-fx-opacity: 1.0;");
                edit_comment.setStyle("-fx-background-color: #0197F6;\n" +
                                        "-fx-fill: #02182B;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: transparent;\n" +
                                        "-fx-font-family: Quicksand;\n" +
                                        "-fx-background-radius: 0;\n" +
                                        "-fx-border-radius: 0;\n" +
                                        "-fx-pref-width: 80;\n" +
                                        "-fx-pref-height: 30;\n" +
                                        "-fx-opacity: 1.0;");
                delete_comment.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Comment del_comment = new Comment(comment.getId());
                        serviceComments.delete(del_comment);
                        addComment(subject);
                    } catch (SQLException ex) {
                        Logger.getLogger(SubjectsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
                edit_comment.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        AnchorPane edit_comment = new AnchorPane();
                        edit_comment.minHeight(200);
                        edit_comment.minWidth(200);
                        VBox edit_comment_elements = new VBox();
                        Label write_new_comment = new Label("Write your new comment");
                        write_new_comment.setStyle("-fx-font-size : 18;"+
                     "-fx-font-weight : bold;"
                    + "-fx-text-fill : #077187");
                        //edit_comment_elements.setSpacing(10);
                        TextField comment_to_edit = new TextField(comment.getText());
                        Button validate = new Button("Post");
                        validate.setStyle("-fx-background-color: #9ECE9A;\n" +
                                        "-fx-fill: #02182B;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: transparent;\n" +
                                        "-fx-font-family: Quicksand;\n" +
                                        "-fx-background-radius: 0;\n" +
                                        "-fx-border-radius: 0;\n" +
                                        "-fx-pref-width: 80;\n" +
                                        "-fx-pref-height: 30;\n" +
                                        "-fx-opacity: 1.0;");
                        validate.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    Comment update_comment = new Comment(comment.getId(),comment_to_edit.getText());
                                    serviceComments.update(update_comment);
                                    addComment(subject);
                                } catch (SQLException ex) {
                                    Logger.getLogger(SubjectsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        
                        edit_comment_elements.getChildren().addAll(write_new_comment,comment_to_edit,validate);
                        edit_comment_elements.setSpacing(25);
                        edit_comment.getChildren().add(edit_comment_elements);
                        
                        printItems.getChildren().clear();
                        printItems.getChildren().add(edit_comment);
                }
            });
                comments_options.getChildren().addAll(edit_comment, delete_comment);
            }

            comment_details.getChildren().addAll(username_commentor, text_comment,date_comment);
            options_details.getChildren().addAll(comment_details, comments_options);
            printItems.getChildren().add(options_details);            
        }
    
    }
    
    public void afficherSubjects() throws SQLException{
        printItems.setPadding(new Insets(0, 0, 0, 50));
        backButton.setVisible(false);
        addButton.setVisible(true);
        printItems.getChildren().clear();
        titleLabel.setText("Subject Feed");
        dataSubjects.clear();
        subjects = serviceSubject.readAll();
        dataSubjects.addAll(subjects);
        printItems.setSpacing(30);
        
        
        addButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    AnchorPane add_subject = new AnchorPane();
                    add_subject.minHeight(200);
                    add_subject.minWidth(200);
                    VBox add_subject_elements = new VBox();
                    add_subject_elements.setSpacing(50);
                    TextField subject_to_add = new TextField();
                    Button validate = new Button("Post");
                    Label lbl_new_subject = new Label("Add new subject");
                        lbl_new_subject.setStyle("-fx-font-size : 18;"
                    + "-fx-font-weight : bold;"
                    + "-fx-text-fill : #077187");      
                        validate.setStyle("-fx-background-color: #9ECE9A;\n" +
                                        "-fx-fill: #02182B;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: transparent;\n" +
                                        "-fx-font-family: Quicksand;\n" +
                                        "-fx-background-radius: 0;\n" +
                                        "-fx-border-radius: 0;\n" +
                                        "-fx-pref-width: 80;\n" +
                                        "-fx-pref-height: 30;\n" +
                                        "-fx-opacity: 1.0;");
                        validate.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    Subject add_subject = new Subject(subject_to_add.getText(), curr_user);
                                    serviceSubject.ajouter(add_subject);
                                    afficherSubjects();
                                } catch (SQLException ex) {
                                    Logger.getLogger(SubjectsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        
                        add_subject_elements.getChildren().addAll(lbl_new_subject,subject_to_add,validate);
                        add_subject_elements.setSpacing(50);
                        add_subject.getChildren().add(add_subject_elements);
                        
                        printItems.getChildren().clear();
                        printItems.getChildren().add(add_subject);
                }
            });
            
        printItems.setAlignment(Pos.CENTER_LEFT);
        for(Subject subject:subjects){
            VBox user_text_date = new VBox();
            user_text_date.setSpacing(20);
            VBox nb_com_add_com = new VBox();
            nb_com_add_com.setSpacing(10);
            VBox edit_remove = new VBox();
            edit_remove.setSpacing(10);
            HBox combine_vboxes = new HBox();
            combine_vboxes.setSpacing(150);
            Label username = new Label(subject.getUser().getNom() + " " + subject.getUser().getPrenom());
            username.setStyle("-fx-font-size : 18;"
                    + "-fx-font-weight : bold;"
                    + "-fx-text-fill : #077187");
            Label text = new Label(subject.getSubject());
            text.setStyle("-fx-font-size : 15");
            Label date = new Label(subject.getTime().format(DateTimeFormatter.ISO_DATE) + " " + subject.getTime().format(DateTimeFormatter.ISO_TIME));
            Label nb_comments = new Label("Comments ("+String.valueOf(serviceComments.Count(subject.getId()))+")");
            
            Button addComment = new Button("Comment");
            
            combine_vboxes.getChildren().addAll(user_text_date, nb_com_add_com);  
            printItems.getChildren().add(combine_vboxes);
            if (curr_user.getIdUser() == subject.getUser().getIdUser()){
                Button removeSubject = new Button("Delete");
                Button editSubject = new Button("Edit");
                removeSubject.setStyle("-fx-background-color: #D7263D;\n" +
                                        "-fx-fill: #02182B;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: transparent;\n" +
                                        "-fx-font-family: Quicksand;\n" +
                                        "-fx-background-radius: 0;\n" +
                                        "-fx-border-radius: 0;\n" +
                                        "-fx-pref-width: 80;\n" +
                                        "-fx-pref-height: 30;\n" +
                                        "-fx-opacity: 1.0;");
                editSubject.setStyle("-fx-background-color: #0197F6;\n" +
                                        "-fx-fill: #02182B;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: transparent;\n" +
                                        "-fx-font-family: Quicksand;\n" +
                                        "-fx-background-radius: 0;\n" +
                                        "-fx-border-radius: 0;\n" +
                                        "-fx-pref-width: 80;\n" +
                                        "-fx-pref-height: 30;\n" +
                                        "-fx-opacity: 1.0;");
                removeSubject.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Subject del_subject = new Subject(subject.getId());
                            serviceSubject.delete(del_subject);
                            afficherSubjects();      
                        } catch (SQLException ex) {
                            Logger.getLogger(SubjectsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                editSubject.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        AnchorPane edit_subject = new AnchorPane();
                        edit_subject.minHeight(200);
                        edit_subject.minWidth(200);
                        VBox edit_subject_elements = new VBox();
                        Label lbl_new_subject = new Label("Edit subject");
                        lbl_new_subject.setStyle("-fx-font-size : 18;"
                    + "-fx-font-weight : bold;"
                    + "-fx-text-fill : #077187");
                        edit_subject_elements.setSpacing(50);
                        TextField subject_to_edit = new TextField(subject.getSubject());
                        Button validate = new Button("Post");                 
                        validate.setStyle("-fx-background-color: #9ECE9A;\n" +
                                        "-fx-fill: #02182B;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: transparent;\n" +
                                        "-fx-font-family: Quicksand;\n" +
                                        "-fx-background-radius: 0;\n" +
                                        "-fx-border-radius: 0;\n" +
                                        "-fx-pref-width: 80;\n" +
                                        "-fx-pref-height: 30;\n" +
                                        "-fx-opacity: 1.0;");
                        validate.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    Subject update_subject = new Subject(subject.getId(),subject_to_edit.getText());
                                    serviceSubject.update(update_subject);
                                    afficherSubjects();
                                } catch (SQLException ex) {
                                    Logger.getLogger(SubjectsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });                        
                        edit_subject_elements.getChildren().addAll(lbl_new_subject,subject_to_edit,validate);
                        edit_subject.getChildren().add(edit_subject_elements);
                        
                        printItems.getChildren().clear();
                        printItems.getChildren().add(edit_subject);
                }
            });
            edit_remove.getChildren().addAll(editSubject, removeSubject);
            }
            addComment.setStyle("-fx-background-color: #9ECE9A;\n" +
                                        "-fx-fill: #02182B;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: transparent;\n" +
                                        "-fx-font-family: Quicksand;\n" +
                                        "-fx-background-radius: 0;\n" +
                                        "-fx-border-radius: 0;\n" +
                                        "-fx-pref-width: 80;\n" +
                                        "-fx-pref-height: 30;\n" +
                                        "-fx-opacity: 1.0;");
            
            addComment.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        backButton.setVisible(false);
                        addComment(subject);
                    } catch (SQLException ex) {
                        Logger.getLogger(SubjectsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            user_text_date.getChildren().addAll(username, text, date);
            nb_com_add_com.getChildren().addAll(edit_remove,nb_comments,addComment);
        }
    }
    
}
