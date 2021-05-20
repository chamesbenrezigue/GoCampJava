/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Service;

import GoCampJavaFX.com.esprit.IService.IEvenementService;
import GoCampJavaFX.com.esprit.Entite.evenement;
import GoCampJavaFX.com.esprit.Entite.evenementreservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import GoCampJavaFX.com.esprit.Util.DataBase;
import java.util.ArrayList;

/**
 *
 * @author HAMMOUDA
 */
public class EvenementService implements IEvenementService{
   Connection cnx;
      private Statement ste;
    PreparedStatement stm;

    public EvenementService() {
        cnx = DataBase.getInstance().getConnection();}
    @Override
    public void createEvenement(evenement e) {
           try {
            String requete2 = "INSERT INTO event (titre,description,start,end,prix,type,image) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = DataBase.getInstance().con.prepareStatement(requete2);
            pst.setString(1, e.getNom_event());
            pst.setString(2, e.getDescription_event());
            pst.setDate(3,e.getDate());
            pst.setDate(4,e.getDateEnd());      
            pst.setString(5, e.getPrix_event());
            pst.setString(6, e.getType());
            pst.setString(7, e.getImage());
            pst.executeUpdate();
            System.out.println("Event added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

      @Override
    public void update(evenement e,int id) {
 try {
            String requete3 = "UPDATE event SET titre=?,description=?,start=?,end=?,prix=?,type=? , image=? WHERE id=?";
            PreparedStatement pst2 = DataBase.getInstance().con.prepareStatement(requete3);
            pst2.setInt(8,id);
            pst2.setString(1, e.getNom_event());
            pst2.setString(2, e.getDescription_event());
            pst2.setDate(3,e.getDate());
            pst2.setDate(4,e.getDateEnd());
            pst2.setString(5, e.getPrix_event());
            pst2.setString(6, e.getType());
            pst2.setString(7, e.getImage());

            
            pst2.executeUpdate();
            System.out.println("Event updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void delete(int id) {
 try {
            String requete7 = "DELETE FROM event WHERE id=?";
            PreparedStatement pst7 = DataBase.getInstance().con.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
    
     @Override
    public List<evenement> getAll() {
ObservableList<evenement> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM event";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                evenement e = new evenement();
                e.setId(rs.getInt(1));
                e.setNom_event(rs.getString("titre"));
                  e.setDescription_event(rs.getString("description"));
                    e.setDate(rs.getDate("start"));
                    e.setDateEnd(rs.getDate("end"));
                    e.setPrix_event(rs.getString("prix"));
                    e.setType(rs.getString("type"));
                    e.setImage(rs.getString("image"));

                  

                myList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }
  public ObservableList<evenement> FindEvent() {

        ObservableList<evenement> list = FXCollections.observableArrayList();
        try {
            String requete4 = "SELECT * FROM event";
            Statement st5 = DataBase.getInstance().con.createStatement();
            ResultSet rs = st5.executeQuery(requete4);
            while (rs.next()) {
                evenement e = new evenement();
                e.setId(rs.getInt("id"));
                e.setDate(rs.getDate("start"));   
                e.setDateEnd(rs.getDate("end"));              
                e.setNom_event(rs.getString("titre"));
                e.setDescription_event(rs.getString("description"));
                e.setPrix_event(rs.getString("prix"));
                e.setType(rs.getString("type"));
              e.setImage(rs.getString("image"));
                list.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return list;

    }
  
  
    public void AddJoindre(evenementreservation r)  throws SQLException{
        ste = cnx.createStatement();
        String sql="INSERT INTO `reservation`(`id`, `nom`, `prenom`, `event`, `nbrplace`)VALUES (NULL, '" + r.getNom()+ "' , '" + r.getPrenom()+ "' , '" + r.getEvent()+"', '" + r.getNbrplace()+"');";
        ste.executeUpdate(sql);
        System.out.println("Reservation Ajoutée");
        } 
    
    public List<evenementreservation> ShowReservationEvent(){
                List<evenementreservation> evenementreservations = new ArrayList<>();
    try {    
    String sql = "select * from  reservation";
    
    stm = cnx.prepareStatement(sql);
    ResultSet rs = stm.executeQuery();
    while (rs.next()){
        evenementreservation er = new evenementreservation();
        er.setId(rs.getInt("id"));
        er.setNom(rs.getString("nom"));
        er.setPrenom(rs.getString("prenom"));
        er.setEvent(rs.getString("event"));
        er.setNbrplace(rs.getString("nbrplace"));
        //er.setName_user(FindNameUserById(rs.getInt("id_user")));
        //er.setName_evenement(FindNameEvenemntById(rs.getInt("id_evenement")));
        evenementreservations.add(er);
    }
    } catch (SQLException ex) {
    System.out.println(ex.getMessage());
    }
        return evenementreservations;
        
    }
//               public String FindNameUserById(int id) throws SQLException{
//       String req = "SELECT  `first_Name` FROM `user` WHERE `id` = ?";
//            String u ="";
//                PreparedStatement ps = cnx.prepareStatement(req);
//                 ps.setInt(1, id);
//                ResultSet resultSet = ps.executeQuery();
//                if (resultSet.next()) {
//               
//                
//                    u = resultSet.getString(1);
//                }
//            return u;
//           
//       }
                              public String FindNameEvenemntById(int id) throws SQLException{
       String req = "SELECT  `titre` FROM `event` WHERE `id` = ?";
            String u ="";
                PreparedStatement ps = cnx.prepareStatement(req);
                 ps.setInt(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
               
                
                    u = resultSet.getString(1);
                }
            return u;
           
       }
                              
    public void deleteReservation(int id) {
 try {
            String requete7 = "DELETE FROM  reservation WHERE id=?";
            PreparedStatement pst7 = DataBase.getInstance().con.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
//           public int countEvenementReserver(int id) throws SQLException{
//         int count=0;
//       Statement stmt3 = cnx.createStatement();
//ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM  reservation WHERE `event_id` ="+id);
//    while(rs3.next()){
//    count = rs3.getInt(1);
//    }
//            return count ; 
//     }


   
}
