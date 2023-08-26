package Model;

import java.sql.*;

public class UtenteServices {

    public UtenteServices(){}

    public  boolean registerUser(String nome, String cognome, String email, String password, int userType) throws SQLException {
        boolean register = false;
        DBConn db = new DBConn();
        String insert="INSERT INTO utenti(Nome, Cognome, Email, Password, UserType, NegBlock) VALUES(?,?,?,?,?,?)";
        Connection connection = db.getConnection();
        PreparedStatement query=connection.prepareStatement(insert);
        query.setString(1, nome);
        query.setString(2, cognome);
        query.setString(3, email);
        query.setString(4, password);
        query.setInt(5, userType);
        if(userType==1){
            query.setInt(6, 1);
        }else{
            query.setNull(6, Types.INTEGER);
        }
        int result=query.executeUpdate();
        if(result!=0){
            register=true;
        }
        query.close();
        connection.close();
        return register;
    }

    public boolean alreadyRegistered(String email) throws SQLException{
        boolean exists=false;
        DBConn db = new DBConn();
        String check="SELECT * FROM utenti WHERE Email=?";
        Connection connection = db.getConnection();
        PreparedStatement query=connection.prepareStatement(check);
        query.setString(1, email);
        ResultSet users = query.executeQuery();
        System.out.println(email+"risultati\n"+users);
        if(users.next()){
            exists=true;
        }
        query.close();
        connection.close();
        users.close();

        return exists;
    }

    public boolean getMerchantStatus(String email) throws SQLException {
        DBConn db = new DBConn();
        boolean status=false;
        Connection connection = db.getConnection();
        String check = "SELECT NegBlock FROM utenti WHERE Email=?";
        PreparedStatement query=connection.prepareStatement(check);
        query.setString(1, email);
        ResultSet result = query.executeQuery();
        if(result.next())
            status = result.getBoolean("NegBlock");
        query.close();
        connection.close();
        return status;
    }

    public void updateMerchantStatus(String email, boolean op) throws SQLException {
        DBConn db = new DBConn();
        Connection connection = db.getConnection();
        String create = "UPDATE utenti SET NegBlock=? WHERE Email=?";
        PreparedStatement query=connection.prepareStatement(create);
        query.setBoolean(1, op);
        query.setString(2, email);
        query.executeUpdate();
        query.close();
        connection.close();
    }

    public Utente getUser(String email, String password) throws SQLException{
        DBConn db = new DBConn();
        String check="SELECT * FROM utenti where Email=? AND Password=?";
        Connection connection = db.getConnection();
        PreparedStatement query=connection.prepareStatement(check);
        query.setString(1, email);
        query.setString(2, password);
        ResultSet resultSet = query.executeQuery();
        Utente user = new Utente();
        if(resultSet.next()){

            user.setId(resultSet.getInt("IDUtente"));
            user.setNome(resultSet.getNString("Nome"));
            user.setCognome(resultSet.getNString("Cognome"));
            user.setEmail(resultSet.getNString("Email"));
            user.setPassword(resultSet.getNString("Password"));
            user.setUserType(resultSet.getInt("UserType"));
            user.setNegBlock(resultSet.getInt("NegBlock"));
        }

        query.close();
        resultSet.close();
        connection.close();

        return user;
    }

    public int getuserIDByEmail(String email) throws SQLException {
        DBConn db = new DBConn();
        String check="SELECT IDUtente FROM utenti where Email=?";
        Connection connection = db.getConnection();
        PreparedStatement query=connection.prepareStatement(check);
        query.setString(1, email);
        ResultSet resultSet = query.executeQuery();
        int id = 0;
        if(resultSet.next())
            id = resultSet.getInt("IDutente");
        
        query.close();
        resultSet.close();
        connection.close();

        return id;
    }


}
