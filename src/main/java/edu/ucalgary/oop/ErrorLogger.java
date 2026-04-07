package edu.ucalgary.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Singleton logger for unrecoverable application errors. Writes timestamped
 * error entries to data/errorlog.txt.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class ErrorLogger {

    private static ErrorLogger instance = null;
    private static final String LOG_FILE = "data/errorlog.txt";

    private ErrorLogger() {
    }

    /**
     * Returns the single instance of ErrorLogger, creating it if necessary.
     *
     * @return the ErrorLogger instance
     */
    public static ErrorLogger getInstance() {
        if (instance == null) {
            instance = new ErrorLogger();
        }
        return instance;
    }

    /**
     * Appends a timestamped error entry to data/errorlog.txt including the
     * exception class name and message.
     *
     * @param message a description of what caused the error
     * @param e the exception that was thrown
     */
    public void logError(String message, Exception e) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write("[" + LocalDateTime.now() + "] ERROR: " + message
                    + " | Exception: " + e.getClass().getName()
                    + " | Message: " + e.getMessage() + "\n");
        } catch (IOException ioException) {
            System.err.println("Failed to write to error log: " + ioException.getMessage());
        }
    }
}
