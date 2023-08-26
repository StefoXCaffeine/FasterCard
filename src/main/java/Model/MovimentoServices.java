package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovimentoServices {

    public MovimentoServices(){}

    public boolean registerTransaction(LocalDate date, float imports, String idCard) throws SQLException {
        DBConn db = new DBConn();
        boolean mov=false;
        String insert="INSERT INTO movimenti(dataMovimento, importo, cartaRicevente) VALUES(?,?,?)";
        Connection connection = db.getConnection();
        PreparedStatement query=connection.prepareStatement(insert);
        query.setDate(1, Date.valueOf(date));
        query.setFloat(2, imports);
        query.setString(3, idCard);

        int result=query.executeUpdate();
        if(result!=0){
            mov=true;
        }
        query.close();
        connection.close();
        return mov;
    }

    public List<Movimento> getTransactions(int id) throws SQLException {
        DBConn db = new DBConn();
        List<Movimento> list = new ArrayList<Movimento>();
        Movimento mov = null;
        String all="SELECT movimenti.* FROM (movimenti INNER JOIN carte ON movimenti.cartaRicevente = carte.NumeroCarta) WHERE carte.IDTitolare = ?";
        Connection connection = db.getConnection();
        PreparedStatement query=connection.prepareStatement(all);
        query.setInt(1, id);
        ResultSet transactions = query.executeQuery();
        while (transactions.next()) {
            mov = new Movimento();
            mov.setIdMovimento(transactions.getInt("idMovimento"));
            mov.setDataMovimento(transactions.getDate("dataMovimento"));
            mov.setImporto(transactions.getFloat("importo"));
            mov.setCartaRicevente(transactions.getNString("cartaRicevente"));
            list.add(mov);
        }
        return list;
    }
}
