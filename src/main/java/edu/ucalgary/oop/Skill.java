package edu.ucalgary.oop;

/**
 * The Skill class represents a skill that a volunteer may have. It contains
 * attributes for the skill name and category, as well as an ID for database
 * purposes.
 */
public class Skill {

    private int id;
    private String skillName;
    private String category;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        if (skillName == null) {
            throw new IllegalArgumentException("Skill name cannot be null");
        }
        if (skillName.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be empty");
        }
        this.skillName = skillName;
    }

    public String getCategory() {
        return category;
    }

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
