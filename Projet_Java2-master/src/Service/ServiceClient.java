package Service;

import Entite.Client;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceClient implements IService<Client> {
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;

    public ServiceClient() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(Client t) throws SQLException {
        String req = "INSERT INTO `client` ( `nom`, `prenom`, `adresse`) "
                + "VALUES ('" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getAdresse() + "');";
        ste.executeUpdate(req);
    }

    @Override
    public boolean supprimer(Client t) throws SQLException {
        String req = "DELETE FROM `client` WHERE id=" + t.getId();
        int result = ste.executeUpdate(req);
        return result > 0;
    }

    @Override

    public boolean modifier(Client t) throws SQLException {
        String req = "UPDATE `client` SET nom='" + t.getNom() + "', prenom='" + t.getPrenom() + "', adresse='" + t.getAdresse() + "' WHERE id=" + t.getId();
        int result = ste.executeUpdate(req);
        return result > 0;
    }

    @Override
    public List<Client> readAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String req = "SELECT * FROM `client`";
        ResultSet result = ste.executeQuery(req);
        while (result.next()) {
            int id = result.getInt("id");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adresse = result.getString("adresse");
            clients.add(new Client(id, nom, prenom, adresse));
        }
        return clients;
    }

    @Override
    public Client findbyId(int id) throws SQLException {
        String req = "SELECT * FROM `client` WHERE id=" + id;
        ResultSet result = ste.executeQuery(req);
        if (result.next()) {
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adresse = result.getString("adresse");
            return new Client(id, nom, prenom, adresse);
        }
        return null;
    }

}