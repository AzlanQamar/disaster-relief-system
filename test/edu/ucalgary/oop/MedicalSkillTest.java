package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Unit tests for the MedicalSkill class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class MedicalSkillTest {

    private Skill skill;
    private MedicalSkill medicalSkill;

    @Before
    public void setUp() {
        skill = new Skill("first-aid", "medical");
        medicalSkill = new MedicalSkill(skill, "intermediate", "first-aid", LocalDate.of(2027, 12, 31));
    }

    @Test
    public void testConstructor_validInputs_setsCertificationType() {
        assertEquals("Certification type should be first-aid", "first-aid", medicalSkill.getCertificationType());
    }

    @Test
    public void testConstructor_validInputs_setsCertificationExpiry() {
        assertEquals("Certification expiry should be set correctly",
                LocalDate.of(2027, 12, 31), medicalSkill.getCertificationExpiry());
    }

    @Test
    public void testConstructor_validInputs_setsProficiencyLevel() {
        assertEquals("Proficiency level should be intermediate", "intermediate", medicalSkill.getProficiencyLevel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_invalidCertificationType_throwsException() {
        new MedicalSkill(skill, "beginner", "invalid-type", LocalDate.now().plusYears(1));
    }

    @Test
    public void testConstructor_validCertificationTypeNursing_setsSuccessfully() {
        MedicalSkill ms = new MedicalSkill(skill, "advanced", "nursing", LocalDate.now().plusYears(1));
        assertEquals("Certification type should be nursing", "nursing", ms.getCertificationType());
    }

    @Test
    public void testSetCertificationType_validType_updatesType() {
        medicalSkill.setCertificationType("doctor");
        assertEquals("Certification type should be updated to doctor", "doctor", medicalSkill.getCertificationType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCertificationType_invalidType_throwsException() {
        medicalSkill.setCertificationType("invalid");
    }

    @Test
    public void testSetCertificationExpiry_validDate_updatesExpiry() {
        LocalDate newExpiry = LocalDate.of(2030, 1, 1);
        medicalSkill.setCertificationExpiry(newExpiry);
        assertEquals("Expiry date should be updated", newExpiry, medicalSkill.getCertificationExpiry());
    }
}
