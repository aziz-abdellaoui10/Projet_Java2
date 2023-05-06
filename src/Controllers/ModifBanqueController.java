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

public class ModifBanqueController {

    @FXML
    private TextField Idfield;

    @FXML
    private TextField Nomfield;

    @FXML
    private Button modif;

    @FXML
    private TextField Codefield;

    Connection c1;
    @FXML
    void cherher(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();
        int id=Integer.parseInt(Idfield.getText());
        PreparedStatement x=(PreparedStatement) c1.prepareStatement("select * from banque where id=?");
        x.setInt(1,id);
        ResultSet rs=x.executeQuery();
        if(rs.next()==false) {
            Alert alert = new Alert(AlertType.ERROR,"ID Introuvable",ButtonType.OK);
            alert.showAndWait();    	}
        else
        {
            Nomfield.setText( rs.getString(2));
            Codefield.setText( rs.getString(3));
        }
    }

    @FXML
    void modifier(ActionEvent event) throws SQLException {
        Window owner = modif.getScene().getWindow();
        c1 = DataSource.getCon();
        String nom=Nomfield.getText();
        String code=Codefield.getText();
        int id=Integer.parseInt(Idfield.getText());
        PreparedStatement x=(PreparedStatement) c1.prepareStatement("update banque set nom=?,code=? where id=?");
        x.setInt(3,id);
        x.setString(1, nom);
        x.setString(2, code);
        x.executeUpdate();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Banque.fxml"));
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