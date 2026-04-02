package edu.ucalgary.oop;

/**
 * The Inquirer class represents a person who inquires about disaster relief
 * services. It extends the Person class and adds additional attributes for
 * information and services phone number.
 */
public class Inquirer extends Person {

    private final String INFO;
    private final String SERVICES_PHONE;

    public Inquirer(String firstName, String lastName, String info, String phone) {
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

    public String getInfo() {
        return INFO;
    }

    public String getServicesPhoneNum() {
        return SERVICES_PHONE;
    }

}
