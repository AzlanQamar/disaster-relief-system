package edu.ucalgary.oop;

import java.util.ArrayList;

/**
 * The Location class represents a location where disaster victims can be housed
 * and supplies can be stored. It contains attributes such as name, address,
 * supplies, and occupants.
 */
public class Location {

    private int id;
    private String name;
    private String address;
    private ArrayList<Supply> supplies = new ArrayList<>();
    private ArrayList<DisasterVictim> occupants = new ArrayList<>();

    public Location(String name, String address) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        if (address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        this.address = address;
    }

    public ArrayList<DisasterVictim> getOccupants() {
        return occupants;
    }

    public void addOccupant(DisasterVictim victim) {
        if (victim == null) {
            throw new IllegalArgumentException("Victim cannot be null");
        }
        this.occupants.add(victim);
    }

    public void removeOccupant(DisasterVictim victim) {
        if (!occupants.remove(victim)) {
            throw new IllegalArgumentException("Victim not found in location");
        }
    }

    public ArrayList<Supply> getSupplies() {
        return supplies;
    }

    public void addSupply(Supply supply) {
        if (supply == null) {
            throw new IllegalArgumentException("Supply cannot be null");
        }
        this.supplies.add(supply);
    }

    public void removeSupply(Supply supply) {
        if (!supplies.remove(supply)) {
            throw new IllegalArgumentException("Supply not found in location");
        }
    }

}
