package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.Window;

public class ModifClientController {

    @FXML
    private TextField idfield;

    @FXML
    private TextField nomfield;

    @FXML
    private Button modif;

    @FXML
    private TextField prenomfield;

    @FXML
    private TextField adressefield;
    Connection c1;
    @FXML
    void cherher(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();
        int id=Integer.parseInt(idfield.getText());
        PreparedStatement x=(PreparedStatement) c1.prepareStatement("select * from client where id=?");
        x.setInt(1,id);
        ResultSet rs=x.executeQuery();
        if(rs.next()==false) {
            Alert alert = new Alert(AlertType.ERROR,"ID Introuvable",ButtonType.OK);
            alert.showAndWait();    	}
        else
        {nomfield.setText( rs.getString(2));
            prenomfield.setText( rs.getString(3));
            adressefield.setText( rs.getString(4));}
    }

    @FXML
    void modifier(ActionEvent event) throws SQLException {
        Window owner = modif.getScene().getWindow();
        c1 = DataSource.getCon();
        String nom=nomfield.getText();
        String prenom=prenomfield.getText();
        String adresse=adressefield.getText();
        int id=Integer.parseInt(idfield.getText());
        PreparedStatement x=(PreparedStatement) c1.prepareStatement("update client set nom=?,prenom=?,adresse=?where id=?");
        x.setInt(4,id);
        x.setString(1, nom);
        x.setString(2, prenom);
        x.setString(3, adresse);
        x.executeUpdate();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Client.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) modif.getScene().getWindow();
        stage.close();
    }

}


