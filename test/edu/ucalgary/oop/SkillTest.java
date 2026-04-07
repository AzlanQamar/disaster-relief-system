package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the Skill class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class SkillTest {

    private Skill skill;

    @Before
    public void setUp() {
        skill = new Skill("carpentry", "trade");
    }

    @Test
    public void testConstructor_validInputs_setsSkillName() {
        assertEquals("Skill name should be carpentry", "carpentry", skill.getSkillName());
    }

    @Test
    public void testConstructor_validInputs_setsCategory() {
        assertEquals("Category should be trade", "trade", skill.getCategory());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullSkillName_throwsException() {
        new Skill(null, "trade");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptySkillName_throwsException() {
        new Skill("   ", "trade");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullCategory_throwsException() {
        new Skill("carpentry", null);
    }

    @Test
    public void testSetSkillName_validName_updatesName() {
        skill.setSkillName("plumbing");
        assertEquals("Skill name should be updated to plumbing", "plumbing", skill.getSkillName());
    }

    @Test
    public void testSetCategory_validCategory_updatesCategory() {
        skill.setCategory("medical");
        assertEquals("Category should be updated to medical", "medical", skill.getCategory());
    }

    @Test
    public void testSetId_validId_setsId() {
        skill.setId(2);
        assertEquals("ID should be 2", 2, skill.getId());
    }
}
