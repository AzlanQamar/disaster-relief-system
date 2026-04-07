package edu.ucalgary.oop;

/**
 * Thrown when a connection to the PostgreSQL database cannot be established.
 * This may occur due to incorrect credentials, network issues, or the database
 * server being unavailable.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class DatabaseConnectionException extends Exception {

    /**
     * Constructs a DatabaseConnectionException with the specified message.
     *
     * @param message a description of the connection failure
     */
    public DatabaseConnectionException(String message) {
        super(message);
    }
}
