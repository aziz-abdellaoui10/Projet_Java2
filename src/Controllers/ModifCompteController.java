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
import javafx.stage.Window;
import javafx.scene.control.Button;

public class ModifCompteController {

    @FXML
    private TextField MaxRetraitField;

    @FXML
    private TextField SoldeField;

    @FXML
    private TextField MaxDecouvertField;

    @FXML
    private Button modif;

    @FXML
    private TextField idfield;

    Connection c1=null;

    @FXML
    void onModifier(ActionEvent event) throws SQLException {
        Window owner = modif.getScene().getWindow();
        c1 = DataSource.getCon();
        Double solde=Double.parseDouble(SoldeField.getText());
        Double maxDecouvert=Double.parseDouble(MaxDecouvertField.getText());
        Double maxRetrait=Double.parseDouble(MaxRetraitField.getText());
        int id=Integer.parseInt(idfield.getText());
        if(solde<0) {
            PreparedStatement x=(PreparedStatement) c1.prepareStatement("update compte set solde=?,maxdecouvert=?,maxretrait=?, decouvert='"+(-solde)+"'where id=?");
            x.setInt(4,id);
            x.setDouble(1, solde);
            x.setDouble(2, maxDecouvert);
            x.setDouble(3, maxRetrait);
            x.executeUpdate();
        }
        else {
            PreparedStatement x=(PreparedStatement) c1.prepareStatement("update compte set solde=?,maxdecouvert=?,maxretrait=?, decouvert=0 where id=?");
            x.setInt(4,id);
            x.setDouble(1, solde);
            x.setDouble(2, maxDecouvert);
            x.setDouble(3, maxRetrait);
            x.executeUpdate();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Compte.fxml"));
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

    @FXML
    void chercher(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();
        int id=Integer.parseInt(idfield.getText());
        PreparedStatement x=(PreparedStatement) c1.prepareStatement("select solde,MaxDecouvert,MaxRetrait from compte where id=?");
        x.setInt(1,id);
        ResultSet rs=x.executeQuery();
        if(rs.next()==false) {
            Alert alert = new Alert(AlertType.ERROR,"ID Introuvable",ButtonType.OK);
            alert.showAndWait();    	}
        else
        {
            String s=String.valueOf(rs.getDouble(1));
            String d=String.valueOf(rs.getDouble(2));
            String r=String.valueOf(rs.getDouble(3));
            SoldeField.setText(s);
            MaxDecouvertField.setText(d);
            MaxRetraitField.setText(r);
        }
    }

}

