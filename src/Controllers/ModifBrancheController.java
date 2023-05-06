package Controllers;

import Utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifBrancheController {
    @FXML
    private Button modif;

    @FXML
    private TextField idfield;

    @FXML
    private TextField branchefield;

    @FXML
    private TextField villeField;


    @FXML
    private TextField banqueidfield;


    Connection c1;
    @FXML
    void cherher(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();
        int id=Integer.parseInt(idfield.getText());
        PreparedStatement x=(PreparedStatement) c1.prepareStatement("select * from branche where id=?");
        x.setInt(1,id);
        ResultSet rs=x.executeQuery();
        if(rs.next()==false) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"ID Introuvable", ButtonType.OK);
            alert.showAndWait();    	}
        else
        {branchefield.setText( rs.getString(2));
            villeField.setText( rs.getString(3));
            banqueidfield.setText( rs.getString(4));}
    }

    @FXML
    void modifier(ActionEvent event) throws SQLException {
        Window owner = modif.getScene().getWindow();
        c1 = DataSource.getCon();
        String codeBranche=branchefield.getText();
        String ville=villeField.getText();
        String banqueId=banqueidfield.getText();
        int id=Integer.parseInt(idfield.getText());
        PreparedStatement x=(PreparedStatement) c1.prepareStatement("update branche set codeBranche=?,ville=?,banqueId=?where id=?");
        x.setInt(4,id);
        x.setString(1, codeBranche);
        x.setString(2, ville);
        x.setString(3, banqueId);
        x.executeUpdate();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Branche.fxml"));
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