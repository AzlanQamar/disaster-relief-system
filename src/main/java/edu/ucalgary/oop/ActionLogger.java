package edu.ucalgary.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Singleton logger for recording user-driven actions in the disaster relief
 * system. Appends timestamped entries to data/action_log.txt for adds, updates,
 * deletes, and soft deletes.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class ActionLogger {

    private static ActionLogger instance = null;
    private final String LOG_FILE = "data/action_log.txt";

    private ActionLogger() {
    }

    /**
     * Returns the single instance of ActionLogger, creating it if necessary.
     *
     * @return the ActionLogger instance
     */
    public static ActionLogger getInstance() {
        if (instance == null) {
            instance = new ActionLogger();
        }
        return instance;
    }

    /**
     * Logs an addition action to the log file.
     *
     * @param entityType the type of entity added (e.g. "disaster victim")
     * @param id the database ID of the added entity
     * @param description a description of the addition
     */
    public void logAdd(String entityType, int id, String description) {
        writeEntry("[" + LocalDate.now() + "] ADDED " + entityType + " " + id + " | " + description);
    }

    /**
     * Logs an update action to the log file.
     *
     * @param entityType the type of entity updated
     * @param id the database ID of the updated entity
     * @param oldValue the value before the update
     * @param newValue the value after the update
     */
    public void logUpdate(String entityType, int id, String oldValue, String newValue) {
        writeEntry("[" + LocalDate.now() + "] UPDATED " + entityType + " " + id + " | from: " + oldValue + " to: " + newValue);
    }

    /**
     * Logs a hard delete action to the log file.
     *
     * @param entityType the type of entity deleted
     * @param id the database ID of the deleted entity
     * @param description a description of the deletion
     */
    public void logDelete(String entityType, int id, String description) {
        writeEntry("[" + LocalDate.now() + "] DELETED " + entityType + " " + id + " | " + description);
    }

    /**
     * Logs a soft delete action to the log file.
     *
     * @param entityType the type of entity soft deleted
     * @param id the database ID of the soft deleted entity
     * @param description a description of the soft deletion
     */
    public void logSoftDelete(String entityType, int id, String description) {
        writeEntry("[" + LocalDate.now() + "] SOFT DELETED " + entityType + " " + id + " | " + description);
    }

    /**
     * Appends a single entry to the log file.
     *
     * @param entry the log entry text to write
     */
    private void writeEntry(String entry) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(entry + "\n");
        } catch (IOException e) {
            System.err.println("Failed to write to log: " + e.getMessage());
        }
    }
}
