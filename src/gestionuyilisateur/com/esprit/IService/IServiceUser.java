/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuyilisateur.com.esprit.IService;

import gestionuyilisateur.com.esprit.Entite.User;
import java.sql.SQLException;

/**
 *
 * @author Hama Hagui
 */
public interface IServiceUser<T> {
    
    void register(T t)throws SQLException;
    boolean update(T t, int id)throws SQLException;
    boolean ResetPassword(String pass, int id)throws SQLException;
    User login(String email, String password) throws SQLException;
    boolean delete(T t) throws SQLException;
    User FindById(int id) throws SQLException;
    
    
    
}
