package edu.ucalgary.oop;

import java.time.LocalDate;

/**
 * The PerishableSupply class represents a supply item that has an expiry date.
 * It extends the Supply class and adds an additional attribute for the expiry
 * date, as well as methods to check if the supply is expired.
 */
public class PerishableSupply extends Supply {

    private LocalDate expiryDate;

    public PerishableSupply(String type, LocalDate expiryDate) {
        super(type);
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null");
        }
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null");
        }
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

}
