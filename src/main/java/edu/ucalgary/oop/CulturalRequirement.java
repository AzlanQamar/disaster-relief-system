package edu.ucalgary.oop;

/**
 * Represents a cultural requirement for a disaster victim, such as a dietary
 * restriction or safe-space requirement. Each requirement has a category and a
 * selected option within that category.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class CulturalRequirement {

    private int id;
    private String requirementCategory;
    private String requirementOption;

    /**
     * Constructs a CulturalRequirement with the given category and option.
     *
     * @param requirementCategory the category of the requirement (e.g. "dietary
     * restrictions")
     * @param requirementOption the selected option within the category (e.g.
     * "halal")
     * @throws IllegalArgumentException if either parameter is null or empty
     */
    public CulturalRequirement(String requirementCategory, String requirementOption) throws IllegalArgumentException {
        if (requirementCategory == null) {
            throw new IllegalArgumentException("Requirement category cannot be null");
        }
        if (requirementCategory.trim().isEmpty()) {
            throw new IllegalArgumentException("Requirement category cannot be empty");
        }
        if (requirementOption == null) {
            throw new IllegalArgumentException("Requirement option cannot be null");
        }
        if (requirementOption.trim().isEmpty()) {
            throw new IllegalArgumentException("Requirement option cannot be empty");
        }
        this.requirementCategory = requirementCategory;
        this.requirementOption = requirementOption;
    }

    /**
     * Returns the database ID of this requirement.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database ID of this requirement.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the category of this requirement.
     *
     * @return the requirement category
     */
    public String getRequirementCategory() {
        return requirementCategory;
    }

    /**
     * Sets the category of this requirement.
     *
     * @param requirementCategory the new category
     * @throws IllegalArgumentException if the category is null or empty
     */
    public void setRequirementCategory(String requirementCategory) throws IllegalArgumentException {
        if (requirementCategory == null) {
            throw new IllegalArgumentException("Requirement category cannot be null");
        }
        if (requirementCategory.trim().isEmpty()) {
            throw new IllegalArgumentException("Requirement category cannot be empty");
        }
        this.requirementCategory = requirementCategory;
    }

    /**
     * Returns the selected option for this requirement.
     *
     * @return the requirement option
     */
    public String getRequirementOption() {
        return requirementOption;
    }

    /**
     * Sets the selected option for this requirement.
     *
     * @param requirementOption the new option
     * @throws IllegalArgumentException if the option is null or empty
     */
    public void setRequirementOption(String requirementOption) throws IllegalArgumentException {
        if (requirementOption == null) {
            throw new IllegalArgumentException("Requirement option cannot be null");
        }
        if (requirementOption.trim().isEmpty()) {
            throw new IllegalArgumentException("Requirement option cannot be empty");
        }
        this.requirementOption = requirementOption;
    }
}
