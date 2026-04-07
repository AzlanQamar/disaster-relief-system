package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Represents a medical skill associated with a disaster victim. Extends
 * VictimSkill by including certification type and certification expiry date.
 * Ensures that certification types are restricted to a predefined valid set.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class MedicalSkill extends VictimSkill {

    public static final String[] VALID_CERTIFICATION_TYPES = {"first-aid", "counseling", "nursing", "doctor"};

    private String certificationType;
    private LocalDate certificationExpiry;

    /**
     * Constructs a MedicalSkill with the specified skill, proficiency level,
     * certification type, and certification expiry date.
     *
     * @param skill the skill associated with this record
     * @param proficiencyLevel the proficiency level of the skill
     * @param certificationType the type of certification
     * @param certificationExpiry the expiry date of the certification
     * @throws IllegalArgumentException if certificationType is not valid
     */
    public MedicalSkill(Skill skill, String proficiencyLevel, String certificationType, LocalDate certificationExpiry)
            throws IllegalArgumentException {
        super(skill, proficiencyLevel);
        setCertificationType(certificationType);
        this.certificationExpiry = certificationExpiry;
    }

    /**
     * Returns the certification type for this medical skill.
     *
     * @return the certification type
     */
    public String getCertificationType() {
        return certificationType;
    }

    /**
     * Sets the certification type for this medical skill.
     *
     * @param certificationType the certification type to set
     * @throws IllegalArgumentException if the certification type is not one of
     * the valid types
     */
    public void setCertificationType(String certificationType) throws IllegalArgumentException {
        if (!Arrays.asList(VALID_CERTIFICATION_TYPES).contains(certificationType)) {
            throw new IllegalArgumentException("Invalid certification type. Must be one of: "
                    + Arrays.toString(VALID_CERTIFICATION_TYPES));
        }
        this.certificationType = certificationType;
    }

    /**
     * Returns the certification expiry date.
     *
     * @return the certification expiry date
     */
    public LocalDate getCertificationExpiry() {
        return certificationExpiry;
    }

    /**
     * Sets the certification expiry date.
     *
     * @param certificationExpiry the expiry date to set
     */
    public void setCertificationExpiry(LocalDate certificationExpiry) {
        this.certificationExpiry = certificationExpiry;
    }
}
