/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Service;

import GoCampJavaFX.com.esprit.Entite.Comment;
import GoCampJavaFX.com.esprit.Entite.Subject;
import GoCampJavaFX.com.esprit.Entite.User;
import GoCampJavaFX.com.esprit.IService.IService;
import GoCampJavaFX.com.esprit.IService.IServiceForum;
import GoCampJavaFX.com.esprit.Util.DataBase;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceComments implements IServiceForum<Comment>{

    private Connection con;
    private Statement ste;
            private ResultSet rs ; 

    
    public ServiceComments(){
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
    public void ajouter(Comment t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `comment` (`text`, `time`, `user`, `subject`) VALUES (?,?,?,?);");
        pre.setString(1,t.getText());
        pre.setObject(2,Timestamp.valueOf(t.getTime()));
        pre.setInt(3, t.getUser().getIdUser());
        pre.setInt(4, t.getSubject().getId());
        pre.executeUpdate();    
    }

    @Override
    public boolean delete(Comment t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `comment` WHERE id = ?");
        pre.setInt(1,t.getId());
        pre.executeUpdate();
        return true;
    }

    @Override
    public boolean update(Comment t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE `comment` SET text = ?, time = ? where id = ?");
        pre.setString(1,t.getText());
        pre.setObject(2,Timestamp.valueOf(t.getTime()));
        pre.setInt(3,t.getId());
        pre.executeUpdate();
        return true;
    }

    @Override
    public List<Comment> readAll() throws SQLException {
        List<Comment> arr=new ArrayList<>();
        ServiceUser serviceUser = new ServiceUser();
        ServiceSubject serviceSubject = new ServiceSubject();
        ste=con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM comment");
        while(rs.next()){
            int id = rs.getInt(1);
            String text = rs.getString(2);
            Timestamp date =  (Timestamp) rs.getObject(3);
            int user = rs.getInt(4);
            int subject = rs.getInt(5);
            Comment comment = new Comment(id,text,date.toLocalDateTime(), serviceUser.FindById(user), serviceSubject.FindById(subject));
            arr.add(comment);
        }
        return arr;
    }
    
    public List<Comment> FindBySubjectId(int id) throws SQLException {
        String req = "select * from comment where subject = ?";
        List<Comment> arr= new ArrayList<>();
        ServiceUser serviceUser = new ServiceUser();
        ServiceSubject serviceSubject = new ServiceSubject();
        Comment comment = null;
        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Timestamp date = (Timestamp) rs.getObject(3);
            comment = new Comment(rs.getInt(1),rs.getString(2),date.toLocalDateTime(),serviceUser.FindById(rs.getInt(4)),serviceSubject.FindById(rs.getInt(5)));
            arr.add(comment);
        }
        return arr;
    }

    @Override
    public Comment search(Comment t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comment FindById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Count(int id) throws SQLException {
        String req = "select COUNT(*) from comment where subject = ?";
        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
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
            Logger.getLogger(ServiceComments.class.getName()).log(Level.SEVERE, null, ex);
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
          String req = "SELECT id from subject " ;  
          List<Integer> listtd = new ArrayList<>() ; 
        
        try {
            ste=con.createStatement() ; 
            rs=ste.executeQuery(req);  
           while(rs.next())
           {
                  listtd.add(rs.getInt(1)) ; 
       
           
           }
          
        } catch (SQLException ex) {  
            Logger.getLogger(ServiceComments.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return listtd ; 
    }

    @Override
    public Subject recherches(int id) throws SQLException {
        {   String req = "SELECT * from subject where id= "+id ; 
              Subject u = null ; 
                  ste=con.createStatement() ; 
                  rs=ste.executeQuery(req) ;  
                   while(rs.next()) 
                   { 

                    u=new Subject(rs.getInt(1));
                   }
            return  u ; 
        }
    } 
     
    
}
