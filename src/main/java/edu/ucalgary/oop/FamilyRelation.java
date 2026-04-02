package edu.ucalgary.oop;

/**
 * The FamilyRelation class represents a family relationship between two people.
 * It contains attributes for the two people involved and the type of
 * relationship.
 */
public class FamilyRelation {

    private int id;
    private Person personOne;
    private Person personTwo;
    private String relationshipTo;

    public FamilyRelation(Person personOne, Person personTwo, String relationshipTo) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPersonOne() {
        return personOne;
    }

    public void setPersonOne(Person personOne) {
        if (personOne == null) {
            throw new IllegalArgumentException("Person one cannot be null");
        }
        this.personOne = personOne;
    }

    public Person getPersonTwo() {
        return personTwo;
    }

    public void setPersonTwo(Person personTwo) {
        if (personTwo == null) {
            throw new IllegalArgumentException("Person two cannot be null");
        }
        this.personTwo = personTwo;
    }

    public String getRelationshipTo() {
        return relationshipTo;
    }

    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

}
