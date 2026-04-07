package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the LanguageSkill class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class LanguageSkillTest {

    private Skill skill;
    private LanguageSkill languageSkill;

    @Before
    public void setUp() {
        skill = new Skill("French", "language");
        languageSkill = new LanguageSkill(skill, "intermediate", "French", true, false);
    }

    @Test
    public void testConstructor_validInputs_setsLanguageName() {
        assertEquals("Language name should be French", "French", languageSkill.getLanguageName());
    }

    @Test
    public void testConstructor_validInputs_setsReadWrite() {
        assertTrue("Read/write should be true", languageSkill.hasReadWrite());
    }

    @Test
    public void testConstructor_validInputs_setsSpeakListen() {
        assertFalse("Speak/listen should be false", languageSkill.hasSpeakListen());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nullLanguageName_throwsException() {
        new LanguageSkill(skill, "beginner", null, true, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptyLanguageName_throwsException() {
        new LanguageSkill(skill, "beginner", "   ", true, true);
    }

    @Test
    public void testSetLanguageName_validName_updatesName() {
        languageSkill.setLanguageName("Spanish");
        assertEquals("Language name should be updated to Spanish", "Spanish", languageSkill.getLanguageName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLanguageName_nullName_throwsException() {
        languageSkill.setLanguageName(null);
    }

    @Test
    public void testSetReadWrite_false_updatesFlag() {
        languageSkill.setReadWrite(false);
        assertFalse("Read/write should be updated to false", languageSkill.hasReadWrite());
    }

    @Test
    public void testSetSpeakListen_true_updatesFlag() {
        languageSkill.setSpeakListen(true);
        assertTrue("Speak/listen should be updated to true", languageSkill.hasSpeakListen());
    }
}
