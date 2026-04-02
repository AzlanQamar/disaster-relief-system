package edu.ucalgary.oop;

/**
 * The CulturalRequirement class represents a cultural requirement that may be
 * needed for disaster relief services. It contains attributes for the category
 * of the requirement and the specific option within that category.
 */
public class CulturalRequirement {

    private int id;
    private String requirementCategory;
    private String requirementOption;

    public CulturalRequirement(String requirementCategory, String requirementOption) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequirementCategory() {
        return requirementCategory;
    }

    public void setRequirementCategory(String requirementCategory) {
        if (requirementCategory == null) {
            throw new IllegalArgumentException("Requirement category cannot be null");
        }
        if (requirementCategory.trim().isEmpty()) {
            throw new IllegalArgumentException("Requirement category cannot be empty");
        }
        this.requirementCategory = requirementCategory;
    }

    public String getRequirementOption() {
        return requirementOption;
    }

    public void setRequirementOption(String requirementOption) {
        if (requirementOption == null) {
            throw new IllegalArgumentException("Requirement option cannot be null");
        }
        if (requirementOption.trim().isEmpty()) {
            throw new IllegalArgumentException("Requirement option cannot be empty");
        }
        this.requirementOption = requirementOption;
    }

}
