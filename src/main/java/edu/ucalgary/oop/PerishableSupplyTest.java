package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Unit tests for the PerishableSupply class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class PerishableSupplyTest {

    @Test
    public void testConstructor_validInputs_setsType() {
        PerishableSupply supply = new PerishableSupply("water", LocalDate.now().plusDays(30));
        assertEquals("Type should be water", "water", supply.getType());
    }

    @Test
    public void testConstructor_validInputs_setsExpiryDate() {
        LocalDate expiry = LocalDate.of(2027, 1, 1);
        PerishableSupply supply = new PerishableSupply("food", expiry);
        assertEquals("Expiry date should be set correctly", expiry, supply.getExpiryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullExpiryDate_throwsException() {
        new PerishableSupply("water", null);
    }

    @Test
    public void testIsExpired_futureDate_returnsFalse() {
        PerishableSupply supply = new PerishableSupply("water", LocalDate.now().plusDays(10));
        assertFalse("Supply with future expiry date should not be expired", supply.isExpired());
    }

    @Test
    public void testIsExpired_pastDate_returnsTrue() {
        PerishableSupply supply = new PerishableSupply("water", LocalDate.now().minusDays(1));
        assertTrue("Supply with past expiry date should be expired", supply.isExpired());
    }

    @Test
    public void testSetExpiryDate_validDate_updatesDate() {
        PerishableSupply supply = new PerishableSupply("water", LocalDate.now().plusDays(30));
        LocalDate newExpiry = LocalDate.of(2028, 6, 1);
        supply.setExpiryDate(newExpiry);
        assertEquals("Expiry date should be updated", newExpiry, supply.getExpiryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetExpiryDate_nullDate_throwsException() {
        PerishableSupply supply = new PerishableSupply("water", LocalDate.now().plusDays(30));
        supply.setExpiryDate(null);
    }

    @Test
    public void testPerishableSupply_extendsSupply_isAllocatedWorks() {
        PerishableSupply supply = new PerishableSupply("water", LocalDate.now().plusDays(30));
        assertFalse("New perishable supply should not be allocated", supply.isAllocated());
    }
}
