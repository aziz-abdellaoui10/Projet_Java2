/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanabenfadhel
 */
public class DataSource {

    private static DataSource data;
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/banque";
    private String login = "root";
    private String pwd = "";

    private DataSource() {
        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static  Connection getCon() {
        Connection conx;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/banque";
            String username = "root";
            String password = "";

            conx = (Connection) DriverManager.getConnection(url, username, password);
            return conx;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getInstance() {
        if (data == null) {
            data = new DataSource();
        }
        return data;
    }
}
