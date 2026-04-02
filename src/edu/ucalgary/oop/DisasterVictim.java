package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The DisasterVictim class represents an individual who has been affected by a
 * disaster. It may contain additional attributes and methods specific to
 * disaster victims.
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

    public DisasterVictim(String firstName, LocalDate entryDate) {
        super(firstName, null);
        if (entryDate == null) {
            throw new IllegalArgumentException("Entry date cannot be null");
        }
        this.ENTRY_DATE = entryDate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) throws InvalidAgeException {
        if (approximateAge != null) {
            throw new InvalidAgeException("Cannot set both approximate age and date of birth");
        }

        if (dateOfBirth != null && dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        this.dateOfBirth = dateOfBirth;
    }

    public Integer getApproximateAge() {
        return approximateAge;
    }

    public void setApproximateAge(int approximateAge) throws InvalidAgeException {
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

    public LocalDate getEntryDate() {
        return ENTRY_DATE;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }

        if (gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be empty");
        }
        this.gender = gender;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isSoftDeleted() {
        return isSoftDeleted;
    }

    public void softDelete() {
        this.isSoftDeleted = true;
    }

    public ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord == null) {
            throw new IllegalArgumentException("Medical record cannot be null");
        }
        this.medicalRecords.add(medicalRecord);
    }

    public ArrayList<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    public void addPersonalBelonging(Supply supply) {
        if (supply == null) {
            throw new IllegalArgumentException("Supply cannot be null");
        }
        this.personalBelongings.add(supply);
    }

    public void removePersonalBelonging(Supply supply) {
        if (!personalBelongings.remove(supply)) {
            throw new IllegalArgumentException("Supply not found in personal belongings");
        }
    }

    public ArrayList<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    }

    public void addFamilyConnection(FamilyRelation familyRelation) {
        if (familyRelation == null) {
            throw new IllegalArgumentException("Family relation cannot be null");
        }
        this.familyConnections.add(familyRelation);
    }

    public void removeFamilyConnection(FamilyRelation familyRelation) {
        if (!familyConnections.remove(familyRelation)) {
            throw new IllegalArgumentException("Family relation not found in family connections");
        }
    }

    public ArrayList<CulturalRequirement> getCulturalRequirements() {
        return culturalRequirements;
    }

    public void addCulturalRequirement(CulturalRequirement culturalRequirement) {
        if (culturalRequirement == null) {
            throw new IllegalArgumentException("Cultural requirement cannot be null");
        }
        this.culturalRequirements.add(culturalRequirement);

    }

    public ArrayList<VictimSkill> getVictimSkills() {
        return victimSkills;
    }

    public void addVictimSkill(VictimSkill victimSkill) {
        if (victimSkill == null) {
            throw new IllegalArgumentException("Victim skill cannot be null");
        }
        this.victimSkills.add(victimSkill);
    }

}
