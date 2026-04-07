package edu.ucalgary.oop;

import java.time.LocalDate;

/**
 * Represents a skill possessed by a disaster victim. Stores information about
 * the associated skill, proficiency level, optional details, language
 * capabilities, and certification expiry.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class VictimSkill {

    private int id;
    private Skill skill;
    private String details;
    private String languageCapabilities;
    private LocalDate certificationExpiry;
    private String proficiencyLevel;

    /**
     * Constructs a VictimSkill with the specified skill and proficiency level.
     *
     * @param skill the associated Skill
     * @param proficiencyLevel the proficiency level of the skill
     * @throws IllegalArgumentException if skill is null or proficiencyLevel is
     * null or empty
     */
    public VictimSkill(Skill skill, String proficiencyLevel) {
        if (skill == null) {
            throw new IllegalArgumentException("Skill cannot be null");
        }
        if (proficiencyLevel == null) {
            throw new IllegalArgumentException("Proficiency level cannot be null");
        }
        if (proficiencyLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Proficiency level cannot be empty");
        }
        this.skill = skill;
        this.proficiencyLevel = proficiencyLevel;
    }

    /**
     * Returns the database ID of this victim skill.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database ID of this victim skill.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the associated skill.
     *
     * @return the Skill
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Sets the associated skill.
     *
     * @param skill the Skill to set
     * @throws IllegalArgumentException if skill is null
     */
    public void setSkill(Skill skill) {
        if (skill == null) {
            throw new IllegalArgumentException("Skill cannot be null");
        }
        this.skill = skill;
    }

    /**
     * Returns additional details about this skill.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets additional details about this skill.
     *
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Returns the language capabilities associated with this skill.
     *
     * @return the language capabilities
     */
    public String getLanguageCapabilities() {
        return languageCapabilities;
    }

    /**
     * Sets the language capabilities associated with this skill.
     *
     * @param languageCapabilities the language capabilities to set
     */
    public void setLanguageCapabilities(String languageCapabilities) {
        this.languageCapabilities = languageCapabilities;
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

    /**
     * Returns the proficiency level of this skill.
     *
     * @return the proficiency level
     */
    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

    /**
     * Sets the proficiency level of this skill.
     *
     * @param proficiencyLevel the proficiency level to set
     * @throws IllegalArgumentException if proficiencyLevel is null or empty
     */
    public void setProficiencyLevel(String proficiencyLevel) {
        if (proficiencyLevel == null) {
            throw new IllegalArgumentException("Proficiency level cannot be null");
        }
        if (proficiencyLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Proficiency level cannot be empty");
        }
        this.proficiencyLevel = proficiencyLevel;
    }

}
