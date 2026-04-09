package edu.ucalgary.oop;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Controls the application flow and coordinates between the UI and database.
 * Handles all user interactions for managing victims, supplies, inquiries,
 * medical records, family relations, cultural requirements, and skills.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class ApplicationController {

    private DatabaseInterface db;
    private ConsoleView view;
    private ActionLogger logger;
    private ArrayList<DisasterVictim> victims;
    private ArrayList<Location> locations;
    private ArrayList<Inquiry> inquiries = new ArrayList<>();
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private HashMap<String, Set<String>> availableRequirements = new HashMap<>();

    /**
     * Constructs the ApplicationController, loads all data from the database,
     * and initializes available cultural requirements from the .ser file.
     *
     * @param db the database interface to use for persistence
     * @param view the console view for user interaction
     */
    public ApplicationController(DatabaseInterface db, ConsoleView view) {
        this.db = db;
        this.view = view;
        this.logger = ActionLogger.getInstance();
        this.victims = db.loadAllVictims();
        this.locations = db.loadAllLocations();
        loadAvailableRequirements();
    }

    /**
     * Starts the main application loop, displaying the menu until the user
     * exits.
     */
    public void run() {
        checkExpiredSupplies();
        boolean running = true;
        while (running) {
            int choice = view.displayMainMenu();
            switch (choice) {
                case 1:
                    manageVictims();
                    break;
                case 2:
                    manageSupplies();
                    break;
                case 3:
                    manageInquiries();
                    break;
                case 4:
                    manageMedicalRecords();
                    break;
                case 5:
                    manageFamilyRelations();
                    break;
                case 6:
                    manageCulturalRequirements();
                    break;
                case 7:
                    manageSkills();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    view.displayError("Invalid choice");
            }
        }
    }

    /**
     * Displays the victim management sub-menu and handles victim operations.
     */
    public void manageVictims() {
        while (true) {
            System.out.println("\n=== Manage Victims ===");
            System.out.println("1. View all victims");
            System.out.println("2. Add victim");
            System.out.println("3. Update victim");
            System.out.println("4. Delete victim");
            System.out.println("5. Back");
            int choice = view.promptInt("Enter choice", 1, 5);
            switch (choice) {
                case 1:
                    view.displayVictims(victims);
                    break;
                case 2:
                    addVictim();
                    break;
                case 3:
                    updateVictim();
                    break;
                case 4:
                    deleteVictim();
                    break;
                case 5:
                    return;
                default:
                    view.displayError("Invalid choice");
            }
        }
    }

    /**
     * Prompts the user for victim details and adds a new disaster victim to the
     * system and database.
     */
    public void addVictim() {
        try {
            String firstName = view.promptString("Enter first name");
            String lastName = view.promptString("Enter last name");
            DisasterVictim victim = new DisasterVictim(firstName, LocalDate.now());
            victim.setLastName(lastName);
            if (view.promptConfirmation("Do you know the exact date of birth?")) {
                victim.setDateOfBirth(view.promptDate("Enter date of birth"));
            } else {
                victim.setApproximateAge(view.promptInt("Enter approximate age", 0, 150));
            }
            db.saveVictim(victim);
            victims.add(victim);
            logger.logAdd("disaster victim", victim.getId(), victim.getFirstName() + " " + victim.getLastName());
            view.displaySuccess("Victim added successfully");
        } catch (InvalidAgeException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Prompts the user to select a victim and update their details in the
     * system and database.
     */
    public void updateVictim() {
        view.displayVictims(victims);
        int id = view.promptInt("Enter victim ID to update", 1, Integer.MAX_VALUE);
        DisasterVictim victim = findVictimById(id);
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        String oldValue = victim.getFirstName() + " " + victim.getLastName();
        while (true) {
            System.out.println("\n=== Update Victim ===");
            System.out.println("1. First name (current: " + victim.getFirstName() + ")");
            System.out.println("2. Last name (current: " + victim.getLastName() + ")");
            System.out.println("3. Gender (current: " + victim.getGender() + ")");
            System.out.println("4. Date of birth / approximate age");
            System.out.println("5. Location");
            System.out.println("6. Done");
            int field = view.promptInt("What to update", 1, 6);
            try {
                switch (field) {
                    case 1:
                        victim.setFirstName(view.promptString("Enter new first name"));
                        break;
                    case 2:
                        victim.setLastName(view.promptString("Enter new last name"));
                        break;
                    case 3:
                        victim.setGender(view.promptString("Enter new gender"));
                        break;
                    case 4:
                        if (victim.getDateOfBirth() != null) {
                            view.displayError("Cannot replace birthdate with approximate age");
                        } else if (view.promptConfirmation("Enter exact date of birth?")) {
                            victim.setDateOfBirth(view.promptDate("Enter date (YYYY-MM-DD)"));
                        } else {
                            victim.setApproximateAge(view.promptInt("Enter approximate age", 0, 150));
                        }
                        break;
                    case 5:
                        showLocations();
                        victim.setLocation(locations.get(view.promptInt("Choose location", 1, locations.size()) - 1));
                        break;
                    case 6:
                        db.updateVictim(victim);
                        logger.logUpdate("disaster victim", victim.getId(), oldValue,
                                victim.getFirstName() + " " + victim.getLastName());
                        view.displaySuccess("Victim updated successfully");
                        return;
                }
            } catch (InvalidAgeException | IllegalArgumentException e) {
                view.displayError(e.getMessage());
            }
        }
    }

    /**
     * Prompts the user to select and delete a victim via soft or hard delete,
     * with confirmation steps to prevent accidental data loss.
     */
    public void deleteVictim() {
        view.displayVictims(victims);
        int id = view.promptInt("Enter victim ID to delete", 1, Integer.MAX_VALUE);
        DisasterVictim victim = findVictimById(id);
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        String description = victim.getFirstName() + " " + victim.getLastName();
        if (!view.promptConfirmation("Are you sure you want to delete " + description + "?")) {
            view.displaySuccess("Deletion cancelled");
            return;
        }
        String mode = view.promptDeletionMode();
        if (mode.equals("soft")) {
            db.softDeleteVictim(id);
            victims.remove(victim);
            logger.logSoftDelete("disaster victim", id, description);
            view.displaySuccess("Victim soft deleted successfully");
        } else {
            if (!view.promptConfirmation("WARNING: Hard delete removes ALL data permanently. Confirm?")) {
                view.displaySuccess("Deletion cancelled");
                return;
            }
            db.hardDeleteVictim(id);
            victims.remove(victim);
            logger.logDelete("disaster victim", id, description);
            view.displaySuccess("Victim permanently deleted");
        }
    }

    /**
     * Displays the supply management sub-menu and handles supply operations.
     */
    public void manageSupplies() {
        while (true) {
            System.out.println("\n=== Manage Supplies ===");
            System.out.println("1. View all supplies");
            System.out.println("2. Add supply");
            System.out.println("3. Allocate supply to victim");
            System.out.println("4. Back");
            int choice = view.promptInt("Enter choice", 1, 4);
            switch (choice) {
                case 1:
                    viewSupplies();
                    break;
                case 2:
                    addSupply();
                    break;
                case 3:
                    allocateSupply();
                    break;
                case 4:
                    return;
                default:
                    view.displayError("Invalid choice");
            }
        }
    }

    /**
     * Displays all supplies grouped by location, including expiry dates for
     * perishable items.
     */
    public void viewSupplies() {
        for (Location location : locations) {
            System.out.println("Location: " + location.getName());
            for (Supply supply : location.getSupplies()) {
                String expiry = (supply instanceof PerishableSupply)
                        ? " | Expires: " + ((PerishableSupply) supply).getExpiryDate() : "";
                System.out.println("  ID: " + supply.getId() + " | " + supply.getType() + expiry);
            }
        }
    }

    /**
     * Prompts the user for supply details and adds a new supply to the selected
     * location in the system and database.
     */
    public void addSupply() {
        String type = view.promptString("Enter supply type");
        Supply supply;
        if (view.promptConfirmation("Is it perishable?")) {
            LocalDate expiry = view.promptDate("Enter expiry date (YYYY-MM-DD)");
            supply = new PerishableSupply(type, expiry);
        } else {
            supply = new Supply(type);
        }
        showLocations();
        Location location = locations.get(view.promptInt("Choose location", 1, locations.size()) - 1);
        supply.setLocation(location);
        location.addSupply(supply);
        db.saveSupply(supply);
        logger.logAdd("supply", supply.getId(), supply.getType());
        view.displaySuccess("Supply added successfully");
    }

    /**
     * Prompts the user to select a non-expired supply and a victim, then
     * allocates the supply to that victim in the system and database.
     */
    public void allocateSupply() {
        viewSupplies();
        int supplyId = view.promptInt("Enter supply ID to allocate", 1, Integer.MAX_VALUE);
        Supply supply = findSupplyById(supplyId);
        if (supply == null) {
            view.displayError("Supply not found");
            return;
        }
        if (supply instanceof PerishableSupply && ((PerishableSupply) supply).isExpired()) {
            view.displayError("Cannot allocate expired supply");
            return;
        }
        view.displayVictims(victims);
        DisasterVictim victim = findVictimById(view.promptInt("Enter victim ID", 1, Integer.MAX_VALUE));
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        supply.allocateToVictim(victim, LocalDate.now());
        db.updateSupply(supply);
        logger.logUpdate("supply", supply.getId(), "unallocated", "allocated to victim " + victim.getId());
        view.displaySuccess("Supply allocated successfully");
    }

    /**
     * Displays the inquiry management sub-menu and handles inquiry operations.
     */
    public void manageInquiries() {
        while (true) {
            System.out.println("\n=== Manage Inquiries ===");
            System.out.println("1. View all inquiries");
            System.out.println("2. Add inquiry");
            System.out.println("3. Back");
            int choice = view.promptInt("Enter choice", 1, 3);
            switch (choice) {
                case 1:
                    viewInquiries();
                    break;
                case 2:
                    addInquiry();
                    break;
                case 3:
                    return;
                default:
                    view.displayError("Invalid choice");
            }
        }
    }

    /**
     * Displays all inquiries currently stored in memory.
     */
    private void viewInquiries() {
        if (inquiries.isEmpty()) {
            System.out.println("No inquiries found.");
            return;
        }
        for (Inquiry inquiry : inquiries) {
            System.out.println("ID: " + inquiry.getId()
                    + " | Inquirer: " + inquiry.getInquirer().getFirstName()
                    + " | Subject: " + inquiry.getSubjectPerson().getFirstName()
                    + " | Date: " + inquiry.getInquiryDate());
        }
    }

    /**
     * Prompts the user for inquirer and subject details and logs a new inquiry
     * in the system and database.
     */
    public void addInquiry() {
        String firstName = view.promptString("Enter inquirer's first name");
        String lastName = view.promptString("Enter inquirer's last name");
        String phone = view.promptString("Enter services phone number");
        String info = view.promptString("Enter inquirer info");
        Inquirer inquirer = new Inquirer(firstName, lastName, phone, info);
        view.displayVictims(victims);
        DisasterVictim subject = findVictimById(view.promptInt("Enter subject victim ID", 1, Integer.MAX_VALUE));
        if (subject == null) {
            view.displayError("Victim not found");
            return;
        }
        String details = view.promptString("Enter inquiry details");
        try {
            Inquiry inquiry = new Inquiry(inquirer, subject, details);
            db.saveInquiry(inquiry);
            inquiries.add(inquiry);
            logger.logAdd("inquiry", inquiry.getId(), "Inquirer: " + firstName + " about " + subject.getFirstName());
            view.displaySuccess("Inquiry added successfully");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Displays the medical record management sub-menu and handles medical
     * record operations.
     */
    public void manageMedicalRecords() {
        while (true) {
            System.out.println("\n=== Manage Medical Records ===");
            System.out.println("1. View all medical records");
            System.out.println("2. Add medical record");
            System.out.println("3. Back");
            int choice = view.promptInt("Enter choice", 1, 3);
            switch (choice) {
                case 1:
                    viewMedicalRecords();
                    break;
                case 2:
                    addMedicalRecord();
                    break;
                case 3:
                    return;
                default:
                    view.displayError("Invalid choice");
            }
        }
    }

    /**
     * Displays all medical records currently stored in memory.
     */
    private void viewMedicalRecords() {
        if (medicalRecords.isEmpty()) {
            System.out.println("No medical records found.");
            return;
        }
        for (MedicalRecord record : medicalRecords) {
            System.out.println("ID: " + record.getId()
                    + " | Victim: " + (record.getVictim() != null ? record.getVictim().getFirstName() : "N/A")
                    + " | Details: " + record.getTreatmentDetails()
                    + " | Date: " + record.getTreatmentDate());
        }
    }

    /**
     * Prompts the user for treatment details and adds a new medical record for
     * the selected victim in the system and database.
     */
    public void addMedicalRecord() {
        view.displayVictims(victims);
        DisasterVictim victim = findVictimById(view.promptInt("Enter victim ID", 1, Integer.MAX_VALUE));
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        String details = view.promptString("Enter treatment details");
        LocalDate date = view.promptDate("Enter treatment date (YYYY-MM-DD)");
        showLocations();
        Location location = locations.get(view.promptInt("Choose location", 1, locations.size()) - 1);
        try {
            MedicalRecord record = new MedicalRecord(location, details, date);
            record.setVictim(victim);
            victim.addMedicalRecord(record);
            db.saveMedicalRecord(record);
            medicalRecords.add(record);
            logger.logAdd("medical record", record.getId(), "Victim: " + victim.getFirstName() + " | " + details);
            view.displaySuccess("Medical record added successfully");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Displays the family relation management sub-menu and handles family
     * relation operations.
     */
    public void manageFamilyRelations() {
        while (true) {
            System.out.println("\n=== Manage Family Relations ===");
            System.out.println("1. Add family relation");
            System.out.println("2. Back");
            int choice = view.promptInt("Enter choice", 1, 2);
            switch (choice) {
                case 1:
                    addFamilyRelation();
                    break;
                case 2:
                    return;
                default:
                    view.displayError("Invalid choice");
            }
        }
    }

    /**
     * Prompts the user to select two victims and a relationship type, then
     * creates a family relation between them in the system and database.
     */
    public void addFamilyRelation() {
        view.displayVictims(victims);
        DisasterVictim personOne = findVictimById(view.promptInt("Enter first person ID", 1, Integer.MAX_VALUE));
        if (personOne == null) {
            view.displayError("Victim not found");
            return;
        }
        DisasterVictim personTwo = findVictimById(view.promptInt("Enter second person ID", 1, Integer.MAX_VALUE));
        if (personTwo == null) {
            view.displayError("Victim not found");
            return;
        }
        String relationship = view.promptString("Enter relationship type (e.g. parent, spouse, sibling)");
        try {
            FamilyRelation relation = new FamilyRelation(personOne, personTwo, relationship);
            personOne.addFamilyConnection(relation);
            personTwo.addFamilyConnection(relation);
            db.saveFamilyRelation(relation);
            logger.logAdd("family relation", relation.getId(),
                    personOne.getFirstName() + " " + relationship + " " + personTwo.getFirstName());
            view.displaySuccess("Family relation added successfully");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Displays the cultural requirement management sub-menu and handles
     * cultural requirement operations.
     */
    public void manageCulturalRequirements() {
        while (true) {
            System.out.println("\n=== Manage Cultural Requirements ===");
            System.out.println("1. View victim requirements");
            System.out.println("2. Add requirement");
            System.out.println("3. Remove requirement");
            System.out.println("4. Back");
            int choice = view.promptInt("Enter choice", 1, 4);
            switch (choice) {
                case 1:
                    viewCulturalRequirements();
                    break;
                case 2:
                    addCulturalRequirement();
                    break;
                case 3:
                    removeCulturalRequirement();
                    break;
                case 4:
                    return;
                default:
                    view.displayError("Invalid choice");
            }
        }
    }

    /**
     * Displays all cultural requirements for the selected victim.
     */
    private void viewCulturalRequirements() {
        view.displayVictims(victims);
        DisasterVictim victim = findVictimById(view.promptInt("Enter victim ID", 1, Integer.MAX_VALUE));
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        if (victim.getCulturalRequirements().isEmpty()) {
            System.out.println("No cultural requirements for this victim.");
            return;
        }
        for (CulturalRequirement req : victim.getCulturalRequirements()) {
            System.out.println("Category: " + req.getRequirementCategory() + " | Option: " + req.getRequirementOption());
        }
    }

    /**
     * Prompts the user to select a category and option from the available
     * requirements loaded from the .ser file, then adds it to the selected
     * victim.
     */
    public void addCulturalRequirement() {
        view.displayVictims(victims);
        DisasterVictim victim = findVictimById(view.promptInt("Enter victim ID", 1, Integer.MAX_VALUE));
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        ArrayList<String> categories = new ArrayList<>(availableRequirements.keySet());
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        String category = categories.get(view.promptInt("Choose category", 1, categories.size()) - 1);
        ArrayList<String> options = new ArrayList<>(availableRequirements.get(category));
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        String option = options.get(view.promptInt("Choose option", 1, options.size()) - 1);
        try {
            CulturalRequirement req = new CulturalRequirement(category, option);
            victim.addCulturalRequirement(req);
            db.saveCulturalRequirement(victim.getId(), req);
            logger.logAdd("cultural requirement", victim.getId(), category + ": " + option);
            view.displaySuccess("Cultural requirement added successfully");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Prompts the user to select and remove a cultural requirement from the
     * selected victim in the system and database.
     */
    public void removeCulturalRequirement() {
        view.displayVictims(victims);
        DisasterVictim victim = findVictimById(view.promptInt("Enter victim ID", 1, Integer.MAX_VALUE));
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        if (victim.getCulturalRequirements().isEmpty()) {
            System.out.println("No cultural requirements to remove.");
            return;
        }
        ArrayList<CulturalRequirement> reqs = victim.getCulturalRequirements();
        for (int i = 0; i < reqs.size(); i++) {
            System.out.println((i + 1) + ". " + reqs.get(i).getRequirementCategory() + ": " + reqs.get(i).getRequirementOption());
        }
        String category = reqs.get(view.promptInt("Choose requirement to remove", 1, reqs.size()) - 1).getRequirementCategory();
        try {
            victim.removeCulturalRequirement(category);
            db.deleteCulturalRequirement(victim.getId(), category);
            logger.logDelete("cultural requirement", victim.getId(), category);
            view.displaySuccess("Cultural requirement removed successfully");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Displays the skill management sub-menu and handles skill operations.
     */
    public void manageSkills() {
        while (true) {
            System.out.println("\n=== Manage Skills ===");
            System.out.println("1. View victim skills");
            System.out.println("2. Add skill");
            System.out.println("3. Remove skill");
            System.out.println("4. Search by category");
            System.out.println("5. Back");
            int choice = view.promptInt("Enter choice", 1, 5);
            switch (choice) {
                case 1:
                    viewSkills();
                    break;
                case 2:
                    addSkill();
                    break;
                case 3:
                    removeSkill();
                    break;
                case 4:
                    searchSkillsByCategory();
                    break;
                case 5:
                    return;
                default:
                    view.displayError("Invalid choice");
            }
        }
    }

    /**
     * Displays all skills for the selected victim.
     */
    private void viewSkills() {
        view.displayVictims(victims);
        DisasterVictim victim = findVictimById(view.promptInt("Enter victim ID", 1, Integer.MAX_VALUE));
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        if (victim.getVictimSkills().isEmpty()) {
            System.out.println("No skills found.");
            return;
        }
        for (VictimSkill vs : victim.getVictimSkills()) {
            System.out.println("ID: " + vs.getId() + " | " + vs.getSkill().getSkillName()
                    + " (" + vs.getSkill().getCategory() + ") | Level: " + vs.getProficiencyLevel());
        }
    }

    /**
     * Prompts the user to select a skill category and details, then adds the
     * appropriate skill subtype to the selected victim in the system and
     * database.
     */
    public void addSkill() {
        view.displayVictims(victims);
        DisasterVictim victim = findVictimById(view.promptInt("Enter victim ID", 1, Integer.MAX_VALUE));
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        System.out.println("Skill category: 1. Medical  2. Language  3. Trade");
        int catChoice = view.promptInt("Choose category", 1, 3);
        String proficiency = view.promptString("Enter proficiency level (beginner/intermediate/advanced)");
        String skillName = view.promptString("Enter skill name");
        Skill skill = new Skill(skillName, catChoice == 1 ? "medical" : catChoice == 2 ? "language" : "trade");
        try {
            VictimSkill vs;
            if (catChoice == 1) {
                String certType = view.promptString("Enter certification type (first-aid/counseling/nursing/doctor)");
                LocalDate certExpiry = view.promptDate("Enter certification expiry (YYYY-MM-DD)");
                vs = new MedicalSkill(skill, proficiency, certType, certExpiry);
            } else if (catChoice == 2) {
                boolean readWrite = view.promptConfirmation("Can read/write?");
                boolean speakListen = view.promptConfirmation("Can speak/listen?");
                vs = new LanguageSkill(skill, proficiency, skillName, readWrite, speakListen);
            } else {
                vs = new TradeSkill(skill, proficiency, skillName);
            }
            victim.addVictimSkill(vs);
            db.saveVictimSkill(victim.getId(), vs);
            logger.logAdd("skill", vs.getId(), skillName + " for victim " + victim.getId());
            view.displaySuccess("Skill added successfully");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Prompts the user to select a skill by ID and removes it from the selected
     * victim in the system and database.
     */
    public void removeSkill() {
        view.displayVictims(victims);
        DisasterVictim victim = findVictimById(view.promptInt("Enter victim ID", 1, Integer.MAX_VALUE));
        if (victim == null) {
            view.displayError("Victim not found");
            return;
        }
        if (victim.getVictimSkills().isEmpty()) {
            System.out.println("No skills to remove.");
            return;
        }
        viewSkills();
        int skillId = view.promptInt("Enter skill ID to remove", 1, Integer.MAX_VALUE);
        try {
            victim.removeVictimSkill(skillId);
            db.deleteVictimSkill(skillId);
            logger.logDelete("skill", skillId, "removed from victim " + victim.getId());
            view.displaySuccess("Skill removed successfully");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Prompts the user to choose a skill category and displays all non-deleted
     * victims with skills in that category.
     */
    public void searchSkillsByCategory() {
        System.out.println("1. Medical  2. Language  3. Trade");
        int choice = view.promptInt("Choose category", 1, 3);
        String category = choice == 1 ? "medical" : choice == 2 ? "language" : "trade";
        boolean found = false;
        for (DisasterVictim victim : victims) {
            for (VictimSkill vs : victim.getVictimSkills()) {
                if (vs.getSkill().getCategory().equalsIgnoreCase(category)) {
                    System.out.println("Victim: " + victim.getFirstName() + " " + victim.getLastName()
                            + " | Skill: " + vs.getSkill().getSkillName()
                            + " | Level: " + vs.getProficiencyLevel());
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No skills found for category: " + category);
        }
    }

    /**
     * Loads available cultural requirement options from the serialized .ser
     * file into the availableRequirements map.
     */
    public void loadAvailableRequirements() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("src/main/resources/available_requirements1.ser"))) {
            CulturalOptions options = (CulturalOptions) ois.readObject();
            HashMap<String, Set<String>> map = options.getAccommodations();
            for (String category : map.keySet()) {
                availableRequirements.put(category, map.get(category));
            }
        } catch (Exception e) {
            ErrorLogger.getInstance().logError("Failed to load requirements", e);
        }
    }

    /**
     * Finds a disaster victim by their database ID.
     *
     * @param id the ID to search for
     * @return the matching DisasterVictim, or null if not found
     */
    private DisasterVictim findVictimById(int id) {
        for (DisasterVictim v : victims) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    /**
     * Finds a supply by its database ID across all locations.
     *
     * @param id the ID to search for
     * @return the matching Supply, or null if not found
     */
    private Supply findSupplyById(int id) {
        for (Location location : locations) {
            for (Supply s : location.getSupplies()) {
                if (s.getId() == id) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * Prints a numbered list of all locations to the console.
     */
    private void showLocations() {
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i).getName());
        }
    }

    /**
     * Checks all locations for expired perishable supplies and displays a
     * warning to the user at application startup.
     */
    private void checkExpiredSupplies() {
        ArrayList<Supply> expired = new ArrayList<>();
        for (Location location : locations) {
            for (Supply supply : location.getSupplies()) {
                if (supply instanceof PerishableSupply && ((PerishableSupply) supply).isExpired()) {
                    expired.add(supply);
                }
            }
        }
        view.displayExpiredSupplies(expired);
    }
}
