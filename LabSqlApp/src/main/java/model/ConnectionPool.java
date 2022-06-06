package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private static Connection connection;
    private static final String USERNAME ="devuser";
    private static final String  PASSWORD="15011310Zoi@";
    private static final String  URL="jdbc:mysql://127.0.0.1:3306/laboratory";

    public ConnectionPool() {
        try{
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (SQLException e){
            System.out.println("Could not connect to database: "+e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Unable to close connection: " + e.getMessage());
        }
    }
}
