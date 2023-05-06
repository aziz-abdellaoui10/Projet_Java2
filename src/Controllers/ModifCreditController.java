package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entite.Compte;
import Entite.Credit;
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

public class ModifCreditController {

    @FXML
    private TextField idfield;

    @FXML
    private TextField montantField;

    @FXML
    private Button modif;

    @FXML
    private TextField nbrAnneeField;

    @FXML
    private TextField approveField;

    Connection c1;

    @FXML
    void cherher(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();
        int id = Integer.parseInt(idfield.getText());
        PreparedStatement x = (PreparedStatement) c1.prepareStatement("select * from credit where id=?");
        x.setInt(1, id);
        ResultSet rs = x.executeQuery();
        if (rs.next() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID Introuvable", ButtonType.OK);
            alert.showAndWait();
        } else {
            montantField.setText(rs.getString(2));
            nbrAnneeField.setText(rs.getString(3));
        }
    }

    @FXML
    void modifier(ActionEvent event) throws SQLException {
        Window owner = modif.getScene().getWindow();
        c1 = DataSource.getCon();
        Double montant = Double.parseDouble(montantField.getText());
        Double nbrAnnee = Double.parseDouble(nbrAnneeField.getText());
        String approve = approveField.getText();
        int id = Integer.parseInt(idfield.getText());

        if ((approve.toUpperCase().equals("YES"))) {
            PreparedStatement x = (PreparedStatement) c1.prepareStatement("update credit set montant=?,nbrAnnee=?, isApproved=? where id=?");
            x.setInt(4, id);
            x.setDouble(1, montant);
            x.setDouble(2, nbrAnnee);
            x.setString(3, approve);

            PreparedStatement x2 = (PreparedStatement) c1.prepareStatement("select * from credit where id="+id);
            ResultSet rs = x2.executeQuery();
            ArrayList<Credit> cl = new ArrayList<>();

            while (rs.next()) {
                Credit c = new Credit();
                c.setId(rs.getInt("id"));
                c.setIdCompte(rs.getInt("idCompte"));
                c.setMontant(rs.getDouble("montant"));
                c.setNbrAnnee(rs.getDouble("nbrAnnee"));
                c.setIsApproved(rs.getString("isApproved"));
                cl.add(c);

            }
            PreparedStatement x3 = (PreparedStatement) c1.prepareStatement("select * from compte where id="+cl.get(0).getIdCompte());
            ResultSet rs1 = x3.executeQuery();
            ArrayList<Compte> cl1 = new ArrayList<>();

            while (rs1.next()) {
                Compte c = new Compte();
                c.setId(rs1.getInt("id"));
                c.setSolde(rs1.getDouble("solde"));
                c.setMaxDecouvert(rs1.getDouble("MaxDecouvert"));
                c.setMaxRetrait(rs1.getDouble("MaxRetrait"));
                c.setClientId(rs1.getInt("clientId"));
                c.setDecouvert(rs1.getDouble("decouvert"));
                cl1.add(c);

            }
            PreparedStatement x1 = (PreparedStatement) c1.prepareStatement("update compte set solde=?, decouvert=? where id=?");
            System.out.println(cl1.get(0).getSolde());
            double solde = 0.0;
            double decouvert = 0.0;
            if(cl1.get(0).getDecouvert() == 0.0 ) {
                solde = (montant + (cl1.get(0).getSolde()));
                decouvert = 0.0;
            }else if(cl1.get(0).getDecouvert() > 0.0){
                if(montant == -(cl1.get(0).getSolde())) {
                    System.out.println("case 1");
                    solde = 0.0;
                    decouvert = 0.0;
                } else if (cl1.get(0).getSolde() + montant > 0.0) {
                    System.out.println("case 2");
                    System.out.println(cl1.get(0).getSolde() + montant);
                    solde = cl1.get(0).getSolde() + montant;
                    decouvert = 1.0;
                }else{
                    System.out.println("case 3");
                    solde = (cl1.get(0).getSolde() + montant);
                    decouvert = 1.0;
                }
            }
            x1.setDouble(1, solde);
            x1.setDouble(2, decouvert);
            x1.setDouble(3, cl1.get(0).getId());

            x.executeUpdate();
            x1.executeUpdate();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Credit.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) modif.getScene().getWindow();
            stage.close();
        }else if (approve.toUpperCase().equals("NO")){
            int idc;
            PreparedStatement x = (PreparedStatement) c1.prepareStatement("delete from credit where id='" + id + "'");
            x.execute();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Credit.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) modif.getScene().getWindow();
            stage.close();
        }
    }

}


