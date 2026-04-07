package edu.ucalgary.oop;

import java.time.LocalDateTime;

/**
 * Represents an inquiry made by a person searching for a missing disaster
 * victim. Records the inquirer, the subject person, the timestamp of the
 * inquiry, and any details provided.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class Inquiry {

    private int id;
    private Person inquirer;
    private Person subjectPerson;
    private LocalDateTime inquiryDate;
    private String details;

    /**
     * Constructs an Inquiry with the given inquirer, subject, and details. The
     * inquiry date is automatically set to the current date and time.
     *
     * @param inquirer the person making the inquiry
     * @param subjectPerson the person being searched for
     * @param details additional information provided by the inquirer
     * @throws IllegalArgumentException if any parameter is null
     */
    public Inquiry(Person inquirer, Person subjectPerson, String details) throws IllegalArgumentException {
        if (inquirer == null) {
            throw new IllegalArgumentException("Inquirer cannot be null");
        }
        if (subjectPerson == null) {
            throw new IllegalArgumentException("Subject person cannot be null");
        }
        if (details == null) {
            throw new IllegalArgumentException("Details cannot be null");
        }
        this.inquirer = inquirer;
        this.subjectPerson = subjectPerson;
        this.inquiryDate = LocalDateTime.now();
        this.details = details;
    }

    /**
     * Returns the database ID of this inquiry.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database ID of this inquiry.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the person who made the inquiry.
     *
     * @return the inquirer
     */
    public Person getInquirer() {
        return inquirer;
    }

    /**
     * Returns the person being searched for.
     *
     * @return the subject person
     */
    public Person getSubjectPerson() {
        return subjectPerson;
    }

    /**
     * Returns the date and time the inquiry was created.
     *
     * @return the inquiry date and time
     */
    public LocalDateTime getInquiryDate() {
        return inquiryDate;
    }

    /**
     * Returns the details provided with the inquiry.
     *
     * @return the details string
     */
    public String getDetails() {
        return details;
    }

    /**
     * Updates the details of this inquiry.
     *
     * @param details the new details to set
     * @throws IllegalArgumentException if details is null
     */
    public void setDetails(String details) throws IllegalArgumentException {
        if (details == null) {
            throw new IllegalArgumentException("Details cannot be null");
        }
        this.details = details;
    }
}
