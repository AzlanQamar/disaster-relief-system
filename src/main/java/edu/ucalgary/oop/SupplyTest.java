package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Unit tests for the Supply class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class SupplyTest {

    private Supply supply;

    @Before
    public void setUp() {
        supply = new Supply("blanket");
    }

    @Test
    public void testConstructor_validType_setsType() {
        assertEquals("Supply type should be blanket", "blanket", supply.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullType_throwsException() {
        new Supply(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptyType_throwsException() {
        new Supply("   ");
    }

    @Test
    public void testIsAllocated_newSupply_returnsFalse() {
        assertFalse("New supply should not be allocated", supply.isAllocated());
    }

    @Test
    public void testAllocateTo_validVictim_setsVictim() {
        DisasterVictim victim = new DisasterVictim("John", LocalDate.now());
        supply.allocateToVictim(victim, LocalDate.now());
        assertTrue("Supply should be allocated after allocateTo()", supply.isAllocated());
    }

    @Test
    public void testAllocateTo_validVictim_setsAllocationDate() {
        DisasterVictim victim = new DisasterVictim("John", LocalDate.now());
        LocalDate date = LocalDate.of(2025, 1, 15);
        supply.allocateToVictim(victim, date);
        assertEquals("Allocation date should be set correctly", date, supply.getAllocationDate());
    }

    @Test
    public void testSetType_validType_updatesType() {
        supply.setType("water");
        assertEquals("Supply type should be updated to water", "water", supply.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetType_nullType_throwsException() {
        supply.setType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetType_emptyType_throwsException() {
        supply.setType("");
    }

    @Test
    public void testSetId_validId_setsId() {
        supply.setId(5);
        assertEquals("Supply ID should be 5", 5, supply.getId());
    }

    @Test
    public void testSetLocation_validLocation_setsLocation() {
        Location loc = new Location("Warehouse", "456 Storage Rd");
        supply.setLocation(loc);
        assertEquals("Location should be set correctly", loc, supply.getLocation());
    }

    @Test
    public void testSetDescription_validDescription_setsDescription() {
        supply.setDescription("Winter blanket");
        assertEquals("Description should be set correctly", "Winter blanket", supply.getDescription());
    }
}
