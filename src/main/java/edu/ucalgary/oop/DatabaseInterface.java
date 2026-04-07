package edu.ucalgary.oop;

import java.util.ArrayList;

/**
 * Defines the contract for all database interactions in the disaster relief
 * system. Implementations include DatabaseQueryHandler (real DB) and
 * MockDatabaseQueryHandler (for testing).
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public interface DatabaseInterface {

    /**
     * Loads all non-deleted disaster victims from the database.
     *
     * @return a list of all active DisasterVictim objects
     */
    ArrayList<DisasterVictim> loadAllVictims();

    /**
     * Loads all locations from the database.
     *
     * @return a list of all Location objects
     */
    ArrayList<Location> loadAllLocations();

    /**
     * Saves a new disaster victim to the database and sets their generated ID.
     *
     * @param victim the DisasterVictim to save
     */
    void saveVictim(DisasterVictim victim);

    /**
     * Updates an existing disaster victim's data in the database.
     *
     * @param victim the DisasterVictim to update
     */
    void updateVictim(DisasterVictim victim);

    /**
     * Marks a disaster victim as soft-deleted in the database.
     *
     * @param id the database ID of the victim to soft delete
     */
    void softDeleteVictim(int id);

    /**
     * Permanently removes a disaster victim and all related data from the
     * database.
     *
     * @param id the database ID of the victim to hard delete
     */
    void hardDeleteVictim(int id);

    /**
     * Saves a new supply to the database and sets its generated ID.
     *
     * @param supply the Supply to save
     */
    void saveSupply(Supply supply);

    /**
     * Updates an existing supply's data in the database.
     *
     * @param supply the Supply to update
     */
    void updateSupply(Supply supply);

    /**
     * Saves a new medical record to the database and sets its generated ID.
     *
     * @param medicalRecord the MedicalRecord to save
     */
    void saveMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Saves a new inquiry to the database and sets its generated ID.
     *
     * @param inquiry the Inquiry to save
     */
    void saveInquiry(Inquiry inquiry);

    /**
     * Saves a new family relation to the database and sets its generated ID.
     *
     * @param familyRelation the FamilyRelation to save
     */
    void saveFamilyRelation(FamilyRelation familyRelation);

    /**
     * Saves a cultural requirement for a victim to the database.
     *
     * @param victimId the database ID of the victim
     * @param req the CulturalRequirement to save
     */
    void saveCulturalRequirement(int victimId, CulturalRequirement req);

    /**
     * Deletes a cultural requirement for a victim from the database.
     *
     * @param victimId the database ID of the victim
     * @param category the category of the requirement to delete
     */
    void deleteCulturalRequirement(int victimId, String category);

    /**
     * Saves a victim skill to the database and sets its generated ID.
     *
     * @param victimId the database ID of the victim
     * @param skill the VictimSkill to save
     */
    void saveVictimSkill(int victimId, VictimSkill skill);

    /**
     * Deletes a victim skill from the database.
     *
     * @param skillId the database ID of the skill to delete
     */
    void deleteVictimSkill(int skillId);
}
