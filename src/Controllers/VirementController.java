package Controllers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entite.Compte;
import Utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class VirementController {

    @FXML
    private TextField iddesttf;

    @FXML
    private TextField montanttf;

    @FXML
    private Button envoieBtn;

    @FXML
    private TextField idexptf;

    static Connection c1=null;

    @FXML
    void onSubmit(ActionEvent event) throws SQLException {
        Window owner = envoieBtn.getScene().getWindow();
        c1 = DataSource.getCon();
        double sde = 0;
        double sdd =0;
        double dc = 0;
        double nvsolder = 0;
        double nvsoldev =0;
        int idd=Integer.parseInt(iddesttf.getText());
        int ide=Integer.parseInt(idexptf.getText());
        PreparedStatement x1=(PreparedStatement) c1.prepareStatement("select solde,maxdecouvert from compte where id='"+ide+"'");
        PreparedStatement x3=(PreparedStatement) c1.prepareStatement("select solde from compte where id='"+idd+"'");
        ResultSet rs=x1.executeQuery();
        while(rs.next())
        {
            Compte c=new Compte();
            c.setSolde(rs.getDouble("solde"));
            c.setMaxDecouvert(rs.getDouble("maxdecouvert"));
            sde=c.getSolde();
            dc=c.getMaxDecouvert();
        }
        ResultSet rs1=x3.executeQuery();
        while(rs1.next())
        {
            Compte c = new Compte();
            c.setSolde(rs1.getDouble("solde"));
            sdd=c.getSolde();
        }
        nvsolder=sde;
        Double montant=Double.parseDouble(montanttf.getText());
        if(sde-montant>=(-dc)) {
            nvsolder=sde-montant;
            nvsoldev=sdd+montant;
        }
        else
        {

            Alert alert = new Alert(AlertType.ERROR,"Virement �chou�: solde du compte expediteur sera inferieur au max decouvert",ButtonType.OK);
            alert.showAndWait();
        }
        if(nvsolder<0) {
            PreparedStatement x=(PreparedStatement) c1.prepareStatement("update compte set solde='"+nvsolder+"',decouvert=1 where id='"+ide+"'");
            x.execute();
        }
        else {
            PreparedStatement x=(PreparedStatement) c1.prepareStatement("update compte set solde='"+nvsolder+"',decouvert=0 where id='"+ide+"'");
            x.execute();
        }
        if(nvsoldev>=0) {
            PreparedStatement x2=(PreparedStatement) c1.prepareStatement("update compte set solde='"+nvsoldev+"',decouvert=0 where id='"+idd+"'");
            x2.execute();

        }
        else {
            PreparedStatement x2=(PreparedStatement) c1.prepareStatement("update compte set solde='"+nvsoldev+"',decouvert=1 where id='"+idd+"'");
            x2.execute();
        }
        Stage stage = (Stage) envoieBtn.getScene().getWindow();
        stage.close();
    }

}

