package Controllers;

import Entite.Branche;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;


public class BrancheController {
    @FXML
    private Button btnSubmit;

    @FXML
    private TextField idField;

    @FXML
    private TextField labelField;

    @FXML
    private TableView<Branche> table;

    @FXML
    private TableColumn<Branche, Integer> idCol;

    @FXML
    private TableColumn<Branche, String> labelCol;

    @FXML
    private Button btnImport;

    @FXML
    private Button btnModify;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExit;

    static Connection c1 = null;

    public BrancheController() {
        c1 = DataSource.getCon();
    }

    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();

        int id = table.getSelectionModel().getSelectedItem().getId();
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        PreparedStatement ps = c1.prepareStatement("delete from branche where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
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
        PreparedStatement ps = c1.prepareStatement("select * from branche");
        ResultSet rs = ps.executeQuery();
        ArrayList<Branche> branches = new ArrayList<>();

        while (rs.next()) {
            Branche b = new Branche();
            b.setId(rs.getInt("id"));
            b.setVille(rs.getString("ville"));
            branches.add(b);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        labelCol.setCellValueFactory(new PropertyValueFactory<>("label"));
        table.getItems().addAll(branches);
    }

    @FXML
    void onModify(ActionEvent event) throws SQLException {
        Window owner = btnModify.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/updateBranche.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) btnModify.getScene().getWindow();
        stage.close();
    }

//    @FXML
//    void onSubmit(ActionEvent event) throws SQLException {
//        c1 = DataSource.getCon();
//
//        int id = Integer.parseInt(idField.getText());
//        String label = labelField.getText();
//        PreparedStatement ps = c1.prepareStatement("insert into branche(id, label) values (?, ?)");
//        ps.setInt(1, id);
//        ps.setString(2, label);
//        ps.executeUpdate();
//
//        Branche b = new Branche(id, label);
//        table.getItems().add(b);
//        idField.clear();
//        labelField.clear();
//    }
}