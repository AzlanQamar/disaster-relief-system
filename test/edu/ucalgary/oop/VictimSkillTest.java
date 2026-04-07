package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the VictimSkill class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class VictimSkillTest {

    private Skill skill;
    private VictimSkill victimSkill;

    @Before
    public void setUp() {
        skill = new Skill("carpentry", "trade");
        victimSkill = new VictimSkill(skill, "beginner");
    }

    @Test
    public void testConstructor_validInputs_setsSkill() {
        assertEquals("Skill should be set correctly", skill, victimSkill.getSkill());
    }

    @Test
    public void testConstructor_validInputs_setsProficiencyLevel() {
        assertEquals("Proficiency level should be beginner", "beginner", victimSkill.getProficiencyLevel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullSkill_throwsException() {
        new VictimSkill(null, "beginner");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullProficiencyLevel_throwsException() {
        new VictimSkill(skill, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptyProficiencyLevel_throwsException() {
        new VictimSkill(skill, "   ");
    }

    @Test
    public void testSetProficiencyLevel_validLevel_updatesLevel() {
        victimSkill.setProficiencyLevel("advanced");
        assertEquals("Proficiency level should be updated to advanced", "advanced", victimSkill.getProficiencyLevel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetProficiencyLevel_nullLevel_throwsException() {
        victimSkill.setProficiencyLevel(null);
    }

    @Test
    public void testSetId_validId_setsId() {
        victimSkill.setId(5);
        assertEquals("ID should be 5", 5, victimSkill.getId());
    }
}
