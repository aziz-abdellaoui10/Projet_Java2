package Service;

import Entite.Compte;
import Utils.DataSource;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCompte implements IService<Compte> {
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;
    public ServiceCompte() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(Compte t) throws SQLException
    {
        String req = "INSERT INTO comptes ('id', 'solde','decouvert',  'MaxDecouvert' ,'MaxRetrait' , 'resteCredit','paiementCreditParMois' , 'type','clientId') "
                + "VALUES ('" + t.getId() + "', '" + t.getSolde() + "', '" + t.getDecouvert() + "', '" + t.getMaxDecouvert() + "'," +
                " '" + t.getMaxRetrait() + "', '" + t.getResteCredit() + "', '" + t.getPaiementCreditParMois() + "', '" + t.getType() + "', '" + t.getClientId() + "');";
        ste.executeUpdate(req);
    }


    @Override
    public boolean supprimer(Compte t) throws SQLException {
        String req = "DELETE FROM 'comptes' WHERE id = " + t.getId();
        int result = ste.executeUpdate(req);
        return result > 0;
    }

    @Override
    public boolean modifier(Compte t) throws SQLException {

        String req = "UPDATE comptes SET solde ='" + t.getSolde() + "', decouvert = '" + t.getDecouvert() + "', max_decouvert = '" + t.getMaxDecouvert() + "'," +
                " max_retrait = '" + t.getMaxRetrait() + "', reste_credit ='" + t.getResteCredit() + "', paiement_credit_par_mois = '" + t.getPaiementCreditParMois() + "', type = '" + t.getType() + "'," +
                " ouverture = '" + t.getOuverture() + "' WHERE id =" + t.getId();
        int result = ste.executeUpdate(req);
        return result > 0;
    }
    @Override
    public List<Compte> readAll() throws SQLException {
        List<Compte> comptes = new ArrayList<>();
        String req = "SELECT * FROM compte";
        ResultSet result = ste.executeQuery(req);
        while (result.next()) {
            int id = result.getInt("id");
            double solde = result.getDouble("solde");
            double decouvert = result.getDouble("decouvert");
            double MaxDecouvert = result.getDouble(" MaxDecouvert");
            double MaxRetrait = result.getDouble("MaxRetrait");
            double resteCredit = result.getDouble("resteCredit");
            double paiementCreditParMois = result.getDouble("paiementCreditParMois");
            String type = result.getString("type");
            Date ouverture = result.getDate("ouverture");
            double clientId = result.getDouble("clientId");
            comptes.add(new Compte(id, solde, decouvert, MaxDecouvert, MaxRetrait, resteCredit, paiementCreditParMois, type, clientId));
        }
        return comptes;
    }

    @Override
    public Compte findbyId(int id) throws SQLException {

        String req = "SELECT * FROM comptes WHERE id =" + id;

        ResultSet rs = ste.executeQuery(req);
        if (rs.next()) {
            double solde = rs.getDouble("solde");
            double decouvert = rs.getDouble(" decouvert");
            double MaxDecouvert = rs.getDouble(" MaxDecouvert");
            double MaxRetrait = rs.getDouble(" MaxRetrait");
            double resteCredit = rs.getDouble(" resteCredit");
            double paiementCreditParMois = rs.getDouble(" paiementCreditParMois");
            String type = rs.getString("type");
            Date ouverture = rs.getDate(" ouverture ");
            int clientId = rs.getInt("clientId");
            return new Compte(id, solde, decouvert, MaxDecouvert, MaxRetrait, resteCredit, paiementCreditParMois,
                    type, ouverture, clientId);

        }
        return null;
    }

}