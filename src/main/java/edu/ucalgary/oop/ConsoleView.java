package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The ConsoleView class provides a simple console-based user interface for the
 * disaster relief system. It allows users to interact with the system through
 * text-based prompts and displays. This class is responsible for displaying
 * menus, prompting for input, and showing messages to the user.
 */
public class ConsoleView {

    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public int displayMainMenu() {
        System.out.println("=== Disaster Relief System ===");
        System.out.println("1. Manage Victims");
        System.out.println("2. Manage Supplies");
        System.out.println("3. Manage Inquiries");
        System.out.println("4. Manage Medical Records");
        System.out.println("5. Manage Family Relations");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return value;
    }

    public void displayVictims(ArrayList<DisasterVictim> victims) {
        System.out.println("=== Disaster Victims ===");
        for (DisasterVictim victim : victims) {
            System.out.println("ID: " + victim.getId() + " - Name: " + victim.getFirstName() + " " + victim.getLastName() + " - Location: " + victim.getLocation());
        }

    }

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

    public boolean promptConfirmation(String message) {
        System.out.print(message + " (y/n): ");
        String input = scanner.next();
        scanner.nextLine(); // Consume newline
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
    }

    public String promptDeletionMode() {
        System.out.println("1. Soft Delete");
        System.out.println("2. Hard Delete");
        int choice = promptInt("Choose deletion mode", 1, 2);
        return choice == 1 ? "soft" : "hard";
    }

    public String promptString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public int promptInt(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt + " (" + min + "-" + max + "): ");
            value = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
        }
        return value;
    }

    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }

    public void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

}
