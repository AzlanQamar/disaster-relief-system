package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Unit tests for the Location class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class LocationTest {

    private Location location;

    @Before
    public void setUp() {
        location = new Location("TELUS Convention Centre", "136 8 Ave SE");
    }

    @Test
    public void testConstructor_validInputs_setsName() {
        assertEquals("Name should be TELUS Convention Centre", "TELUS Convention Centre", location.getName());
    }

    @Test
    public void testConstructor_validInputs_setsAddress() {
        assertEquals("Address should be set correctly", "136 8 Ave SE", location.getAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullName_throwsException() {
        new Location(null, "123 Main St");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptyName_throwsException() {
        new Location("   ", "123 Main St");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullAddress_throwsException() {
        new Location("Shelter", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptyAddress_throwsException() {
        new Location("Shelter", "   ");
    }

    @Test
    public void testAddOccupant_validVictim_addsToList() {
        DisasterVictim victim = new DisasterVictim("John", LocalDate.now());
        location.addOccupant(victim);
        assertEquals("Occupants list should have 1 entry", 1, location.getOccupants().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOccupant_nullVictim_throwsException() {
        location.addOccupant(null);
    }

    @Test
    public void testRemoveOccupant_existingVictim_removesFromList() {
        DisasterVictim victim = new DisasterVictim("John", LocalDate.now());
        location.addOccupant(victim);
        location.removeOccupant(victim);
        assertEquals("Occupants list should be empty after removal", 0, location.getOccupants().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveOccupant_nonExistingVictim_throwsException() {
        location.removeOccupant(new DisasterVictim("John", LocalDate.now()));
    }

    @Test
    public void testAddSupply_validSupply_addsToList() {
        Supply supply = new Supply("blanket");
        location.addSupply(supply);
        assertEquals("Supplies list should have 1 entry", 1, location.getSupplies().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSupply_nullSupply_throwsException() {
        location.addSupply(null);
    }

    @Test
    public void testRemoveSupply_existingSupply_removesFromList() {
        Supply supply = new Supply("blanket");
        location.addSupply(supply);
        location.removeSupply(supply);
        assertEquals("Supplies list should be empty after removal", 0, location.getSupplies().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSupply_nonExistingSupply_throwsException() {
        location.removeSupply(new Supply("blanket"));
    }

    @Test
    public void testSetName_validName_updatesName() {
        location.setName("University of Calgary");
        assertEquals("Name should be updated", "University of Calgary", location.getName());
    }

    @Test
    public void testSetId_validId_setsId() {
        location.setId(3);
        assertEquals("ID should be 3", 3, location.getId());
    }
}
