package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the CulturalRequirement class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class CulturalRequirementTest {

    private CulturalRequirement requirement;

    @Before
    public void setUp() {
        requirement = new CulturalRequirement("dietary restrictions", "halal");
    }

    @Test
    public void testConstructor_validInputs_setsCategory() {
        assertEquals("Category should be dietary restrictions",
                "dietary restrictions", requirement.getRequirementCategory());
    }

    @Test
    public void testConstructor_validInputs_setsOption() {
        assertEquals("Option should be halal", "halal", requirement.getRequirementOption());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullCategory_throwsException() {
        new CulturalRequirement(null, "halal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptyCategory_throwsException() {
        new CulturalRequirement("   ", "halal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullOption_throwsException() {
        new CulturalRequirement("dietary restrictions", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptyOption_throwsException() {
        new CulturalRequirement("dietary restrictions", "   ");
    }

    @Test
    public void testSetRequirementOption_validOption_updatesOption() {
        requirement.setRequirementOption("vegan");
        assertEquals("Option should be updated to vegan", "vegan", requirement.getRequirementOption());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRequirementOption_nullOption_throwsException() {
        requirement.setRequirementOption(null);
    }

    @Test
    public void testSetId_validId_setsId() {
        requirement.setId(3);
        assertEquals("ID should be 3", 3, requirement.getId());
    }
}
