package Controllers;

import Entite.Client;
import Entite.Credit;
import Utils.DataSource;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditController {

    @FXML
    private TableView<Credit> table;

    @FXML
    private TableColumn<Credit, Integer> idCol;

    @FXML
    private TableColumn<Credit, Double> montantCol;

    @FXML
    private TableColumn<Credit, Integer> idCompteCol;

    @FXML
    private TableColumn<Credit, String> isApprovedCol;

    @FXML
    private TableColumn<Credit, Double> nbrAnneeCol;

    @FXML
    private Button btnImport;

    @FXML
    private Button btnmodify;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExit;

    static Connection c1 = null;

    public CreditController() {
        c1 = DataSource.getCon();
    }

    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();

        int idc;
        idc = table.getSelectionModel().getSelectedItem().getId();
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        PreparedStatement x = (PreparedStatement) c1.prepareStatement("delete from credit where id='" + idc + "'");
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
        PreparedStatement x1 = (PreparedStatement) c1.prepareStatement("select * from credit");
        ResultSet rs = x1.executeQuery();
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

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCompteCol.setCellValueFactory(new PropertyValueFactory<>("idCompte"));
        montantCol.setCellValueFactory(new PropertyValueFactory<>("montant"));
        nbrAnneeCol.setCellValueFactory(new PropertyValueFactory<>("nbrAnnee"));
        isApprovedCol.setCellValueFactory(new PropertyValueFactory<>("isApproved"));

        table.getItems().addAll(cl);
    }

    @FXML
    void onModify(ActionEvent event) throws SQLException {
        Window owner = btnmodify.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/updateCredit.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) btnmodify.getScene().getWindow();
        stage.close();
    }

}
