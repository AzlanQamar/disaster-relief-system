package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Unit tests for the MedicalRecord class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class MedicalRecordTest {

    private Location location;
    private MedicalRecord record;

    @Before
    public void setUp() {
        location = new Location("Hospital", "123 Medical Dr");
        record = new MedicalRecord(location, "Broken arm treated", LocalDate.of(2025, 1, 18));
    }

    @Test
    public void testConstructor_validInputs_setsTreatmentDetails() {
        assertEquals("Treatment details should be set correctly",
                "Broken arm treated", record.getTreatmentDetails());
    }

    @Test
    public void testConstructor_validInputs_setsTreatmentDate() {
        assertEquals("Treatment date should be set correctly",
                LocalDate.of(2025, 1, 18), record.getTreatmentDate());
    }

    @Test
    public void testConstructor_validInputs_setsLocation() {
        assertEquals("Location should be set correctly", location, record.getLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_futureTreatmentDate_throwsException() {
        new MedicalRecord(location, "Treatment", LocalDate.now().plusDays(1));
    }

    @Test
    public void testSetVictim_validVictim_setsVictim() {
        DisasterVictim victim = new DisasterVictim("John", LocalDate.now());
        record.setVictim(victim);
        assertEquals("Victim should be set correctly", victim, record.getVictim());
    }

    @Test
    public void testSetTreatmentDetails_validDetails_updatesDetails() {
        record.setTreatmentDetails("Updated treatment");
        assertEquals("Treatment details should be updated", "Updated treatment", record.getTreatmentDetails());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTreatmentDetails_nullDetails_throwsException() {
        record.setTreatmentDetails(null);
    }

    @Test
    public void testSetTreatmentDate_validDate_updatesDate() {
        LocalDate newDate = LocalDate.of(2025, 2, 1);
        record.setTreatmentDate(newDate);
        assertEquals("Treatment date should be updated", newDate, record.getTreatmentDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTreatmentDate_futureDate_throwsException() {
        record.setTreatmentDate(LocalDate.now().plusDays(1));
    }

    @Test
    public void testSetId_validId_setsId() {
        record.setId(7);
        assertEquals("ID should be 7", 7, record.getId());
    }
}
