package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a person who has been affected by a disaster. Extends Person with
 * disaster-specific attributes including age, gender, location, medical
 * records, supplies, family connections, cultural requirements, and skills.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class DisasterVictim extends Person {

    private Integer approximateAge;
    private LocalDate dateOfBirth;
    private String gender;
    private final LocalDate ENTRY_DATE;
    private boolean isSoftDeleted = false;
    private Location location;
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private ArrayList<Supply> personalBelongings = new ArrayList<>();
    private ArrayList<FamilyRelation> familyConnections = new ArrayList<>();
    private ArrayList<CulturalRequirement> culturalRequirements = new ArrayList<>();
    private ArrayList<VictimSkill> victimSkills = new ArrayList<>();

    /**
     * Constructs a DisasterVictim with a first name and entry date.
     *
     * @param firstName the victim's first name
     * @param entryDate the date the victim was entered into the system
     * @throws IllegalArgumentException if entryDate is null
     */
    public DisasterVictim(String firstName, LocalDate entryDate) throws IllegalArgumentException {
        super(firstName, null);
        if (entryDate == null) {
            throw new IllegalArgumentException("Entry date cannot be null");
        }
        this.ENTRY_DATE = entryDate;
    }

    /**
     * Returns the victim's date of birth.
     *
     * @return the date of birth, or null if not set
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the victim's exact date of birth. Cannot be set if an approximate
     * age is already recorded.
     *
     * @param dateOfBirth the date of birth to set
     * @throws InvalidAgeException if an approximate age is already set
     * @throws IllegalArgumentException if the date is in the future
     */
    public void setDateOfBirth(LocalDate dateOfBirth) throws InvalidAgeException, IllegalArgumentException {
        if (approximateAge != null) {
            throw new InvalidAgeException("Cannot set both approximate age and date of birth");
        }
        if (dateOfBirth != null && dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the victim's approximate age.
     *
     * @return the approximate age, or null if not set
     */
    public Integer getApproximateAge() {
        return approximateAge;
    }

    /**
     * Sets the victim's approximate age. Cannot be set if a date of birth is
     * already recorded.
     *
     * @param approximateAge the approximate age to set
     * @throws InvalidAgeException if a date of birth is already set
     * @throws IllegalArgumentException if age is negative or greater than 150
     */
    public void setApproximateAge(int approximateAge) throws InvalidAgeException, IllegalArgumentException {
        if (dateOfBirth != null) {
            throw new InvalidAgeException("Cannot set both date of birth and approximate age");
        }
        if (approximateAge < 0) {
            throw new IllegalArgumentException("Approximate age cannot be negative");
        }
        if (approximateAge > 150) {
            throw new IllegalArgumentException("Approximate age cannot be greater than 150");
        }
        this.approximateAge = approximateAge;
    }

    /**
     * Returns the date the victim was entered into the system.
     *
     * @return the entry date
     */
    public LocalDate getEntryDate() {
        return ENTRY_DATE;
    }

    /**
     * Returns the victim's gender.
     *
     * @return the gender string
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the victim's gender.
     *
     * @param gender the gender to set
     * @throws IllegalArgumentException if gender is null or empty
     */
    public void setGender(String gender) throws IllegalArgumentException {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        if (gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be empty");
        }
        this.gender = gender;
    }

    /**
     * Returns the victim's current location.
     *
     * @return the Location, or null if not set
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the victim's current location.
     *
     * @param location the Location to assign
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns whether this victim has been soft deleted.
     *
     * @return true if soft deleted, false otherwise
     */
    public boolean isSoftDeleted() {
        return isSoftDeleted;
    }

    /**
     * Marks this victim as soft deleted, hiding them from the UI while
     * retaining their data in the database.
     */
    public void softDelete() {
        this.isSoftDeleted = true;
    }

    /**
     * Returns all medical records for this victim.
     *
     * @return the list of MedicalRecord objects
     */
    public ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    /**
     * Adds a medical record to this victim.
     *
     * @param medicalRecord the MedicalRecord to add
     * @throws IllegalArgumentException if medicalRecord is null
     */
    public void addMedicalRecord(MedicalRecord medicalRecord) throws IllegalArgumentException {
        if (medicalRecord == null) {
            throw new IllegalArgumentException("Medical record cannot be null");
        }
        this.medicalRecords.add(medicalRecord);
    }

    /**
     * Returns all personal belongings (allocated supplies) for this victim.
     *
     * @return the list of Supply objects
     */
    public ArrayList<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    /**
     * Adds a supply to this victim's personal belongings.
     *
     * @param supply the Supply to add
     * @throws IllegalArgumentException if supply is null
     */
    public void addPersonalBelonging(Supply supply) throws IllegalArgumentException {
        if (supply == null) {
            throw new IllegalArgumentException("Supply cannot be null");
        }
        this.personalBelongings.add(supply);
    }

    /**
     * Removes a supply from this victim's personal belongings.
     *
     * @param supply the Supply to remove
     * @throws IllegalArgumentException if the supply is not found
     */
    public void removePersonalBelonging(Supply supply) throws IllegalArgumentException {
        if (!personalBelongings.remove(supply)) {
            throw new IllegalArgumentException("Supply not found in personal belongings");
        }
    }

    /**
     * Returns all family connections for this victim.
     *
     * @return the list of FamilyRelation objects
     */
    public ArrayList<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    }

    /**
     * Adds a family connection for this victim.
     *
     * @param familyRelation the FamilyRelation to add
     * @throws IllegalArgumentException if familyRelation is null
     */
    public void addFamilyConnection(FamilyRelation familyRelation) throws IllegalArgumentException {
        if (familyRelation == null) {
            throw new IllegalArgumentException("Family relation cannot be null");
        }
        this.familyConnections.add(familyRelation);
    }

    /**
     * Removes a family connection from this victim.
     *
     * @param familyRelation the FamilyRelation to remove
     * @throws IllegalArgumentException if the relation is not found
     */
    public void removeFamilyConnection(FamilyRelation familyRelation) throws IllegalArgumentException {
        if (!familyConnections.remove(familyRelation)) {
            throw new IllegalArgumentException("Family relation not found in family connections");
        }
    }

    /**
     * Returns all cultural requirements for this victim.
     *
     * @return the list of CulturalRequirement objects
     */
    public ArrayList<CulturalRequirement> getCulturalRequirements() {
        return culturalRequirements;
    }

    /**
     * Adds a cultural requirement to this victim. Only one requirement per
     * category is permitted.
     *
     * @param culturalRequirement the CulturalRequirement to add
     * @throws IllegalArgumentException if null or a requirement for that
     * category already exists
     */
    public void addCulturalRequirement(CulturalRequirement culturalRequirement) throws IllegalArgumentException {
        if (culturalRequirement == null) {
            throw new IllegalArgumentException("Cultural requirement cannot be null");
        }
        for (CulturalRequirement existing : culturalRequirements) {
            if (existing.getRequirementCategory().equals(culturalRequirement.getRequirementCategory())) {
                throw new IllegalArgumentException("A requirement for category '"
                        + culturalRequirement.getRequirementCategory() + "' already exists");
            }
        }
        this.culturalRequirements.add(culturalRequirement);
    }

    /**
     * Removes a cultural requirement by category name.
     *
     * @param category the category of the requirement to remove
     * @throws IllegalArgumentException if no requirement with that category
     * exists
     */
    public void removeCulturalRequirement(String category) throws IllegalArgumentException {
        CulturalRequirement toRemove = null;
        for (CulturalRequirement req : culturalRequirements) {
            if (req.getRequirementCategory().equals(category)) {
                toRemove = req;
                break;
            }
        }
        if (toRemove == null) {
            throw new IllegalArgumentException("No requirement found for category: " + category);
        }
        culturalRequirements.remove(toRemove);
    }

    /**
     * Returns all skills for this victim.
     *
     * @return the list of VictimSkill objects
     */
    public ArrayList<VictimSkill> getVictimSkills() {
        return victimSkills;
    }

    /**
     * Adds a skill to this victim.
     *
     * @param victimSkill the VictimSkill to add
     * @throws IllegalArgumentException if victimSkill is null
     */
    public void addVictimSkill(VictimSkill victimSkill) throws IllegalArgumentException {
        if (victimSkill == null) {
            throw new IllegalArgumentException("Victim skill cannot be null");
        }
        this.victimSkills.add(victimSkill);
    }

    /**
     * Removes a skill from this victim by its database ID.
     *
     * @param id the ID of the VictimSkill to remove
     * @throws IllegalArgumentException if no skill with that ID is found
     */
    public void removeVictimSkill(int id) throws IllegalArgumentException {
        VictimSkill toRemove = null;
        for (VictimSkill skill : victimSkills) {
            if (skill.getId() == id) {
                toRemove = skill;
                break;
            }
        }
        if (toRemove == null) {
            throw new IllegalArgumentException("No skill found with ID: " + id);
        }
        victimSkills.remove(toRemove);
    }
}
