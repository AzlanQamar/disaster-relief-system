package edu.ucalgary.oop;

/**
 * Represents a skill that a volunteer or disaster victim may possess. Stores
 * the skill name, category, and a database ID, with validation to ensure
 * non-null and non-empty values.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class Skill {

    private int id;
    private String skillName;
    private String category;

    /**
     * Constructs a Skill with the specified name and category.
     *
     * @param skillName the name of the skill
     * @param category the category of the skill
     * @throws IllegalArgumentException if skillName or category is null or
     * empty
     */
    public Skill(String skillName, String category) {
        if (skillName == null) {
            throw new IllegalArgumentException("Skill name cannot be null");
        }
        if (skillName.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be empty");
        }

        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        this.skillName = skillName;
        this.category = category;
    }

    /**
     * Returns the database ID of this skill.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database ID of this skill.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of this skill.
     *
     * @return the skill name
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * Sets the name of this skill.
     *
     * @param skillName the new skill name
     * @throws IllegalArgumentException if skillName is null or empty
     */
    public void setSkillName(String skillName) {
        if (skillName == null) {
            throw new IllegalArgumentException("Skill name cannot be null");
        }
        if (skillName.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be empty");
        }
        this.skillName = skillName;
    }

    /**
     * Returns the category of this skill.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of this skill.
     *
     * @param category the new category
     * @throws IllegalArgumentException if category is null or empty
     */
    public void setCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }
        this.category = category;
    }

}
