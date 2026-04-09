package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides a console-based user interface for the disaster relief system.
 * Responsible for displaying menus, prompting for input, and showing messages.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class ConsoleView {

    private Scanner scanner;

    /**
     * Constructs a ConsoleView and initializes the input scanner.
     */
    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu and returns the user's choice.
     *
     * @return the menu option selected by the user
     */
    public int displayMainMenu() {
        System.out.println("=== Disaster Relief System ===");
        System.out.println("1. Manage Victims");
        System.out.println("2. Manage Supplies");
        System.out.println("3. Manage Inquiries");
        System.out.println("4. Manage Locations");
        System.out.println("5. Manage Medical Records");
        System.out.println("6. Manage Family Relations");
        System.out.println("7. Manage Cultural Requirements");
        System.out.println("8. Manage Skills");
        System.out.println("9. Exit");
        int value = promptInt("Enter choice", 1, 9);
        return value;
    }

    /**
     * Displays a list of disaster victims with their ID, name, and location.
     *
     * @param victims the list of victims to display
     */
    public void displayVictims(ArrayList<DisasterVictim> victims) {
        System.out.println("=== Disaster Victims ===");
        for (DisasterVictim victim : victims) {
            String age = victim.getDateOfBirth() != null
                    ? "DOB: " + victim.getDateOfBirth()
                    : victim.getApproximateAge() != null ? "Age: ~" + victim.getApproximateAge() : "Age: N/A";
            System.out.println("ID: " + victim.getId() + " - Name: " + victim.getFirstName()
                    + " " + victim.getLastName() + " | " + age);
        }
    }

    /**
     * Displays a list of expired supplies, or a message if none exist.
     *
     * @param supplies the list of expired supplies to display
     */
    public void displayExpiredSupplies(ArrayList<Supply> supplies) {
        if (supplies.isEmpty()) {
            System.out.println("No expired supplies.");
            return;
        }
        System.out.println("=== Expired Supplies ===");
        for (Supply supply : supplies) {
            System.out.println("ID: " + supply.getId() + " | Type: " + supply.getType());
        }
    }

    /**
     * Prompts the user with a yes/no question and returns their response.
     *
     * @param message the question to display to the user
     * @return true if the user answered yes, false otherwise
     */
    public boolean promptConfirmation(String message) {
        while (true) {
            System.out.print(message + " (y/n): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                return true;
            }
            if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                return false;
            }
            System.out.println("Please enter y or n.");
        }
    }

    /**
     * Prompts the user to choose between soft delete and hard delete.
     *
     * @return "soft" if the user chose soft delete, "hard" otherwise
     */
    public String promptDeletionMode() {
        System.out.println("1. Soft Delete");
        System.out.println("2. Hard Delete");
        int choice = promptInt("Choose deletion mode", 1, 2);
        return choice == 1 ? "soft" : "hard";
    }

    /**
     * Displays a prompt and returns the string entered by the user.
     *
     * @param prompt the message to display before reading input
     * @return the string entered by the user
     */
    public String promptString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user for an integer within a specified range, re-prompting
     * until a valid value is entered.
     *
     * @param prompt the message to display before reading input
     * @param min the minimum acceptable value (inclusive)
     * @param max the maximum acceptable value (inclusive)
     * @return a valid integer entered by the user within [min, max]
     */
    public int promptInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " (" + min + "-" + max + "): ");
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Displays a success message to the user.
     *
     * @param message the success message to display
     */
    public void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Prompts the user for a date in YYYY-MM-DD format, re-prompting on invalid
     * input.
     *
     * @param prompt the message to display
     * @return a valid LocalDate entered by the user
     */
    public LocalDate promptDate(String prompt) {
        while (true) {
            System.out.print(prompt + " (YYYY-MM-DD): ");
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input);
            } catch (Exception e) {
                System.out.println("\"Invalid date format. Please use YYYY-MM-DD with zero-padded month and day (e.g. 2028-09-10).\");.");
            }
        }
    }
}
