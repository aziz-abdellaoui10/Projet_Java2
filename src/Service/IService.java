/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sanabenfadhel
 */
public interface IService<T> {
    
    
    void ajouter(T t) throws SQLException;
    boolean supprimer(T t) throws SQLException;
    boolean modifier (T t) throws SQLException;
    List<T> readAll() throws SQLException;
    T findbyId(int id) throws SQLException;
    
}
