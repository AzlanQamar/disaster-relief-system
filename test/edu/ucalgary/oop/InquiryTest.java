package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Unit tests for the Inquiry class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class InquiryTest {

    private Inquirer inquirer;
    private DisasterVictim subject;
    private Inquiry inquiry;

    @Before
    public void setUp() {
        inquirer = new Inquirer("Joseph", "Bouillon", "Looking for family", "555-1234");
        subject = new DisasterVictim("Freda", LocalDate.now());
        inquiry = new Inquiry(inquirer, subject, "Seeking information about Freda");
    }

    @Test
    public void testConstructor_validInputs_setsInquirer() {
        assertEquals("Inquirer should be set correctly", inquirer, inquiry.getInquirer());
    }

    @Test
    public void testConstructor_validInputs_setsSubjectPerson() {
        assertEquals("Subject person should be set correctly", subject, inquiry.getSubjectPerson());
    }

    @Test
    public void testConstructor_validInputs_setsDetails() {
        assertEquals("Details should be set correctly",
                "Seeking information about Freda", inquiry.getDetails());
    }

    @Test
    public void testConstructor_validInputs_setsInquiryDateToNow() {
        assertNotNull("Inquiry date should be set automatically", inquiry.getInquiryDate());
        assertTrue("Inquiry date should be close to now",
                inquiry.getInquiryDate().isBefore(LocalDateTime.now().plusSeconds(5)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullInquirer_throwsException() {
        new Inquiry(null, subject, "Details");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullSubjectPerson_throwsException() {
        new Inquiry(inquirer, null, "Details");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullDetails_throwsException() {
        new Inquiry(inquirer, subject, null);
    }

    @Test
    public void testSetDetails_validDetails_updatesDetails() {
        inquiry.setDetails("Updated details");
        assertEquals("Details should be updated", "Updated details", inquiry.getDetails());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDetails_nullDetails_throwsException() {
        inquiry.setDetails(null);
    }

    @Test
    public void testSetId_validId_setsId() {
        inquiry.setId(10);
        assertEquals("ID should be 10", 10, inquiry.getId());
    }
}
