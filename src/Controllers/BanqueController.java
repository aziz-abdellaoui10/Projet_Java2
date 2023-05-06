package Controllers;

import Entite.Banque;
import Entite.Branche;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class BanqueController {
    @FXML
    private Button btnSubmit;
    @FXML
    private TextField NomField;
    @FXML
    private TextField CodeField;
    @FXML
    private TableView<Banque> table;
    @FXML
    private TableColumn<Banque, String> NomCol;
    @FXML
    private TableColumn<Banque, String> IdCol;
    @FXML
    private TableColumn<Banque, String> CodeCol;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnmodify;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExit;
    static Connection c1 = null;
    public BanqueController() {
        c1 = DataSource.getCon();
    }

    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();
        int idc = ((Banque)this.table.getSelectionModel().getSelectedItem()).getId();
        this.table.getItems().remove(this.table.getSelectionModel().getSelectedItem());
        PreparedStatement x = c1.prepareStatement("delete from banque where id='" + idc + "'");
        x.execute();
    }
    @FXML
    void onExit(ActionEvent event) {
        Window owner = this.btnExit.getScene().getWindow();
        Stage stage = (Stage)this.btnExit.getScene().getWindow();
        stage.close();
    }
    @FXML
    void onImport(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();
        this.table.getItems().clear();
        PreparedStatement x1 = c1.prepareStatement("select * from banque");
        ResultSet rs = x1.executeQuery();
        ArrayList<Banque> cl = new ArrayList();
        while(rs.next()) {
            Banque c = new Banque();
            c.setId(rs.getInt("id"));
            c.setNom(rs.getString("nom"));
            c.setCode(rs.getString("code"));
            cl.add(c);
        }
        this.CodeCol.setCellValueFactory(new PropertyValueFactory("Code"));
        this.NomCol.setCellValueFactory(new PropertyValueFactory("Nom"));
        this.IdCol.setCellValueFactory(new PropertyValueFactory("Id"));
        this.table.getItems().addAll(cl);
    }
    @FXML
    void onModify(ActionEvent event) throws SQLException {
        Window owner = this.btnmodify.getScene().getWindow();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../Views/updateBanque.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        Stage stage = (Stage)this.btnmodify.getScene().getWindow();
        stage.close();
    }
    @FXML
    void onSubmit(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();
        String nom = this.NomField.getText();
        String code = this.CodeField.getText();
        PreparedStatement x = c1.prepareStatement("insert into Banque(nom,code) values(?,?)");
        x.setString(1, nom);
        x.setString(2, code);
        x.execute();
        Banque c = new Banque(this.NomField.getText(), this.CodeField.getText());
        this.table.getItems().add(c);
    }

}
