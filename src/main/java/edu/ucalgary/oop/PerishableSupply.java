package edu.ucalgary.oop;

import java.time.LocalDate;

/**
 * Represents a perishable supply item that has an associated expiry date.
 * Extends the Supply class by adding functionality to track and determine
 * whether the item has expired.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class PerishableSupply extends Supply {

    private LocalDate expiryDate;

    /**
     * Constructs a PerishableSupply with the specified type and expiry date.
     *
     * @param type the type of supply
     * @param expiryDate the expiry date of the supply
     * @throws IllegalArgumentException if expiryDate is null
     */
    public PerishableSupply(String type, LocalDate expiryDate) {
        super(type);
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null");
        }
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the expiry date of this supply.
     *
     * @return the expiry date
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of this supply.
     *
     * @param expiryDate the expiry date to set
     * @throws IllegalArgumentException if expiryDate is null
     */
    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null");
        }
        this.expiryDate = expiryDate;
    }

    /**
     * Determines whether this supply item has expired based on the current
     * date.
     *
     * @return true if the current date is after the expiry date, false
     * otherwise
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

}
