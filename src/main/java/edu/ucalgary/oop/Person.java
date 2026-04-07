package edu.ucalgary.oop;

/**
 * Represents an individual in the disaster relief system. Serves as a base
 * class for DisasterVictim and Inquirer.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String comments;

    /**
     * Constructs a Person with a first and last name.
     *
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @throws IllegalArgumentException if firstName is null or empty
     */
    public Person(String firstName, String lastName) throws IllegalArgumentException {
        if (firstName == null) {
            throw new IllegalArgumentException("First name cannot be null");
        }
        if (firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the person's database ID.
     *
     * @return the person's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the person's database ID.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the person's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the person's first name.
     *
     * @param firstName the new first name
     * @throws IllegalArgumentException if firstName is null or empty
     */
    public void setFirstName(String firstName) throws IllegalArgumentException {
        if (firstName == null) {
            throw new IllegalArgumentException("First name cannot be null");
        }
        if (firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.firstName = firstName;
    }

    /**
     * Returns the person's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the person's last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns any comments about the person.
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets comments about the person.
     *
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}
