package edu.ucalgary.oop;

import java.time.LocalDate;

/**
 * Represents a supply item that can be stored at a location and allocated to a
 * disaster victim. Tracks details such as type, associated location, assigned
 * victim, allocation date, and description.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class Supply {

    private int id;
    private String type;
    private Location location;
    private DisasterVictim victim;
    private LocalDate allocationDate;
    private String description;

    /**
     * Constructs a Supply with the specified type.
     *
     * @param type the type of supply
     * @throws IllegalArgumentException if type is null or empty
     */
    public Supply(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        this.type = type;
    }

    /**
     * Returns the database ID of this supply.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database ID of this supply.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the type of this supply.
     *
     * @return the supply type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of this supply.
     *
     * @param type the new type
     * @throws IllegalArgumentException if type is null or empty
     */
    public void setType(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        this.type = type;
    }

    /**
     * Returns the location where this supply is stored.
     *
     * @return the Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location where this supply is stored.
     *
     * @param location the Location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the disaster victim to whom this supply is allocated.
     *
     * @return the DisasterVictim, or null if not allocated
     */
    public DisasterVictim getVictim() {
        return victim;
    }

    /**
     * Sets the disaster victim to whom this supply is allocated.
     *
     * @param victim the DisasterVictim to set
     */
    public void setVictim(DisasterVictim victim) {
        this.victim = victim;
    }

    /**
     * Returns the date this supply was allocated.
     *
     * @return the allocation date
     */
    public LocalDate getAllocationDate() {
        return allocationDate;
    }

    /**
     * Sets the allocation date for this supply.
     *
     * @param allocationDate the date to set
     */
    public void setAllocationDate(LocalDate allocationDate) {
        this.allocationDate = allocationDate;
    }

    /**
     * Returns the description of this supply.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this supply.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Allocates this supply to a disaster victim on a specific date.
     *
     * @param victim the DisasterVictim receiving the supply
     * @param date the date of allocation
     */
    public void allocateToVictim(DisasterVictim victim, LocalDate date) {
        this.victim = victim;
        this.allocationDate = date;
    }

    /**
     * Determines whether this supply has been allocated to a victim.
     *
     * @return true if allocated, false otherwise
     */
    public boolean isAllocated() {
        return victim != null;
    }
}
