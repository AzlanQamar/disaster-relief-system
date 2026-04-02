package edu.ucalgary.oop;

/**
 * The Person class represents an individual with an ID, first name, last name,
 * and comments. It serves as a base class for other classes that may inherit
 * from it.
 */
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String comments;

    public Person(String firstName, String lastName) {

        if (firstName == null) {
            throw new IllegalArgumentException("First name cannot be null");
        }
        if (firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }

        this.firstName = firstName;
        this.lastName = lastName;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("First name cannot be null");
        }
        if (firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
