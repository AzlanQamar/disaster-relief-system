package edu.ucalgary.oop;

/**
 * Thrown when an age-related rule is violated, such as attempting to set both a
 * date of birth and an approximate age on the same disaster victim.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class InvalidAgeException extends Exception {

    /**
     * Constructs an InvalidAgeException with the specified message.
     *
     * @param message a description of the age rule that was violated
     */
    public InvalidAgeException(String message) {
        super(message);
    }
}
