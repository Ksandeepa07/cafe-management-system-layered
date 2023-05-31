package lk.ijse.cafe_au_lait.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection con;

    private DBConnection() throws SQLException {
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cafe_au__lait",
                "root",
                "1234"
        );
    }



    public static DBConnection getInstance() throws SQLException {
        /*if(dbConnection == null) {
            dbConnection = new DBConnection();
            return dbConnection;
        } else {
            return dbConnection;
        }*/
        return (null == dbConnection) ? dbConnection = new DBConnection()
                : dbConnection;
    }

    public Connection getConnection() {
        return con;
    }

}
