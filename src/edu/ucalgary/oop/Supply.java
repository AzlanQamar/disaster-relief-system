package edu.ucalgary.oop;

import java.time.LocalDate;

/**
 * The Supply class represents a supply item that can be allocated to a disaster
 * victim. It contains attributes such as type, location, allocation date, and
 * description.
 */
public class Supply {

    private int id;
    private String type;
    private Location location;
    private DisasterVictim victim;
    private LocalDate allocationDate;
    private String description;

    public Supply(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public DisasterVictim getVictim() {
        return victim;
    }

    public void setVictim(DisasterVictim victim) {
        this.victim = victim;
    }

    public LocalDate getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(LocalDate allocationDate) {
        this.allocationDate = allocationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void allocateToVictim(DisasterVictim victim, LocalDate date) {
        this.victim = victim;
        this.allocationDate = date;
    }

    public boolean isAllocated() {
        return victim != null;
    }
}
