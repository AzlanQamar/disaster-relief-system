package edu.ucalgary.oop;

import java.time.LocalDate;

/**
 * Represents a medical record associated with a disaster victim. Stores
 * information about the treatment location, treatment details, and the date the
 * treatment occurred.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class MedicalRecord {

    private int id;
    private DisasterVictim victim;
    private Location location;
    private String treatmentDetails;
    private LocalDate treatmentDate;

    /**
     * Constructs a MedicalRecord with the specified location, treatment
     * details, and treatment date.
     *
     * @param location the location where treatment occurred
     * @param treatmentDetails details describing the treatment
     * @param treatmentDate the date the treatment occurred
     * @throws IllegalArgumentException if location, treatmentDetails, or
     * treatmentDate is null, or if the treatmentDate is in the future
     */
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

    /**
     * Returns the database ID of this medical record.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database ID of this medical record.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the disaster victim associated with this record.
     *
     * @return the DisasterVictim
     */
    public DisasterVictim getVictim() {
        return victim;
    }

    /**
     * Sets the disaster victim associated with this record.
     *
     * @param victim the DisasterVictim to associate
     * @throws IllegalArgumentException if victim is null
     */
    public void setVictim(DisasterVictim victim) {
        if (victim == null) {
            throw new IllegalArgumentException("Victim cannot be null");
        }
        this.victim = victim;
    }

    /**
     * Returns the location where the treatment occurred.
     *
     * @return the Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location where the treatment occurred.
     *
     * @param location the Location to set
     * @throws IllegalArgumentException if location is null
     */
    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        this.location = location;
    }

    /**
     * Returns the treatment details for this record.
     *
     * @return the treatment details
     */
    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    /**
     * Sets the treatment details for this record.
     *
     * @param treatmentDetails the details to set
     * @throws IllegalArgumentException if treatmentDetails is null
     */
    public void setTreatmentDetails(String treatmentDetails) {
        if (treatmentDetails == null) {
            throw new IllegalArgumentException("Treatment details cannot be null");
        }
        this.treatmentDetails = treatmentDetails;
    }

    /**
     * Returns the date the treatment occurred.
     *
     * @return the treatment date
     */
    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    /**
     * Sets the date the treatment occurred.
     *
     * @param treatmentDate the date to set
     * @throws IllegalArgumentException if treatmentDate is null or in the
     * future
     */
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
