package edu.ucalgary.oop;

/**
 * The InvalidAgeException is a custom exception that is thrown when an invalid
 * age is provided for a disaster victim. This exception can be used to enforce
 * age-related constraints in the application, such as ensuring that the age of
 * a victim is within a reasonable range.
 */
public class InvalidAgeException extends Exception {

    public InvalidAgeException(String message) {
        super(message);
    }

}
