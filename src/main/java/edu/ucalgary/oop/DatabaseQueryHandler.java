package edu.ucalgary.oop;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The DatabaseQueryHandler class implements the DatabaseInterface and provides
 * methods to interact with the database for performing CRUD operations on
 * disaster victims, locations, supplies, medical records, inquiries, and family
 * relations. This class uses JDBC to connect to a relational database and
 * execute SQL queries.
 */
public class DatabaseQueryHandler implements DatabaseInterface {

    private Connection connection;

    public DatabaseQueryHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ArrayList<DisasterVictim> loadAllVictims() {
        ArrayList<DisasterVictim> victims = new ArrayList<>();
        String query = "SELECT * FROM DisasterVictim JOIN Person ON person_id = Person.id WHERE is_soft_deleted = FALSE";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // read columns from current row
                String firstName = rs.getString("first_name");
                LocalDate entryDate = rs.getDate("entry_date").toLocalDate();

                // create the object
                DisasterVictim victim = new DisasterVictim(firstName, entryDate);

                // set the rest of the fields
                victim.setId(rs.getInt("person_id"));
                victim.setLastName(rs.getString("last_name"));
                // gender
                if (rs.getString("gender") != null) {
                    victim.setGender(rs.getString("gender"));
                }

                try {
                    if (rs.getObject("approximate_age") != null) {
                        victim.setApproximateAge(rs.getInt("approximate_age"));
                    }
                    if (rs.getDate("date_of_birth") != null) {
                        victim.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                    }
                } catch (InvalidAgeException e) {
                    System.err.println("Age conflict loading victim: " + e.getMessage());
                }

                // comments
                victim.setComments(rs.getString("comments"));
                // add to list
                victims.add(victim);

            }
        } catch (SQLException e) {
            System.err.println("Failed to load victims: " + e.getMessage());
        }
        return victims;
    }

    @Override
    public ArrayList<Location> loadAllLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        String query = "SELECT * FROM Location";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // read columns from current row
                String name = rs.getString("name");
                String address = rs.getString("address");

                // create the object
                Location location = new Location(name, address);

                // set the rest of the fields
                location.setId(rs.getInt("id"));

                // add to list
                locations.add(location);
            }
        } catch (SQLException e) {
            System.err.println("Failed to load locations: " + e.getMessage());
        }
        return locations;
    }

    @Override
    public void saveVictim(DisasterVictim victim) {
        String personQuery = "INSERT INTO Person (first_name, last_name, comments) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(personQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, victim.getFirstName());
            stmt.setString(2, victim.getLastName());
            stmt.setString(3, victim.getComments());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                victim.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Failed to save victim: " + e.getMessage());
        }
    }

    @Override
    public void updateVictim(DisasterVictim victim) {
        String query = "UPDATE Person SET first_name = ?, last_name = ?, comments = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, victim.getFirstName());
            stmt.setString(2, victim.getLastName());
            stmt.setString(3, victim.getComments());
            stmt.setInt(4, victim.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to update victim: " + e.getMessage());
        }
    }

    @Override
    public void softDeleteVictim(int id) {
        String query = "UPDATE DisasterVictim SET is_soft_deleted = TRUE WHERE person_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to soft delete victim: " + e.getMessage());
        }
    }

    @Override
    public void hardDeleteVictim(int id) {
        String query = "DELETE FROM Person WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to hard delete victim: " + e.getMessage());
        }
    }

    @Override
    public void saveSupply(Supply supply) {
        String query = "INSERT INTO Supply(supply_type, location_id, victim_id, expiry_date, allocation_date, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, supply.getType());
            stmt.setObject(2, supply.getLocation() != null ? supply.getLocation().getId() : null);
            stmt.setObject(3, supply.getVictim() != null ? supply.getVictim().getId() : null);
            if (supply instanceof PerishableSupply) {
                stmt.setObject(4, ((PerishableSupply) supply).getExpiryDate());
            } else {
                stmt.setObject(4, null);
            }
            stmt.setObject(5, supply.getAllocationDate());
            stmt.setString(6, supply.getDescription());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                supply.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Failed to save supply: " + e.getMessage());
        }
    }

    @Override
    public void updateSupply(Supply supply) {
        String query = "UPDATE Supply SET supply_type = ?, location_id = ?, victim_id = ?, expiry_date = ?, allocation_date = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, supply.getType());
            stmt.setObject(2, supply.getLocation() != null ? supply.getLocation().getId() : null);
            stmt.setObject(3, supply.getVictim() != null ? supply.getVictim().getId() : null);
            if (supply instanceof PerishableSupply) {
                stmt.setObject(4, ((PerishableSupply) supply).getExpiryDate());
            } else {
                stmt.setObject(4, null);
            }
            stmt.setObject(5, supply.getAllocationDate());
            stmt.setString(6, supply.getDescription());
            stmt.setInt(7, supply.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to update supply: " + e.getMessage());
        }
    }

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord) {
        String query = "INSERT INTO MedicalRecord(victim_id, location_id, treatment_details, treatment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, medicalRecord.getVictim().getId());
            stmt.setObject(2, medicalRecord.getLocation() != null ? medicalRecord.getLocation().getId() : null);
            stmt.setString(3, medicalRecord.getTreatmentDetails());
            stmt.setDate(4, Date.valueOf(medicalRecord.getTreatmentDate()));
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                medicalRecord.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Failed to save medical record: " + e.getMessage());
        }
    }

    @Override
    public void saveInquiry(Inquiry inquiry) {
        String query = "INSERT INTO Inquiry(inquirer_id, subject_person_id, details, inquiry_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, inquiry.getInquirer().getId());
            stmt.setInt(2, inquiry.getSubjectPerson().getId());
            stmt.setString(3, inquiry.getDetails());
            stmt.setTimestamp(4, Timestamp.valueOf(inquiry.getInquiryDate()));
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                inquiry.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Failed to save inquiry: " + e.getMessage());
        }
    }

    @Override
    public void saveFamilyRelation(FamilyRelation familyRelation) {
        String query = "INSERT INTO FamilyRelationship(person_one_id, person_two_id, relationship_type) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, familyRelation.getPersonOne().getId());
            stmt.setInt(2, familyRelation.getPersonTwo().getId());
            stmt.setString(3, familyRelation.getRelationshipTo());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                familyRelation.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Failed to save family relation: " + e.getMessage());
        }
    }
}
