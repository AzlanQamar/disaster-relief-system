package edu.ucalgary.oop;

/**
 * Represents a person who makes an inquiry about a missing disaster victim.
 * Extends Person with immutable info and services phone number fields.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class Inquirer extends Person {

    private final String INFO;
    private final String SERVICES_PHONE;

    /**
     * Constructs an Inquirer with the given personal details.
     *
     * @param firstName the inquirer's first name
     * @param lastName the inquirer's last name
     * @param info additional information about the inquirer
     * @param phone the inquirer's services phone number
     * @throws IllegalArgumentException if info or phone is null
     */
    public Inquirer(String firstName, String lastName, String info, String phone) throws IllegalArgumentException {
        super(firstName, lastName);
        if (info == null) {
            throw new IllegalArgumentException("Info cannot be null");
        }
        if (phone == null) {
            throw new IllegalArgumentException("Services phone cannot be null");
        }
        this.INFO = info;
        this.SERVICES_PHONE = phone;
    }

    /**
     * Returns additional information about the inquirer.
     *
     * @return the info string
     */
    public String getInfo() {
        return INFO;
    }

    /**
     * Returns the inquirer's services phone number.
     *
     * @return the services phone number
     */
    public String getServicesPhoneNum() {
        return SERVICES_PHONE;
    }
}
