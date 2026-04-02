package edu.ucalgary.oop;

import java.util.ArrayList;

/**
 * The DatabaseInterface defines the contract for interacting with the database
 * to perform CRUD operations on disaster victims, locations, supplies, medical
 * records, inquiries, and family relations. Implementations of this interface
 * will handle the actual database interactions.
 */

public interface DatabaseInterface {

    ArrayList<DisasterVictim> loadAllVictims();

    ArrayList<Location> loadAllLocations();

    void saveVictim(DisasterVictim victim);

    void updateVictim(DisasterVictim victim);

    void softDeleteVictim(int id);

    void hardDeleteVictim(int id);

    void saveSupply(Supply supply);

    void updateSupply(Supply supply);

    void saveMedicalRecord(MedicalRecord medicalRecord);

    void saveInquiry(Inquiry inquiry);

    void saveFamilyRelation(FamilyRelation familyRelation);

}
