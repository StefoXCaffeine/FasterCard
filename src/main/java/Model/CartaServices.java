package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CartaServices {

    public CartaServices(){}

    public String checkCredit(String numCarta) throws SQLException {
        DBConn db = new DBConn();
        String check="SELECT Credito FROM carte WHERE NumeroCarta=?";
        Connection connection = db.getConnection();
        PreparedStatement query=connection.prepareStatement(check);
        query.setString(1, numCarta);
        ResultSet result = query.executeQuery();
        String credit=null;
        if(result.next()){
            credit= String.valueOf(result.getFloat("Credito"));
        }

        query.close();
        connection.close();

        return credit;
    }

    public String updateBalance(String numCarta, float value, boolean operation) throws SQLException {
        DBConn db = new DBConn();
        Connection connection = db.getConnection();
        float oldCredit = Float.parseFloat(checkCredit(numCarta));
        float newCredit = 0;
        if(operation){
            newCredit = oldCredit + value;
        }else{
            newCredit = oldCredit - value;
        }

        String update = "UPDATE carte SET Credito=? WHERE NumeroCarta=?";
        PreparedStatement query=connection.prepareStatement(update);
        query.setFloat(1, newCredit);
        query.setString(2, numCarta);
        query.executeUpdate();
        query.close();
        connection.close();

        return String.valueOf(newCredit);
    }

    public void createCard(String numCarta, int idUser, float value, LocalDate dataCreazione, LocalDate dataScadenza, String cvv) throws SQLException {
        DBConn db = new DBConn();
        Connection connection = db.getConnection();
        String create = "INSERT INTO carte(NumeroCarta, DataCreazione, DataScadenza, FlagBlock, Cvv, Credito, IDTitolare) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement query=connection.prepareStatement(create);
        query.setString(1, numCarta);
        query.setDate(2, Date.valueOf(dataCreazione));
        query.setDate(3, Date.valueOf(dataScadenza));
        query.setBoolean(4, true);
        query.setString(5, cvv);
        query.setFloat(6, value);
        query.setInt(7, idUser);
        query.executeUpdate();
        query.close();
        connection.close();
    }

    public boolean getCardStatus(String numCarta) throws SQLException {
        DBConn db = new DBConn();
        boolean status=false;
        Connection connection = db.getConnection();
        String create = "SELECT FlagBlock from carte WHERE NumeroCarta=?";
        PreparedStatement query=connection.prepareStatement(create);
        query.setString(1, numCarta);
        ResultSet result = query.executeQuery();
        if(result.next())
            status = result.getBoolean("FlagBlock");
        query.close();
        connection.close();
        return status;
    }

    public void updateCardStatus(String numCarta, boolean op) throws SQLException {
        DBConn db = new DBConn();
        Connection connection = db.getConnection();
        String create = "UPDATE carte SET FlagBlock=? WHERE NumeroCarta=?";
        PreparedStatement query=connection.prepareStatement(create);
        query.setBoolean(1, op);
        query.setString(2, numCarta);
        query.executeUpdate();
        query.close();
        connection.close();
    }

    public boolean cardExists(String numCarta) throws SQLException {
        DBConn db = new DBConn();
        Connection connection = db.getConnection();
        boolean exists=false;
        String check="SELECT * FROM carte where NumeroCarta=?";
        PreparedStatement query=connection.prepareStatement(check);
        query.setString(1, numCarta);
        ResultSet resultSet = query.executeQuery();
        if(resultSet.next())
            exists=true;
        query.close();
        resultSet.close();
        connection.close();

        return exists;
    }

    public static List<Carta> getUserCards(int id) throws SQLException {
        DBConn db = new DBConn();
        List<Carta> list = new ArrayList<Carta>();
        Carta carta = null;
        String all="SELECT carte.* FROM (carte INNER JOIN utenti ON carte.IDTitolare = utenti.IDUtente) WHERE utenti.IDUtente = ?";
        Connection connection = db.getConnection();
        PreparedStatement query=connection.prepareStatement(all);
        query.setInt(1, id);
        ResultSet transactions = query.executeQuery();
        while (transactions.next()) {
            carta = new Carta();
            carta.setNumCarta(transactions.getString("NumeroCarta"));
            carta.setDataCreazione(transactions.getDate("DataCreazione"));
            carta.setDataScadenza(transactions.getDate("DataScadenza"));
            carta.setBlock(transactions.getBoolean("FlagBlock"));
            carta.setCvv(transactions.getString("Cvv"));
            carta.setCredito(transactions.getFloat("Credito"));
            carta.setIdTitolare(transactions.getInt("IDTitolare"));
            list.add(carta);
        }
        return list;
    }
}
