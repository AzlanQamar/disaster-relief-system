package edu.ucalgary.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * The ActionLogger class is responsible for logging actions performed on
 * entities such as adding, updating, deleting, and soft deleting. It follows
 * the singleton design pattern to ensure that only one instance of the logger
 * exists throughout the application. The log file path can be configured, and
 * the class provides methods to log different types of actions with relevant
 * details.
 */
public class ActionLogger {

    private static ActionLogger instance = null;
    private String logFilePath;

    private ActionLogger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public static ActionLogger getInstance() {
        if (instance == null) {
            instance = new ActionLogger("data/action_log.txt");
        }
        return instance;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void logAdd(String entityType, int id, String description) {
        try (FileWriter fw = new FileWriter(logFilePath, true)) {
            String line = "[" + LocalDate.now() + "] ADDED " + entityType + " " + id + " | " + description + "\n";
            fw.write(line);
        } catch (IOException e) {
            System.err.println("Failed to write to log: " + e.getMessage());
        }

    }

    public void logUpdate(String entityType, int id, String oldValue, String newValue) {
        try (FileWriter fw = new FileWriter(logFilePath, true)) {
            String line = "[" + LocalDate.now() + "] UPDATED " + entityType + " " + id + " | from: " + oldValue + " to: " + newValue + "\n";
            fw.write(line);
        } catch (IOException e) {
            System.err.println("Failed to write to log: " + e.getMessage());
        }
    }

    public void logDelete(String entityType, int id, String description) {
        try (FileWriter fw = new FileWriter(logFilePath, true)) {
            String line = "[" + LocalDate.now() + "] DELETED " + entityType + " " + id + " | " + description + "\n";
            fw.write(line);
        } catch (IOException e) {
            System.err.println("Failed to write to log: " + e.getMessage());
        }
    }

    public void logSoftDelete(String entityType, int id, String description) {
        try (FileWriter fw = new FileWriter(logFilePath, true)) {
            String line = "[" + LocalDate.now() + "] SOFT DELETED " + entityType + " " + id + " | " + description + "\n";
            fw.write(line);
        } catch (IOException e) {
            System.err.println("Failed to write to log: " + e.getMessage());
        }
    }
}
