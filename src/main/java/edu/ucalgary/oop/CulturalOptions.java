package edu.ucalgary.oop;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * Serializable class holding available cultural requirement options. Loaded
 * from available_requirements.ser at startup.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class CulturalOptions implements Serializable {

    private static final long serialVersionUID = 1L;
    private HashMap<String, Set<String>> accommodations;

    /**
     * Returns the map of available cultural requirement categories and their
     * options.
     *
     * @return a HashMap where keys are category names and values are sets of
     * valid options
     */
    public HashMap<String, Set<String>> getAccommodations() {
        return accommodations;
    }
}
