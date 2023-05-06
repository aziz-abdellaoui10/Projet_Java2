package Controllers;

import Entite.Compte;
import Entite.Credit;
import Utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DemandeCreditController {

    @FXML
    private Button envoieBtn;

    @FXML
    private TextField idexptf;

    @FXML
    private TextField remboursementtf;

    @FXML
    private TextField montanttf;

    static Connection c1 = null;

    public DemandeCreditController() {
        c1 = DataSource.getCon();
    }

    @FXML
    void onSubmit(ActionEvent event) throws SQLException {
        c1 = DataSource.getCon();

        Integer idc = Integer.parseInt(idexptf.getText());
        Double montant = Double.parseDouble(montanttf.getText());
        Double nbrAnnee = Double.parseDouble(remboursementtf.getText());

        PreparedStatement x = (PreparedStatement) c1.prepareStatement("insert into credit(montant,nbrAnnee,idCompte) values(?,?,?)");

        x.setDouble(1, montant);
        x.setDouble(2, nbrAnnee);
        x.setInt(3, idc);
        x.execute();

        Stage stage = (Stage) envoieBtn.getScene().getWindow();
        stage.close();
    }

}
