package Controllers;

import Entite.Client;
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

public class ClientController {

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField NomField;

    @FXML
    private TextField PrenomField;

    @FXML
    private TextField AdresseField;

    @FXML
    private TableView<Client> table;

    @FXML
    private TableColumn<Client, String> NomCol;
    @FXML
    private TableColumn<Client, String> IdCol;


    @FXML
    private TableColumn<Client, String> PrenomCol;

    @FXML
    private TableColumn<Client, String> adresseCol;

    @FXML
    private Button btnImport;

    @FXML
    private Button btnmodify;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExit;

    static Connection c1 = null;

    public ClientController() {
        c1 = DataSource.getCon();
    }

    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();

        int idc;
        idc = table.getSelectionModel().getSelectedItem().getId();
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        PreparedStatement x = (PreparedStatement) c1.prepareStatement("delete from client where id='" + idc + "'");
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
        PreparedStatement x1 = (PreparedStatement) c1.prepareStatement("select * from client");
        ResultSet rs = x1.executeQuery();
        ArrayList<Client> cl = new ArrayList<>();

        while (rs.next()) {
            Client c = new Client();
            c.setId(rs.getInt("id"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setAdresse(rs.getString("adresse"));
            cl.add(c);
        }

        PrenomCol.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table.getItems().addAll(cl);
    }

    @FXML
    void onModify(ActionEvent event) throws SQLException {
        Window owner = btnmodify.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/updatClient.fxml"));
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

    @FXML
    void onSubmit(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();

        String nom = NomField.getText();
        String prenom = PrenomField.getText();
        String adresse = AdresseField.getText();
        PreparedStatement x = (PreparedStatement) c1.prepareStatement("insert into client(nom,prenom,adresse) values(?,?,?)");

        x.setString(1, nom);
        x.setString(2, prenom);
        x.setString(3, adresse);
        x.execute();

        Client c = new Client(this.NomField.getText(), this.PrenomField.getText(), this.AdresseField.getText());
        table.getItems().add(c);


    }

}
