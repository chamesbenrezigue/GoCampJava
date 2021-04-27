/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.IService;

import GoCampJavaFX.com.esprit.Entite.Subject;
import GoCampJavaFX.com.esprit.Entite.User;
import java.sql.SQLException;
import java.util.List;

public interface IServiceForum<T> {
    void ajouter(T t) throws SQLException;
    boolean delete(T t ) throws SQLException;
    boolean update(T t) throws SQLException;
    List<T> readAll() throws SQLException;
    T search(T t) throws SQLException;
    T FindById(int id) throws SQLException;
    int Count(int id) throws SQLException; 
    public List<Integer> get_id_user() throws SQLException;    
    public User rechercheu (int id) throws SQLException ; 
     public List<Integer> get_id_sub() throws SQLException;    
    public Subject recherches (int id) throws SQLException ;
}
