package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the Person class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class PersonTest {

    private Person person;

    @Before
    public void setUp() {
        person = new Person("John", "Smith");
    }

    @Test
    public void testConstructor_validInputs_setsFirstName() {
        assertEquals("First name should be John", "John", person.getFirstName());
    }

    @Test
    public void testConstructor_validInputs_setsLastName() {
        assertEquals("Last name should be Smith", "Smith", person.getLastName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullFirstName_throwsException() {
        new Person(null, "Smith");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptyFirstName_throwsException() {
        new Person("   ", "Smith");
    }

    @Test
    public void testConstructor_nullLastName_allowsNull() {
        Person p = new Person("John", null);
        assertNull("Last name should be null", p.getLastName());
    }

    @Test
    public void testSetFirstName_validName_updatesFirstName() {
        person.setFirstName("Jane");
        assertEquals("First name should be updated to Jane", "Jane", person.getFirstName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFirstName_nullName_throwsException() {
        person.setFirstName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFirstName_emptyName_throwsException() {
        person.setFirstName("");
    }

    @Test
    public void testSetLastName_validName_updatesLastName() {
        person.setLastName("Doe");
        assertEquals("Last name should be updated to Doe", "Doe", person.getLastName());
    }

    @Test
    public void testSetComments_validComments_setsComments() {
        person.setComments("Alert but quiet");
        assertEquals("Comments should be set correctly", "Alert but quiet", person.getComments());
    }

    @Test
    public void testSetId_validId_setsId() {
        person.setId(42);
        assertEquals("ID should be 42", 42, person.getId());
    }
}
