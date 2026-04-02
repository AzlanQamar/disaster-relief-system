package edu.ucalgary.oop;

/**
 * The DatabaseConnectionException is a custom exception that is thrown when
 * there is an issue connecting to the database. This exception can be used to
 * handle errors related to database connectivity, such as incorrect
 * credentials, network issues, or database server downtime.
 */
public class DatabaseConnectionException extends Exception {

    public DatabaseConnectionException(String message) {
        super(message);
    }

}
