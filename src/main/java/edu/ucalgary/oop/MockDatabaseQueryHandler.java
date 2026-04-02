package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A mock implementation of DatabaseInterface for use in unit tests. Returns
 * hardcoded data instead of connecting to a real database.
 */
public class MockDatabaseQueryHandler implements DatabaseInterface {

    private ArrayList<DisasterVictim> victims;
    private ArrayList<Location> locations;

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

    @Override
    public ArrayList<DisasterVictim> loadAllVictims() {
        return victims;
    }

    @Override
    public ArrayList<Location> loadAllLocations() {
        return locations;
    }

    @Override
    public void saveVictim(DisasterVictim victim) {
    }

    @Override
    public void updateVictim(DisasterVictim victim) {
    }

    @Override
    public void softDeleteVictim(int id) {
    }

    @Override
    public void hardDeleteVictim(int id) {
    }

    @Override
    public void saveSupply(Supply supply) {
    }

    @Override
    public void updateSupply(Supply supply) {
    }

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord) {
    }

    @Override
    public void saveInquiry(Inquiry inquiry) {
    }

    @Override
    public void saveFamilyRelation(FamilyRelation familyRelation) {
    }
}
