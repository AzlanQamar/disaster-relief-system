package edu.ucalgary.oop;

import java.time.LocalDateTime;

/**
 * The Inquiry class represents an inquiry made by a person about another person.
 * It contains information about the inquirer, the subject of the inquiry, the
 * date of the inquiry, and details about the inquiry.
 */

public class Inquiry {

    private int id;
    private Person inquirer;
    private Person subjectPerson;
    private LocalDateTime inquiryDate;
    private String details;

    public Inquiry(Person inquirer, Person subjectPerson, String details) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getInquirer() {
        return inquirer;
    }


    public Person getSubjectPerson() {
        return subjectPerson;
    }


    public LocalDateTime getInquiryDate() {
        return inquiryDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        if (details == null) {
            throw new IllegalArgumentException("Details cannot be null");
        }
        this.details = details;
    }


}
