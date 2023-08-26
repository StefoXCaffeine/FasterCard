
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

    private String database_name="schemacard";
    private String username="root";
    private String password="root";
    private String url="jdbc:mysql://localhost:3306/"+database_name;

    Connection connection;

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(this.url,this.username,this.password);
        }catch(SQLException | ClassNotFoundException sqlex){
            sqlex.printStackTrace();
        }

        return connection;
    }

}
