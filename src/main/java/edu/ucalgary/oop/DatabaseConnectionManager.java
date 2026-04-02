package edu.ucalgary.oop;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The DatabaseConnectionManager class is responsible for managing the database
 * connection using the singleton design pattern. It reads the database
 * configuration from a properties file and provides methods to get the
 * connection and close it when done. It also handles exceptions related to
 * database connections by throwing a custom DatabaseConnectionException.
 */
public class DatabaseConnectionManager {

    private static DatabaseConnectionManager instance = null;
    private Connection connection;

    private DatabaseConnectionManager() throws DatabaseConnectionException {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/config.txt"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect: " + e.getMessage());
        }
    }

    public static DatabaseConnectionManager getInstance() throws DatabaseConnectionException {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws DatabaseConnectionException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to close connection: " + e.getMessage());
        }

    }

}
