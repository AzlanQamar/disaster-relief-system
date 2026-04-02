package edu.ucalgary.oop;

import java.time.LocalDate;

/**
 * The VictimSkill class represents a skill possessed by a disaster victim. It
 * contains information about the skill, proficiency level, language
 * capabilities, certification expiry, and additional details.
 */
public class VictimSkill {

    private int id;
    private Skill skill;
    private String details;
    private String languageCapabilities;
    private LocalDate certificationExpiry;
    private String proficiencyLevel;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        if (skill == null) {
            throw new IllegalArgumentException("Skill cannot be null");
        }
        this.skill = skill;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {

        this.details = details;
    }

    public String getLanguageCapabilities() {
        return languageCapabilities;
    }

    public void setLanguageCapabilities(String languageCapabilities) {
        this.languageCapabilities = languageCapabilities;
    }

    public LocalDate getCertificationExpiry() {
        return certificationExpiry;
    }

    public void setCertificationExpiry(LocalDate certificationExpiry) {
        this.certificationExpiry = certificationExpiry;
    }

    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

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
