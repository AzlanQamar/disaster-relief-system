package edu.ucalgary.oop;

/**
 * Represents a family relationship between two people in the disaster relief
 * system. Stores the two people involved and the type of relationship between
 * them.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class FamilyRelation {

    private int id;
    private Person personOne;
    private Person personTwo;
    private String relationshipTo;

    /**
     * Constructs a FamilyRelation between two people with a specified
     * relationship type.
     *
     * @param personOne the first person in the relationship
     * @param personTwo the second person in the relationship
     * @param relationshipTo the type of relationship (e.g. "parent", "spouse")
     * @throws IllegalArgumentException if any parameter is null
     */
    public FamilyRelation(Person personOne, Person personTwo, String relationshipTo) throws IllegalArgumentException {
        if (personOne == null) {
            throw new IllegalArgumentException("Person one cannot be null");
        }
        if (personTwo == null) {
            throw new IllegalArgumentException("Person two cannot be null");
        }
        if (relationshipTo == null) {
            throw new IllegalArgumentException("Relationship to cannot be null");
        }
        this.personOne = personOne;
        this.personTwo = personTwo;
        this.relationshipTo = relationshipTo;
    }

    /**
     * Returns the database ID of this relation.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database ID of this relation.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the first person in the relationship.
     *
     * @return personOne
     */
    public Person getPersonOne() {
        return personOne;
    }

    /**
     * Sets the first person in the relationship.
     *
     * @param personOne the Person to set
     * @throws IllegalArgumentException if personOne is null
     */
    public void setPersonOne(Person personOne) throws IllegalArgumentException {
        if (personOne == null) {
            throw new IllegalArgumentException("Person one cannot be null");
        }
        this.personOne = personOne;
    }

    /**
     * Returns the second person in the relationship.
     *
     * @return personTwo
     */
    public Person getPersonTwo() {
        return personTwo;
    }

    /**
     * Sets the second person in the relationship.
     *
     * @param personTwo the Person to set
     * @throws IllegalArgumentException if personTwo is null
     */
    public void setPersonTwo(Person personTwo) throws IllegalArgumentException {
        if (personTwo == null) {
            throw new IllegalArgumentException("Person two cannot be null");
        }
        this.personTwo = personTwo;
    }

    /**
     * Returns the type of relationship between the two people.
     *
     * @return the relationship type string
     */
    public String getRelationshipTo() {
        return relationshipTo;
    }

    /**
     * Sets the type of relationship between the two people.
     *
     * @param relationshipTo the relationship type to set
     */
    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }
}
