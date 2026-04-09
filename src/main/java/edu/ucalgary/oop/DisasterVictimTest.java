package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Unit tests for the DisasterVictim class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class DisasterVictimTest {

    private DisasterVictim victim;

    @Before
    public void setUp() {
        victim = new DisasterVictim("John", LocalDate.of(2025, 1, 1));
    }

    @Test
    public void testConstructor_validInputs_setsFirstName() {
        assertEquals("First name should be John", "John", victim.getFirstName());
    }

    @Test
    public void testConstructor_validInputs_setsEntryDate() {
        assertEquals("Entry date should be 2025-01-01", LocalDate.of(2025, 1, 1), victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullEntryDate_throwsException() {
        new DisasterVictim("Jane", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullFirstName_throwsException() {
        new DisasterVictim(null, LocalDate.now());
    }

    @Test
    public void testSetApproximateAge_validAge_setsAge() throws InvalidAgeException {
        // Arrange + Act
        victim.setApproximateAge(25);
        // Assert
        assertEquals("Approximate age should be 25", 25, (int) victim.getApproximateAge());
    }

    @Test(expected = InvalidAgeException.class)
    public void testSetApproximateAge_whenDateOfBirthAlreadySet_throwsException() throws InvalidAgeException {
        victim.setDateOfBirth(LocalDate.of(2000, 1, 1));
        victim.setApproximateAge(25);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetApproximateAge_negativeAge_throwsException() throws InvalidAgeException {
        victim.setApproximateAge(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetApproximateAge_ageOver150_throwsException() throws InvalidAgeException {
        victim.setApproximateAge(151);
    }

    @Test
    public void testSetApproximateAge_boundaryAge0_setsSuccessfully() throws InvalidAgeException {
        victim.setApproximateAge(0);
        assertEquals("Age 0 should be valid", 0, (int) victim.getApproximateAge());
    }

    @Test
    public void testSetApproximateAge_boundaryAge150_setsSuccessfully() throws InvalidAgeException {
        victim.setApproximateAge(150);
        assertEquals("Age 150 should be valid", 150, (int) victim.getApproximateAge());
    }

    @Test
    public void testSetDateOfBirth_validDate_setsDateOfBirth() throws InvalidAgeException {
        LocalDate dob = LocalDate.of(2000, 6, 15);
        victim.setDateOfBirth(dob);
        assertEquals("Date of birth should be set correctly", dob, victim.getDateOfBirth());
    }

    @Test(expected = InvalidAgeException.class)
    public void testSetDateOfBirth_whenApproximateAgeAlreadySet_throwsException() throws InvalidAgeException {
        victim.setApproximateAge(25);
        victim.setDateOfBirth(LocalDate.of(2000, 1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirth_futureDate_throwsException() throws InvalidAgeException {
        victim.setDateOfBirth(LocalDate.now().plusDays(1));
    }

    @Test
    public void testSetDateOfBirth_cannotBeReplacedByApproximateAge() throws InvalidAgeException {
        // Arrange
        victim.setDateOfBirth(LocalDate.of(1990, 1, 1));
        // Act + Assert
        try {
            victim.setApproximateAge(30);
            fail("Expected InvalidAgeException when replacing birthdate with approximate age");
        } catch (InvalidAgeException e) {
            assertNotNull("Exception message should not be null", e.getMessage());
        }
    }

    @Test
    public void testSetGender_validGender_setsGender() {
        victim.setGender("man");
        assertEquals("Gender should be set to man", "man", victim.getGender());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetGender_nullGender_throwsException() {
        victim.setGender(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetGender_emptyGender_throwsException() {
        victim.setGender("   ");
    }

    @Test
    public void testIsSoftDeleted_newVictim_returnsFalse() {
        assertFalse("New victim should not be soft deleted", victim.isSoftDeleted());
    }

    @Test
    public void testSoftDelete_afterCall_returnsTrue() {
        victim.softDelete();
        assertTrue("Victim should be soft deleted after calling softDelete()", victim.isSoftDeleted());
    }

    @Test
    public void testAddMedicalRecord_validRecord_addsToList() {
        Location loc = new Location("Hospital", "123 Main St");
        MedicalRecord record = new MedicalRecord(loc, "Treatment details", LocalDate.now());
        victim.addMedicalRecord(record);
        assertEquals("Medical records list should have 1 entry", 1, victim.getMedicalRecords().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMedicalRecord_nullRecord_throwsException() {
        victim.addMedicalRecord(null);
    }

    @Test
    public void testAddPersonalBelonging_validSupply_addsToList() {
        Supply supply = new Supply("blanket");
        victim.addPersonalBelonging(supply);
        assertEquals("Personal belongings should have 1 entry", 1, victim.getPersonalBelongings().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPersonalBelonging_nullSupply_throwsException() {
        victim.addPersonalBelonging(null);
    }

    @Test
    public void testRemovePersonalBelonging_existingSupply_removesFromList() {
        Supply supply = new Supply("blanket");
        victim.addPersonalBelonging(supply);
        victim.removePersonalBelonging(supply);
        assertEquals("Personal belongings should be empty after removal", 0, victim.getPersonalBelongings().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovePersonalBelonging_nonExistingSupply_throwsException() {
        victim.removePersonalBelonging(new Supply("blanket"));
    }

    @Test
    public void testAddFamilyConnection_validRelation_addsToList() {
        DisasterVictim other = new DisasterVictim("Jane", LocalDate.now());
        FamilyRelation relation = new FamilyRelation(victim, other, "spouse");
        victim.addFamilyConnection(relation);
        assertEquals("Family connections should have 1 entry", 1, victim.getFamilyConnections().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFamilyConnection_nullRelation_throwsException() {
        victim.addFamilyConnection(null);
    }

    @Test
    public void testRemoveFamilyConnection_existingRelation_removesFromList() {
        DisasterVictim other = new DisasterVictim("Jane", LocalDate.now());
        FamilyRelation relation = new FamilyRelation(victim, other, "spouse");
        victim.addFamilyConnection(relation);
        victim.removeFamilyConnection(relation);
        assertEquals("Family connections should be empty after removal", 0, victim.getFamilyConnections().size());
    }

    @Test
    public void testAddCulturalRequirement_validRequirement_addsToList() {
        CulturalRequirement req = new CulturalRequirement("dietary restrictions", "halal");
        victim.addCulturalRequirement(req);
        assertEquals("Cultural requirements should have 1 entry", 1, victim.getCulturalRequirements().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCulturalRequirement_duplicateCategory_throwsException() {
        victim.addCulturalRequirement(new CulturalRequirement("dietary restrictions", "halal"));
        victim.addCulturalRequirement(new CulturalRequirement("dietary restrictions", "vegan"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCulturalRequirement_nullRequirement_throwsException() {
        victim.addCulturalRequirement(null);
    }

    @Test
    public void testAddCulturalRequirement_differentCategories_bothAdded() {
        victim.addCulturalRequirement(new CulturalRequirement("dietary restrictions", "halal"));
        victim.addCulturalRequirement(new CulturalRequirement("safe-space requirements", "women-only"));
        assertEquals("Should allow requirements from different categories", 2, victim.getCulturalRequirements().size());
    }

    @Test
    public void testRemoveCulturalRequirement_existingCategory_removesFromList() {
        victim.addCulturalRequirement(new CulturalRequirement("dietary restrictions", "halal"));
        victim.removeCulturalRequirement("dietary restrictions");
        assertEquals("Cultural requirements should be empty after removal", 0, victim.getCulturalRequirements().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCulturalRequirement_nonExistingCategory_throwsException() {
        victim.removeCulturalRequirement("dietary restrictions");
    }

    @Test
    public void testAddVictimSkill_validSkill_addsToList() {
        Skill skill = new Skill("French", "language");
        VictimSkill vs = new VictimSkill(skill, "beginner");
        victim.addVictimSkill(vs);
        assertEquals("Victim skills should have 1 entry", 1, victim.getVictimSkills().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVictimSkill_nullSkill_throwsException() {
        victim.addVictimSkill(null);
    }

    @Test
    public void testRemoveVictimSkill_existingId_removesFromList() {
        Skill skill = new Skill("French", "language");
        VictimSkill vs = new VictimSkill(skill, "beginner");
        vs.setId(1);
        victim.addVictimSkill(vs);
        victim.removeVictimSkill(1);
        assertEquals("Victim skills should be empty after removal", 0, victim.getVictimSkills().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveVictimSkill_nonExistingId_throwsException() {
        victim.removeVictimSkill(99);
    }
}
