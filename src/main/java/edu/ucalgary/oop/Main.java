package edu.ucalgary.oop;

/**
 * Entry point for the Disaster Relief System. Initializes core components
 * including the database connection, query handler, user interface, and
 * application controller, then starts the application.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class Main {

    /**
     * Main method that launches the Disaster Relief System. Establishes a
     * database connection, initializes required components, and starts the
     * application controller.
     *
     * @param args command-line arguments (not used)
     * @throws DatabaseConnectionException if a database connection cannot be
     * established
     */
    public static void main(String[] args) throws DatabaseConnectionException {

        try {
            DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
            DatabaseQueryHandler db = new DatabaseQueryHandler(dbManager.getConnection());
            ConsoleView view = new ConsoleView();
            ApplicationController controller = new ApplicationController(db, view);
            controller.run();
        } catch (DatabaseConnectionException e) {
            ErrorLogger.getInstance().logError("Failed to connect to database", e);
            System.err.println("Could not connect to database. Check config.txt and ensure PostgreSQL is running.");
            System.exit(1);
        }
    }
}
