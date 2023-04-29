//package Service;
//
//import Entite.Compte;
//import Entite.Credit;
//import Utils.DataSource;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ServiceCredit implements IService<Credit> {
//    private Connection con = DataSource.getInstance().getCon();
//    private Statement ste;
//
//    public ServiceCredit() {
//
//        try {
//            ste = con.createStatement();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    }
//
//    @Override
//    public void ajouter(Credit t) throws SQLException {
//
////        String req = "INSERT INTO `credit` ( `nCredit`, `montant`, `type`, `clientId`) "
////                + "VALUES ('" + t.getnCredit() + "', '" + t.getMontant() + "', '" + t.getType() + "', '" + t.getClientId() + "');";
////        ste.executeUpdate(req);
//    }
//
//    @Override
//    public boolean supprimer(Credit t) throws SQLException {
//        try {
//            PreparedStatement x = (PreparedStatement) con.prepareStatement("delete from credit where id='" + t.getId() + "'");
//            x.execute();
//            return true;
//        } catch (Exception err) {
//            return false;
//        }
//    }
//
//    @Override
//    public boolean modifier(Credit t) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<Credit> readAll() throws SQLException {
//
//        List<Credit> list = new ArrayList<>();
//        ResultSet res = ste.executeQuery("select * from credit");
//
//
//        while (res.next()) {
//            int id = res.getInt("id");
//            int nCompte = res.getInt("nCompte");
//            Double montant = res.getDouble("montant");
//            String type = res.getString("type");
//            int clientId = res.getInt("clientId");
//
////            Credit cr = new Credit(id, nCompte, montant, type, clientId);
////            list.add(cr);
//
//
//        }
//
//        return list;
//    }
//
//    @Override
//    public Credit findbyId(int id) throws SQLException {
//        ResultSet res = ste.executeQuery("select * from credit where id='" + id + "'");
//
//
//        int nCompte = res.getInt("nCompte");
//        Double montant = res.getDouble("montant");
//        String type = res.getString("type");
//        int clientId = res.getInt("clientId");
//
////        Credit cr = new Credit(id, nCompte, montant, type, clientId);
////        return cr;
//
//    }
//}
