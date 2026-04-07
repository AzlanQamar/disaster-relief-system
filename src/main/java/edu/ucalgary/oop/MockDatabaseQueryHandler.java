package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Mock implementation of the DatabaseInterface used for testing purposes.
 * Provides hardcoded data for victims and locations, allowing testing of
 * application logic without requiring a real database connection.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class MockDatabaseQueryHandler implements DatabaseInterface {

    private ArrayList<DisasterVictim> victims;
    private ArrayList<Location> locations;

    /**
     * Constructs a MockDatabaseQueryHandler and initializes it with predefined
     * test data for victims and locations.
     */
    public MockDatabaseQueryHandler() {
        victims = new ArrayList<>();
        locations = new ArrayList<>();

        // Hardcoded test data
        Location loc1 = new Location("TELUS Convention Centre", "136 8 Ave SE, Calgary");
        Location loc2 = new Location("University of Calgary", "2500 University Dr NW");
        locations.add(loc1);
        locations.add(loc2);

        DisasterVictim v1 = new DisasterVictim("Teruya", LocalDate.of(2025, 1, 18));
        v1.setLocation(loc1);
        victims.add(v1);

        DisasterVictim v2 = new DisasterVictim("Freda", LocalDate.of(2025, 1, 18));
        v2.setLocation(loc2);
        victims.add(v2);
    }

    /**
     * Returns all disaster victims in the mock database.
     *
     * @return the list of DisasterVictim objects
     */
    @Override
    public ArrayList<DisasterVictim> loadAllVictims() {
        return victims;
    }

    /**
     * Returns all locations in the mock database.
     *
     * @return the list of Location objects
     */
    @Override
    public ArrayList<Location> loadAllLocations() {
        return locations;
    }

    /**
     * Mock method for saving a victim. No operation performed.
     *
     * @param victim the DisasterVictim to save
     */
    @Override
    public void saveVictim(DisasterVictim victim) {
    }

    /**
     * Mock method for updating a victim. No operation performed.
     *
     * @param victim the DisasterVictim to update
     */
    @Override
    public void updateVictim(DisasterVictim victim) {
    }

    /**
     * Mock method for soft deleting a victim. No operation performed.
     *
     * @param id the ID of the victim to delete
     */
    @Override
    public void softDeleteVictim(int id) {
    }

    /**
     * Mock method for hard deleting a victim. No operation performed.
     *
     * @param id the ID of the victim to delete
     */
    @Override
    public void hardDeleteVictim(int id) {
    }

    /**
     * Mock method for saving a supply. No operation performed.
     *
     * @param supply the Supply to save
     */
    @Override
    public void saveSupply(Supply supply) {
    }

    /**
     * Mock method for updating a supply. No operation performed.
     *
     * @param supply the Supply to update
     */
    @Override
    public void updateSupply(Supply supply) {
    }

    /**
     * Mock method for saving a medical record. No operation performed.
     *
     * @param medicalRecord the MedicalRecord to save
     */
    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord) {
    }

    /**
     * Mock method for saving an inquiry. No operation performed.
     *
     * @param inquiry the Inquiry to save
     */
    @Override
    public void saveInquiry(Inquiry inquiry) {
    }

    /**
     * Mock method for saving a family relation. No operation performed.
     *
     * @param familyRelation the FamilyRelation to save
     */
    @Override
    public void saveFamilyRelation(FamilyRelation familyRelation) {
    }

    /**
     * Mock method for saving a cultural requirement. No operation performed.
     *
     * @param victimId the ID of the victim
     * @param req the CulturalRequirement to save
     */
    @Override
    public void saveCulturalRequirement(int victimId, CulturalRequirement req) {
    }

    /**
     * Mock method for deleting a cultural requirement. No operation performed.
     *
     * @param victimId the ID of the victim
     * @param category the category to delete
     */
    @Override
    public void deleteCulturalRequirement(int victimId, String category) {
    }

    /**
     * Mock method for saving a victim skill. No operation performed.
     *
     * @param victimId the ID of the victim
     * @param skill the VictimSkill to save
     */
    @Override
    public void saveVictimSkill(int victimId, VictimSkill skill) {
    }

    /**
     * Mock method for deleting a victim skill. No operation performed.
     *
     * @param skillId the ID of the skill to delete
     */
    @Override
    public void deleteVictimSkill(int skillId) {
    }
}
