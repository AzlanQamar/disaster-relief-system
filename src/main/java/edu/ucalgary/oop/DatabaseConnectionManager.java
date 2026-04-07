package edu.ucalgary.oop;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Manages the PostgreSQL database connection using the Singleton pattern. Reads
 * credentials from src/main/resources/config.txt and provides a single shared
 * connection throughout the application.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class DatabaseConnectionManager {

    private static DatabaseConnectionManager instance = null;
    private Connection connection;

    /**
     * Private constructor that reads database credentials from config.txt and
     * opens a connection to the PostgreSQL database.
     *
     * @throws DatabaseConnectionException if the connection cannot be
     * established
     */
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

    /**
     * Returns the single instance of DatabaseConnectionManager, creating it if
     * it does not yet exist.
     *
     * @return the shared DatabaseConnectionManager instance
     * @throws DatabaseConnectionException if the database connection fails
     */
    public static DatabaseConnectionManager getInstance() throws DatabaseConnectionException {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    /**
     * Returns the active database connection.
     *
     * @return the PostgreSQL Connection object
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the database connection if it is open.
     *
     * @throws DatabaseConnectionException if the connection cannot be closed
     */
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
