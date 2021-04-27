/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Service;

import GoCampJavaFX.com.esprit.Entite.Subject;
import GoCampJavaFX.com.esprit.Entite.User;
import GoCampJavaFX.com.esprit.IService.IServiceForum;
import GoCampJavaFX.com.esprit.Util.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceSubject implements IServiceForum<Subject>{

    private Connection con;
    private Statement ste;
            private ResultSet rs ; 

    public ServiceSubject(){
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
    public void ajouter(Subject t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `subject` (`text`, `time`, `user`) VALUES (?,?,?);");
        pre.setString(1,t.getSubject());
        pre.setTimestamp(2, Timestamp.valueOf(t.getTime()));
        pre.setInt(3,t.getUser().getIdUser());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Subject t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `subject` WHERE id = ?");
        pre.setInt(1,t.getId());
        pre.executeUpdate();
        return true;
    }


    @Override
    public boolean update(Subject t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE `subject` SET text = ?, time = ? where id = ?");
        pre.setString(1,t.getSubject());
        pre.setObject(2,Timestamp.valueOf(t.getTime()));
        pre.setInt(3, t.getId());
        pre.executeUpdate();
        return true;
    }

    @Override
    public List<Subject> readAll() throws SQLException {
        List<Subject> arr=new ArrayList<>();
        ste=con.createStatement();
        ServiceUser serviceUser = new ServiceUser();
        ServiceComments serviceComments = new ServiceComments();
        ResultSet rs = ste.executeQuery("SELECT * FROM subject");
        while(rs.next()){
            int id = rs.getInt(1);
            String text = rs.getString(2);
            Timestamp date =  (Timestamp) rs.getObject(3);
            int user = rs.getInt(4);
            
            Subject subject = new Subject(id,text,date.toLocalDateTime(),serviceUser.FindById(user));
            subject.setComments(serviceComments.FindBySubjectId(id));
            arr.add(subject);
        }
        return arr;
    }

    @Override
    public Subject search(Subject t) throws SQLException {
        ste = con.createStatement();
        ServiceUser serviceUser = new ServiceUser();
        ResultSet rs = ste.executeQuery("SELECT * from don WHERE id ="+t.getId());
        rs.last();
        int nb = rs.getRow();
        rs.beforeFirst();
        if(nb != 0){
            while(rs.next()){
                int id = rs.getInt(1);
                String text = rs.getString(2);
                Timestamp date =  (Timestamp) rs.getObject(3);
                int user = rs.getInt(4);
                Subject subject = new Subject(id,text,date.toLocalDateTime(),serviceUser.FindById(user));
                return subject;
            }
        }
        return null;
    }

    @Override
    public Subject FindById(int id) throws SQLException {
        ServiceUser serviceUser = new ServiceUser();
        String req = "select * from subject where id = ?";
        Subject subject = null;
        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Timestamp date = (Timestamp) rs.getObject(3);
            subject = new Subject(rs.getInt(1),rs.getString(2),date.toLocalDateTime(),serviceUser.FindById(rs.getInt(4)));
        }
        return subject;
    }

    @Override
    public int Count(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  @Override
    public List<Integer> get_id_user()  
     { 
          String req = "SELECT idUser from user " ;  
          List<Integer> listtd = new ArrayList<>() ; 
        
        try {
            ste=con.createStatement() ; 
            rs=ste.executeQuery(req);  
           while(rs.next())
           {
                  listtd.add(rs.getInt(1)) ; 
       
           
           }
          
        } catch (SQLException ex) {  
            Logger.getLogger(ServiceSubject.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return listtd ; 
     }         
    @Override
      public User rechercheu (int id) throws SQLException 
        {   String req = "SELECT * from user where idUser= "+id ; 
              User u = null ; 
                  ste=con.createStatement() ; 
                  rs=ste.executeQuery(req) ;  
                   while(rs.next()) 
                   { 

                    u=new User(rs.getInt(1));
                   }
            return  u ; 
        }

    @Override
    public List<Integer> get_id_sub() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Subject recherches(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public List<String> getUsersMails() 
            {    List<String> list = new ArrayList<>() ;  
          
         
        String req = "select email from user " ;  
         try {
            ste=con.createStatement() ; 
            rs=ste.executeQuery(req);  
           
           while(rs.next()) 
               list.add(rs.getString(1)) ; 
        } catch (SQLException ex) {  
            Logger.getLogger(ServiceSubject.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return list;
               
            }

    
}