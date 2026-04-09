package edu.ucalgary.oop;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implements DatabaseInterface using JDBC to interact with the PostgreSQL
 * database. Handles all CRUD operations for disaster victims, locations,
 * supplies, medical records, inquiries, family relations, cultural
 * requirements, and skills.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class DatabaseQueryHandler implements DatabaseInterface {

    private Connection connection;

    /**
     * Constructs a DatabaseQueryHandler using the provided database connection.
     *
     * @param connection the active JDBC connection to use for queries
     */
    public DatabaseQueryHandler(Connection connection) {
        this.connection = connection;
    }

    /**
     * Loads all non-deleted disaster victims from the database, joining Person
     * and DisasterVictim tables.
     *
     * @return a list of all active DisasterVictim objects
     */
    @Override
    public ArrayList<DisasterVictim> loadAllVictims(ArrayList<Location> locations) {
        ArrayList<DisasterVictim> victims = new ArrayList<>();
        if (locations == null) {
            locations = new ArrayList<>();
        }
        // Build a map for quick location lookup
        HashMap<Integer, Location> locationMap = new HashMap<>();
        for (Location loc : locations) {
            locationMap.put(loc.getId(), loc);
        }

        String query = "SELECT * FROM DisasterVictim JOIN Person ON person_id = Person.id WHERE is_soft_deleted = FALSE";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                LocalDate entryDate = rs.getDate("entry_date").toLocalDate();
                DisasterVictim victim = new DisasterVictim(firstName, entryDate);
                victim.setId(rs.getInt("person_id"));
                victim.setLastName(rs.getString("last_name"));
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
                victim.setComments(rs.getString("comments"));

                // Set location using the passed-in locations list
                int locationId = rs.getInt("location_id");
                if (!rs.wasNull() && locationMap.containsKey(locationId)) {
                    victim.setLocation(locationMap.get(locationId));
                }

                victims.add(victim);
            }
        } catch (SQLException e) {
            System.err.println("Failed to load victims: " + e.getMessage());
        }

        // Load related data for each victim
        loadMedicalRecordsForVictims(victims, locationMap);
        loadCulturalRequirementsForVictims(victims);
        loadFamilyConnectionsForVictims(victims);
        loadSkillsForVictims(victims);

        return victims;
    }

    /**
     * Loads all locations from the database.
     *
     * @return a list of all Location objects
     */
    @Override
    public ArrayList<Location> loadAllLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        String query = "SELECT * FROM Location";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Location location = new Location(rs.getString("name"), rs.getString("address"));
                location.setId(rs.getInt("id"));
                locations.add(location);
            }
        } catch (SQLException e) {
            System.err.println("Failed to load locations: " + e.getMessage());
        }
        // Load supplies for each location
        String supplyQuery = "SELECT * FROM Supply WHERE location_id IS NOT NULL";
        try (PreparedStatement stmt = connection.prepareStatement(supplyQuery); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int locId = rs.getInt("location_id");
                for (Location loc : locations) {
                    if (loc.getId() == locId) {
                        Supply supply;
                        if (rs.getDate("expiry_date") != null) {
                            supply = new PerishableSupply(rs.getString("supply_type"),
                                    rs.getDate("expiry_date").toLocalDate());
                        } else {
                            supply = new Supply(rs.getString("supply_type"));
                        }
                        supply.setId(rs.getInt("id"));
                        supply.setLocation(loc);
                        loc.addSupply(supply);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to load supplies: " + e.getMessage());
        }
        return locations;
    }

    /**
     * Saves a new disaster victim to the Person table and sets the generated
     * ID.
     *
     * @param victim the DisasterVictim to save
     */
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

    /**
     * Updates an existing disaster victim's name and comments in the Person
     * table.
     *
     * @param victim the DisasterVictim to update
     */
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

    /**
     * Sets is_soft_deleted to TRUE for the specified victim in the database.
     *
     * @param id the database ID of the victim to soft delete
     */
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

    /**
     * Permanently deletes a victim and all related data from the database via
     * cascade delete on the Person table.
     *
     * @param id the database ID of the victim to hard delete
     */
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

    /**
     * Loads all inquiries from the database and associates them with their
     * subject victims.
     *
     * @param victims the list of loaded victims to match against subject IDs
     * @return a list of all Inquiry objects loaded from the database
     */
    public ArrayList<Inquiry> loadAllInquiries(ArrayList<DisasterVictim> victims) {
        ArrayList<Inquiry> inquiries = new ArrayList<>();
        String query = "SELECT i.*, p.first_name, p.last_name FROM Inquiry i JOIN Person p ON i.inquirer_id = p.id";
        HashMap<Integer, DisasterVictim> victimMap = new HashMap<>();
        for (DisasterVictim v : victims) {
            victimMap.put(v.getId(), v);
        }
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int subjectId = rs.getInt("subject_person_id");
                DisasterVictim subject = victimMap.get(subjectId);
                if (subject == null) {
                    continue;
                }
                String inquirerFirstName = rs.getString("first_name");
                String inquirerLastName = rs.getString("last_name");
                Inquirer inquirer = new Inquirer(inquirerFirstName, inquirerLastName, "", "");
                inquirer.setId(rs.getInt("inquirer_id"));
                Inquiry inquiry = new Inquiry(inquirer, subject, rs.getString("details"));
                inquiry.setId(rs.getInt("id"));
                inquiries.add(inquiry);
            }
        } catch (SQLException e) {
            System.err.println("Failed to load inquiries: " + e.getMessage());
        }
        return inquiries;
    }

    /**
     * Saves a new supply to the database and sets the generated ID. Handles
     * perishable supplies by setting the expiry date.
     *
     * @param supply the Supply to save
     */
    @Override
    public void saveSupply(Supply supply) {
        String query = "INSERT INTO Supply(supply_type, location_id, victim_id, expiry_date, allocation_date, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, supply.getType());
            stmt.setObject(2, supply.getLocation() != null ? supply.getLocation().getId() : null);
            stmt.setObject(3, supply.getVictim() != null ? supply.getVictim().getId() : null);
            stmt.setObject(4, supply instanceof PerishableSupply ? ((PerishableSupply) supply).getExpiryDate() : null);
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

    /**
     * Updates an existing supply's data in the database.
     *
     * @param supply the Supply to update
     */
    @Override
    public void updateSupply(Supply supply) {
        String query = "UPDATE Supply SET supply_type = ?, location_id = ?, victim_id = ?, expiry_date = ?, allocation_date = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, supply.getType());
            stmt.setObject(2, supply.getLocation() != null ? supply.getLocation().getId() : null);
            stmt.setObject(3, supply.getVictim() != null ? supply.getVictim().getId() : null);
            stmt.setObject(4, supply instanceof PerishableSupply ? ((PerishableSupply) supply).getExpiryDate() : null);
            stmt.setObject(5, supply.getAllocationDate());
            stmt.setString(6, supply.getDescription());
            stmt.setInt(7, supply.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to update supply: " + e.getMessage());
        }
    }

    /**
     * Saves a new medical record to the database and sets the generated ID.
     *
     * @param medicalRecord the MedicalRecord to save
     */
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

    /**
     * Saves a new inquiry to the database and sets the generated ID.
     *
     * @param inquiry the Inquiry to save
     */
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

    /**
     * Saves a new family relation to the FamilyRelationship table and sets the
     * generated ID.
     *
     * @param familyRelation the FamilyRelation to save
     */
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

    /**
     * Saves a cultural requirement for a victim to the database and sets the
     * generated ID.
     *
     * @param victimId the database ID of the victim
     * @param req the CulturalRequirement to save
     */
    @Override
    public void saveCulturalRequirement(int victimId, CulturalRequirement req) {
        String query = "INSERT INTO CulturalRequirement(victim_id, requirement_category, requirement_option) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, victimId);
            stmt.setString(2, req.getRequirementCategory());
            stmt.setString(3, req.getRequirementOption());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                req.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Failed to save cultural requirement: " + e.getMessage());
        }
    }

    /**
     * Deletes a cultural requirement for a victim from the database.
     *
     * @param victimId the database ID of the victim
     * @param category the category of the requirement to delete
     */
    @Override
    public void deleteCulturalRequirement(int victimId, String category) {
        String query = "DELETE FROM CulturalRequirement WHERE victim_id = ? AND requirement_category = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, victimId);
            stmt.setString(2, category);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to delete cultural requirement: " + e.getMessage());
        }
    }

    /**
     * Saves a victim skill to the VictimSkill table, storing type-specific
     * details for medical, language, and trade skills.
     *
     * @param victimId the database ID of the victim
     * @param skill the VictimSkill to save
     */
    @Override
    public void saveVictimSkill(int victimId, VictimSkill skill) {
        String query = "INSERT INTO VictimSkill(victim_id, skill_id, proficiency_level, details, language_capabilities, certification_expiry) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, victimId);
            stmt.setInt(2, skill.getSkill().getId());
            stmt.setString(3, skill.getProficiencyLevel());
            if (skill instanceof MedicalSkill) {
                MedicalSkill ms = (MedicalSkill) skill;
                stmt.setString(4, ms.getCertificationType());
                stmt.setObject(5, null);
                stmt.setObject(6, ms.getCertificationExpiry());
            } else if (skill instanceof LanguageSkill) {
                LanguageSkill ls = (LanguageSkill) skill;
                stmt.setString(4, ls.getLanguageName());
                stmt.setString(5, (ls.hasReadWrite() ? "read/write " : "") + (ls.hasSpeakListen() ? "speak/listen" : ""));
                stmt.setObject(6, null);
            } else if (skill instanceof TradeSkill) {
                stmt.setString(4, ((TradeSkill) skill).getTradeType());
                stmt.setObject(5, null);
                stmt.setObject(6, null);
            } else {
                stmt.setObject(4, null);
                stmt.setObject(5, null);
                stmt.setObject(6, null);
            }
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                skill.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Failed to save victim skill: " + e.getMessage());
        }
    }

    /**
     * Deletes a victim skill from the VictimSkill table by its ID.
     *
     * @param skillId the database ID of the skill to delete
     */
    @Override
    public void deleteVictimSkill(int skillId) {
        String query = "DELETE FROM VictimSkill WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, skillId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to delete victim skill: " + e.getMessage());
        }
    }

    /**
     * Loads medical records from the database and assigns them to the
     * appropriate victims.
     *
     * @param victims the list of victims to populate
     * @param locationMap map of location ID to Location
     */
    private void loadMedicalRecordsForVictims(ArrayList<DisasterVictim> victims,
            HashMap<Integer, Location> locationMap) {
        HashMap<Integer, DisasterVictim> victimMap = new HashMap<>();
        for (DisasterVictim v : victims) {
            victimMap.put(v.getId(), v);
        }
        String query = "SELECT * FROM MedicalRecord";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DisasterVictim victim = victimMap.get(rs.getInt("victim_id"));
                if (victim == null) {
                    continue;
                }
                Location loc = locationMap.get(rs.getInt("location_id"));
                MedicalRecord record = new MedicalRecord(loc,
                        rs.getString("treatment_details"),
                        rs.getDate("treatment_date").toLocalDate());
                record.setId(rs.getInt("id"));
                record.setVictim(victim);
                victim.addMedicalRecord(record);
            }
        } catch (SQLException e) {
            System.err.println("Failed to load medical records: " + e.getMessage());
        }
    }

    /**
     * Loads cultural requirements and assigns them to the appropriate victims.
     *
     * @param victims the list of victims to populate
     */
    private void loadCulturalRequirementsForVictims(ArrayList<DisasterVictim> victims) {
        HashMap<Integer, DisasterVictim> victimMap = new HashMap<>();
        for (DisasterVictim v : victims) {
            victimMap.put(v.getId(), v);
        }
        String query = "SELECT * FROM CulturalRequirement";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DisasterVictim victim = victimMap.get(rs.getInt("victim_id"));
                if (victim == null) {
                    continue;
                }
                CulturalRequirement req = new CulturalRequirement(
                        rs.getString("requirement_category"),
                        rs.getString("requirement_option"));
                req.setId(rs.getInt("id"));
                try {
                    victim.addCulturalRequirement(req);
                } catch (IllegalArgumentException ignored) {
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to load cultural requirements: " + e.getMessage());
        }
    }

    /**
     * Loads family connections and assigns them to the appropriate victims.
     *
     * @param victims the list of victims to populate
     */
    private void loadFamilyConnectionsForVictims(ArrayList<DisasterVictim> victims) {
        HashMap<Integer, DisasterVictim> victimMap = new HashMap<>();
        for (DisasterVictim v : victims) {
            victimMap.put(v.getId(), v);
        }
        String query = "SELECT * FROM FamilyRelationship";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DisasterVictim p1 = victimMap.get(rs.getInt("person_one_id"));
                DisasterVictim p2 = victimMap.get(rs.getInt("person_two_id"));
                if (p1 == null || p2 == null) {
                    continue;
                }
                FamilyRelation relation = new FamilyRelation(p1, p2,
                        rs.getString("relationship_type"));
                relation.setId(rs.getInt("id"));
                p1.addFamilyConnection(relation);
                p2.addFamilyConnection(relation);
            }
        } catch (SQLException e) {
            System.err.println("Failed to load family connections: " + e.getMessage());
        }
    }

    /**
     * Loads victim skills from the database and assigns them to the appropriate
     * victims.
     *
     * @param victims the list of victims to populate
     */
    private void loadSkillsForVictims(ArrayList<DisasterVictim> victims) {
        HashMap<Integer, DisasterVictim> victimMap = new HashMap<>();
        for (DisasterVictim v : victims) {
            victimMap.put(v.getId(), v);
        }
        String query = "SELECT vs.*, s.skill_name, s.category FROM VictimSkill vs "
                + "JOIN Skill s ON vs.skill_id = s.id";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DisasterVictim victim = victimMap.get(rs.getInt("victim_id"));
                if (victim == null) {
                    continue;
                }
                Skill skill = new Skill(rs.getString("skill_name"), rs.getString("category"));
                skill.setId(rs.getInt("skill_id"));
                String proficiency = rs.getString("proficiency_level");
                String category = rs.getString("category");
                VictimSkill vs;
                try {
                    if (category.equalsIgnoreCase("medical")) {
                        String certType = rs.getString("details");
                        Date expiryDate = rs.getDate("certification_expiry");
                        LocalDate expiry = expiryDate != null ? expiryDate.toLocalDate() : LocalDate.now().plusYears(1);
                        vs = new MedicalSkill(skill, proficiency,
                                certType != null ? certType : "first-aid", expiry);
                    } else if (category.equalsIgnoreCase("language")) {
                        String langName = rs.getString("details");
                        String caps = rs.getString("language_capabilities");
                        boolean readWrite = caps != null && caps.contains("read/write");
                        boolean speakListen = caps != null && caps.contains("speak/listen");
                        vs = new LanguageSkill(skill, proficiency,
                                langName != null ? langName : skill.getSkillName(), readWrite, speakListen);
                    } else {
                        String tradeType = rs.getString("details");
                        vs = new TradeSkill(skill, proficiency,
                                tradeType != null ? tradeType : "carpentry");
                    }
                    vs.setId(rs.getInt("id"));
                    victim.addVictimSkill(vs);
                } catch (IllegalArgumentException e) {
                    System.err.println("Failed to load skill for victim " + victim.getId() + ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to load victim skills: " + e.getMessage());
        }
    }
}
