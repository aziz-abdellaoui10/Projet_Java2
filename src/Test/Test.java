/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entite.Client;
import Entite.Compte;
import Service.ServiceClient;
import Utils.DataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author sanabenfadhel
 */
public class Test extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = DataSource.getCon();
        Application.launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        BorderPane root = FXMLLoader.load(getClass().getResource("Banque.fxml"));
//        primaryStage.setTitle("Acceuil Banque");
//        primaryStage.setScene(new Scene(root, 900, 506));
//        primaryStage.show();
    }

}