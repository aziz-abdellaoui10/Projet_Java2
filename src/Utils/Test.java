/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 * @author sanabenfadhel
 */

import java.sql.*;

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        
//        DataSource da1=DataSource.getInstance();
//        
//         DataSource da2=DataSource.getInstance();
//         
//         System.out.println(da1);
//         System.out.println(da2);

        Connection con = DataSource.getInstance().getCon();

        Connection con1 = DataSource.getInstance().getCon();

        System.out.println(con);
        System.out.println(con1);
        Connection conn = DataSource.getCon();
        System.out.println(conn);

    }

}
