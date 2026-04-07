package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Unit tests for the FamilyRelation class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class FamilyRelationTest {

    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private FamilyRelation relation;

    @Before
    public void setUp() {
        personOne = new DisasterVictim("John", LocalDate.now());
        personTwo = new DisasterVictim("Jane", LocalDate.now());
        relation = new FamilyRelation(personOne, personTwo, "spouse");
    }

    @Test
    public void testConstructor_validInputs_setsPersonOne() {
        assertEquals("Person one should be John", personOne, relation.getPersonOne());
    }

    @Test
    public void testConstructor_validInputs_setsPersonTwo() {
        assertEquals("Person two should be Jane", personTwo, relation.getPersonTwo());
    }

    @Test
    public void testConstructor_validInputs_setsRelationshipTo() {
        assertEquals("Relationship should be spouse", "spouse", relation.getRelationshipTo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullPersonOne_throwsException() {
        new FamilyRelation(null, personTwo, "spouse");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullPersonTwo_throwsException() {
        new FamilyRelation(personOne, null, "spouse");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullRelationship_throwsException() {
        new FamilyRelation(personOne, personTwo, null);
    }

    @Test
    public void testSetPersonOne_validPerson_updatesPersonOne() {
        DisasterVictim newPerson = new DisasterVictim("Bob", LocalDate.now());
        relation.setPersonOne(newPerson);
        assertEquals("Person one should be updated", newPerson, relation.getPersonOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPersonOne_nullPerson_throwsException() {
        relation.setPersonOne(null);
    }

    @Test
    public void testSetPersonTwo_validPerson_updatesPersonTwo() {
        DisasterVictim newPerson = new DisasterVictim("Alice", LocalDate.now());
        relation.setPersonTwo(newPerson);
        assertEquals("Person two should be updated", newPerson, relation.getPersonTwo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPersonTwo_nullPerson_throwsException() {
        relation.setPersonTwo(null);
    }

    @Test
    public void testSetRelationshipTo_validRelationship_updatesRelationship() {
        relation.setRelationshipTo("parent");
        assertEquals("Relationship should be updated to parent", "parent", relation.getRelationshipTo());
    }

    @Test
    public void testSetId_validId_setsId() {
        relation.setId(4);
        assertEquals("ID should be 4", 4, relation.getId());
    }
}
