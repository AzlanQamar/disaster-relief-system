package edu.ucalgary.oop;

import java.time.LocalDate;

/**
 * The MedicalRecord class represents a medical record for a disaster victim. It
 * contains information about the location of treatment, details of the
 * treatment, and the date of treatment.
 */

public class MedicalRecord {

    private int id;
    private DisasterVictim victim;
    private Location location;
    private String treatmentDetails;
    private LocalDate treatmentDate;

    public MedicalRecord(Location location, String treatmentDetails, LocalDate treatmentDate) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        if (treatmentDetails == null) {
            throw new IllegalArgumentException("Treatment details cannot be null");
        }
        if (treatmentDate == null) {
            throw new IllegalArgumentException("Treatment date cannot be null");
        }
        if (treatmentDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Treatment date cannot be in the future");
        }

        this.location = location;
        this.treatmentDetails = treatmentDetails;
        this.treatmentDate = treatmentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DisasterVictim getVictim() {
        return victim;
    }

    public void setVictim(DisasterVictim victim) {
        if (victim == null) {
            throw new IllegalArgumentException("Victim cannot be null");
        }
        this.victim = victim;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        this.location = location;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        if (treatmentDetails == null) {
            throw new IllegalArgumentException("Treatment details cannot be null");
        }
        this.treatmentDetails = treatmentDetails;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        if (treatmentDate == null) {
            throw new IllegalArgumentException("Treatment date cannot be null");
        }
        if (treatmentDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Treatment date cannot be in the future");
        }
        this.treatmentDate = treatmentDate;
    }
}
