package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the Inquirer class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class InquirerTest {

    private Inquirer inquirer;

    @Before
    public void setUp() {
        inquirer = new Inquirer("Joseph", "Bouillon", "Looking for family", "555-1234");
    }

    @Test
    public void testConstructor_validInputs_setsFirstName() {
        assertEquals("First name should be Joseph", "Joseph", inquirer.getFirstName());
    }

    @Test
    public void testConstructor_validInputs_setsLastName() {
        assertEquals("Last name should be Bouillon", "Bouillon", inquirer.getLastName());
    }

    @Test
    public void testConstructor_validInputs_setsInfo() {
        assertEquals("Info should be set correctly", "Looking for family", inquirer.getInfo());
    }

    @Test
    public void testConstructor_validInputs_setsServicesPhone() {
        assertEquals("Services phone should be set correctly", "555-1234", inquirer.getServicesPhoneNum());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullInfo_throwsException() {
        new Inquirer("Joseph", "Bouillon", null, "555-1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullPhone_throwsException() {
        new Inquirer("Joseph", "Bouillon", "info", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullFirstName_throwsException() {
        new Inquirer(null, "Bouillon", "info", "555-1234");
    }
}
