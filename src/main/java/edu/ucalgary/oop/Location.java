package edu.ucalgary.oop;

import java.util.ArrayList;

/**
 * Represents a physical location where disaster victims can be housed and
 * supplies can be stored. Tracks both occupants and available supplies.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class Location {

    private int id;
    private String name;
    private String address;
    private ArrayList<Supply> supplies = new ArrayList<>();
    private ArrayList<DisasterVictim> occupants = new ArrayList<>();

    /**
     * Constructs a Location with the given name and address.
     *
     * @param name the name of the location
     * @param address the street address of the location
     * @throws IllegalArgumentException if name or address is null or empty
     */
    public Location(String name, String address) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        if (address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        this.name = name;
        this.address = address;
    }

    /**
     * Returns the database ID of this location.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database ID of this location.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of this location.
     *
     * @return the location name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this location.
     *
     * @param name the new name
     * @throws IllegalArgumentException if name is null or empty
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    /**
     * Returns the street address of this location.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the street address of this location.
     *
     * @param address the new address
     * @throws IllegalArgumentException if address is null or empty
     */
    public void setAddress(String address) throws IllegalArgumentException {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        if (address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        this.address = address;
    }

    /**
     * Returns all disaster victims currently housed at this location.
     *
     * @return the list of DisasterVictim occupants
     */
    public ArrayList<DisasterVictim> getOccupants() {
        return occupants;
    }

    /**
     * Adds a disaster victim to this location's occupants.
     *
     * @param victim the DisasterVictim to add
     * @throws IllegalArgumentException if victim is null
     */
    public void addOccupant(DisasterVictim victim) throws IllegalArgumentException {
        if (victim == null) {
            throw new IllegalArgumentException("Victim cannot be null");
        }
        this.occupants.add(victim);
    }

    /**
     * Removes a disaster victim from this location's occupants.
     *
     * @param victim the DisasterVictim to remove
     * @throws IllegalArgumentException if the victim is not found at this
     * location
     */
    public void removeOccupant(DisasterVictim victim) throws IllegalArgumentException {
        if (!occupants.remove(victim)) {
            throw new IllegalArgumentException("Victim not found in location");
        }
    }

    /**
     * Returns all supplies stored at this location.
     *
     * @return the list of Supply objects
     */
    public ArrayList<Supply> getSupplies() {
        return supplies;
    }

    /**
     * Adds a supply to this location's inventory.
     *
     * @param supply the Supply to add
     * @throws IllegalArgumentException if supply is null
     */
    public void addSupply(Supply supply) throws IllegalArgumentException {
        if (supply == null) {
            throw new IllegalArgumentException("Supply cannot be null");
        }
        this.supplies.add(supply);
    }

    /**
     * Removes a supply from this location's inventory.
     *
     * @param supply the Supply to remove
     * @throws IllegalArgumentException if the supply is not found at this
     * location
     */
    public void removeSupply(Supply supply) throws IllegalArgumentException {
        if (!supplies.remove(supply)) {
            throw new IllegalArgumentException("Supply not found in location");
        }
    }
}
