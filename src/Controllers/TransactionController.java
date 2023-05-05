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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TransactionController {

    @FXML
    private TextField idComptetf;

    @FXML
    private TextField montanttf;

    @FXML
    private Button retraitBtn;

    @FXML
    private Button versementBtn;

    static Connection c1=null;

    @FXML
    void onRetrait(ActionEvent event) throws SQLException {
        Window owner = retraitBtn.getScene().getWindow();
        c1 = DataSource.getCon();
        double sd = 0;
        double dc = 0;
        double m=0;
        double decn=1;
        double decp=0;
        int id=Integer.parseInt(idComptetf.getText());
        PreparedStatement x1=(PreparedStatement) c1.prepareStatement("select solde,maxdecouvert,maxretrait from compte where id=?");
        x1.setInt(1, id);
        ResultSet rs=x1.executeQuery();
        while(rs.next())
        {
            Compte c=new Compte();
            c.setSolde(rs.getDouble("solde"));
            c.setMaxDecouvert(rs.getDouble("maxdecouvert"));
            c.setMaxRetrait(rs.getDouble("maxretrait"));
            sd=c.getSolde();
            dc=c.getMaxDecouvert();
            m=c.getMaxRetrait();
        }
        double nvsolde=sd;
        Double montant=Double.parseDouble(montanttf.getText());
        if(montant>m) {
            Alert alert = new Alert(AlertType.ERROR,"Retrait �chou�: montant superieur au max retrait",ButtonType.OK);
            alert.showAndWait();
        }
        if(sd-montant<(-dc)) {
            Alert alert = new Alert(AlertType.ERROR,"Retrait �chou�: solde du compte sera inferieur au max decouvert",ButtonType.OK);
            alert.showAndWait();
        }
        if((montant<=m)&&(sd-montant<(-dc)))
        {
            System.out.println("je suis la");

            nvsolde=sd-montant;
            if(nvsolde<0) {
                PreparedStatement x=(PreparedStatement) c1.prepareStatement("update compte set solde=?,decouvert='"+decn+"' where id=?");
                x.setDouble(1, nvsolde);
                x.setInt(2, id);
                x.execute();
            }
            else {
                PreparedStatement x=(PreparedStatement) c1.prepareStatement("update compte set solde=?,decouvert='"+decp+"' where id=?");
                x.setDouble(1, nvsolde);
                x.setInt(2, id);
                x.execute();
            }
        }
        Stage stage = (Stage) retraitBtn.getScene().getWindow();
        stage.close();

    }


    @FXML
    void onVerser(ActionEvent event) throws SQLException {
        Window owner = versementBtn.getScene().getWindow();
        c1 = DataSource.getCon();
        double sd = 0;
        double decn=1;
        double decp=0;
        int id=Integer.parseInt(idComptetf.getText());
        PreparedStatement x1=(PreparedStatement) c1.prepareStatement("select solde from compte where id='"+id+"'");
        ResultSet rs=x1.executeQuery();
        while(rs.next())
        {
            Compte c=new Compte();
            c.setSolde(rs.getDouble("solde"));
            sd=c.getSolde();
        }
        Double montant=Double.parseDouble(montanttf.getText());
        Double nvsolde=sd+montant;
        if(nvsolde<0) {
            PreparedStatement x=(PreparedStatement) c1.prepareStatement("update compte set solde='"+nvsolde+"',decouvert='"+(-nvsolde)+"' where id=?");
            x.execute();
        }
        else{
            PreparedStatement x=(PreparedStatement) c1.prepareStatement("update compte set solde='"+nvsolde+"',decouvert=0 where id='"+id+"'");
            x.execute();
        }

        Stage stage = (Stage) versementBtn.getScene().getWindow();
        stage.close();

    }

}
