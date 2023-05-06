package Controllers;

import Entite.Branche;

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
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrancheController {

    @FXML
    private TableColumn<Branche, Integer> banqueIdCol;

    @FXML
    private TextField banqueid;

    @FXML
    private TableColumn<Branche, String> brancheCol;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnImport;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnmodify;

    @FXML
    private TextField codebranche;

    @FXML
    private TableColumn<Branche, Integer> idCol;

    @FXML
    private TableView<Branche> table;

    @FXML
    private TextField ville;

    @FXML
    private TableColumn<Branche, String> villeCol;

    static Connection c1 = null;

    public BrancheController() {
        c1 = DataSource.getCon();
    }

    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();

        int idc;
        idc = table.getSelectionModel().getSelectedItem().getId();
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        PreparedStatement x = (PreparedStatement) c1.prepareStatement("delete from branche where id='" + idc + "'");
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
        PreparedStatement x1 = (PreparedStatement) c1.prepareStatement("select * from branche");
        ResultSet rs = x1.executeQuery();
        ArrayList<Branche> cl = new ArrayList<>();

        while (rs.next()) {
            Branche b = new Branche();
            b.setId(rs.getInt("id"));
            b.setCodeBranche(rs.getString("codeBranche"));
            b.setVille(rs.getString("ville"));
            b.setBanqueId(rs.getInt("banqueId"));
            cl.add(b);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        brancheCol.setCellValueFactory(new PropertyValueFactory<>("codeBranche"));
        villeCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        banqueIdCol.setCellValueFactory(new PropertyValueFactory<>("banqueId"));
        table.getItems().addAll(cl);
    }

    @FXML
    void onModify(ActionEvent event) throws SQLException {
        Window owner = btnmodify.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/updateBranche.fxml"));
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

        String code= codebranche.getText();
        String nomVille = ville.getText();
        String banqueId = banqueid.getText();
        PreparedStatement x = (PreparedStatement) c1.prepareStatement("insert into branche (codeBranche,ville,banqueId) values(?,?,?)");

        x.setString(1, code);
        x.setString(2, nomVille);
        x.setString(3, banqueId);
        x.execute();

        Branche c = new Branche(this.codebranche.getText(), this.ville.getText(), Integer.parseInt(this.banqueid.getText()));
        table.getItems().add(c);


    }

}