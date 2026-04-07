package edu.ucalgary.oop;

/**
 * Represents a skill possessed by a disaster victim. Acts as a base class for
 * MedicalSkill, LanguageSkill, and TradeSkill, storing the associated skill
 * reference and proficiency level.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class VictimSkill {

    private int id;
    private Skill skill;
    private String proficiencyLevel;

    /**
     * Constructs a VictimSkill with the specified skill and proficiency level.
     *
     * @param skill the associated Skill
     * @param proficiencyLevel the proficiency level
     * (beginner/intermediate/advanced)
     * @throws IllegalArgumentException if skill is null or proficiencyLevel is
     * null or empty
     */
    public VictimSkill(Skill skill, String proficiencyLevel) throws IllegalArgumentException {
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
     * Returns the associated Skill.
     *
     * @return the Skill
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Returns the proficiency level of this skill.
     *
     * @return the proficiency level string
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
    public void setProficiencyLevel(String proficiencyLevel) throws IllegalArgumentException {
        if (proficiencyLevel == null) {
            throw new IllegalArgumentException("Proficiency level cannot be null");
        }
        if (proficiencyLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Proficiency level cannot be empty");
        }
        this.proficiencyLevel = proficiencyLevel;
    }
}
