package Controllers;

import Entite.Compte;
import Utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CompteController {

    static Connection c1;

    public CompteController() {
        c1 = DataSource.getCon();
    }

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField SoldeField;

    @FXML
    private TextField TypeField;

    @FXML
    private TextField maxDecouvertField;

    @FXML
    private TextField maxRetraitField;

    @FXML
    private TextField idClientField;

    @FXML
    private TableView<Compte> table;

    @FXML
    private TableColumn<Compte, Integer> ClientCol;

    @FXML
    private TableColumn<Compte, Integer> CompteCol;

    @FXML
    private TableColumn<Compte, Double> SoldeCol;

    @FXML
    private TableColumn<Compte, Double> decouvertCol;

    @FXML
    private TableColumn<Compte, Double> maxDecouvertCol;

    @FXML
    private TableColumn<Compte, Double> maxRetraitCol;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLister;

    @FXML
    private Button btnModify;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnTransaction;

    @FXML
    private Button btnVirement;

    @FXML
    private ComboBox<Integer> combo;

    @FXML
    private Button btnCredit;

    @FXML
    void modifier(ActionEvent event) throws SQLException {
        Window owner = btnModify.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/updateCompte.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) btnModify.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();

        int idc;
        idc = table.getSelectionModel().getSelectedItem().getId();
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        PreparedStatement x = (PreparedStatement) c1.prepareStatement("delete from compte where id='" + idc + "'");
        x.execute();
    }

    @FXML
    void onExit(ActionEvent event) {
        Window owner = btnExit.getScene().getWindow();
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onImport(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();
        table.getItems().clear();

        PreparedStatement x1 = (PreparedStatement) c1.prepareStatement("select * from compte");
        ResultSet rs = x1.executeQuery();
        ArrayList<Compte> cl = new ArrayList<>();

        while (rs.next()) {
            Compte c = new Compte();
            c.setId(rs.getInt("id"));
            c.setSolde(rs.getDouble("solde"));
            c.setMaxDecouvert(rs.getDouble("MaxDecouvert"));
            c.setMaxRetrait(rs.getDouble("MaxRetrait"));
            c.setClientId(rs.getInt("clientId"));
            c.setDecouvert(rs.getDouble("decouvert"));
            cl.add(c);
        }

        ClientCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        CompteCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        SoldeCol.setCellValueFactory(new PropertyValueFactory<>("solde"));
        decouvertCol.setCellValueFactory(new PropertyValueFactory<>("decouvert"));
        maxDecouvertCol.setCellValueFactory(new PropertyValueFactory<>("MaxDecouvert"));
        maxRetraitCol.setCellValueFactory(new PropertyValueFactory<>("MaxRetrait"));
        table.getItems().addAll(cl);
    }

    @FXML
    void onMax(ActionEvent event) throws SQLException {
        c1 = DataSource.getInstance().getCon();
        table.getItems().clear();

        PreparedStatement x1 = (PreparedStatement) c1.prepareStatement("select * from compte where solde=(select max(solde) from compte)");
        ResultSet rs = x1.executeQuery();
        ArrayList<Compte> cl = new ArrayList<>();

        while (rs.next()) {
            Compte c = new Compte();
            c.setId(rs.getInt("id"));
            c.setSolde(rs.getDouble("solde"));
            c.setMaxDecouvert(rs.getDouble("MaxDecouvert"));
            c.setMaxRetrait(rs.getDouble("MaxRetrait"));
            c.setClientId(rs.getInt("clientId"));
            c.setDecouvert(rs.getDouble("decouvert"));
            cl.add(c);
        }

        ClientCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        CompteCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        SoldeCol.setCellValueFactory(new PropertyValueFactory<>("solde"));
        decouvertCol.setCellValueFactory(new PropertyValueFactory<>("decouvert"));
        maxDecouvertCol.setCellValueFactory(new PropertyValueFactory<>("MaxDecouvert"));
        maxRetraitCol.setCellValueFactory(new PropertyValueFactory<>("MaxRetrait"));
        table.getItems().addAll(cl);
    }


    @FXML
    void onSubmit(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();

        Double solde = Double.parseDouble(SoldeField.getText());
        Double maxDecouvert = Double.parseDouble(maxDecouvertField.getText());
        Double maxRetrait = Double.parseDouble(maxRetraitField.getText());
        Integer clientId = Integer.parseInt(idClientField.getText());

        PreparedStatement x = (PreparedStatement) c1.prepareStatement("insert into compte(solde,maxdecouvert,maxretrait,clientid) values(?,?,?,?)");

        x.setDouble(1, solde);
        x.setDouble(2, maxDecouvert);
        x.setDouble(3, maxRetrait);
        x.setDouble(4, clientId);
        x.execute();

        Compte c = new Compte(Double.parseDouble(this.SoldeField.getText()), Double.parseDouble(this.maxDecouvertField.getText()), Double.parseDouble(this.maxRetraitField.getText()), Integer.parseInt(this.idClientField.getText()));
        table.getItems().add(c);
    }

    @FXML
    void onTransaction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Transaction.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onVirement(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Virement.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCredit(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/DemandeCredit.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
