package edu.ucalgary.oop;

/**
 * Represents a language skill held by a disaster victim. Extends VictimSkill
 * with a language name and capability flags for reading/writing and
 * speaking/listening.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class LanguageSkill extends VictimSkill {

    private String languageName;
    private boolean readWrite;
    private boolean speakListen;

    /**
     * Constructs a LanguageSkill with the given skill, proficiency level,
     * language name, and capability flags.
     *
     * @param skill the Skill object representing the language
     * @param proficiencyLevel the proficiency level
     * (beginner/intermediate/advanced)
     * @param languageName the name of the language
     * @param readWrite true if the victim can read and write in this language
     * @param speakListen true if the victim can speak and listen in this
     * language
     * @throws IllegalArgumentException if languageName is null or empty
     */
    public LanguageSkill(Skill skill, String proficiencyLevel, String languageName,
            boolean readWrite, boolean speakListen) throws IllegalArgumentException {
        super(skill, proficiencyLevel);
        if (languageName == null || languageName.trim().isEmpty()) {
            throw new IllegalArgumentException("Language name cannot be null or empty");
        }
        this.languageName = languageName;
        this.readWrite = readWrite;
        this.speakListen = speakListen;
    }

    /**
     * Returns the name of the language.
     *
     * @return the language name
     */
    public String getLanguageName() {
        return languageName;
    }

    /**
     * Sets the name of the language.
     *
     * @param languageName the language name to set
     * @throws IllegalArgumentException if languageName is null or empty
     */
    public void setLanguageName(String languageName) throws IllegalArgumentException {
        if (languageName == null || languageName.trim().isEmpty()) {
            throw new IllegalArgumentException("Language name cannot be null or empty");
        }
        this.languageName = languageName;
    }

    /**
     * Returns whether the victim can read and write in this language.
     *
     * @return true if the victim has read/write capability
     */
    public boolean hasReadWrite() {
        return readWrite;
    }

    /**
     * Sets whether the victim can read and write in this language.
     *
     * @param readWrite true if the victim has read/write capability
     */
    public void setReadWrite(boolean readWrite) {
        this.readWrite = readWrite;
    }

    /**
     * Returns whether the victim can speak and listen in this language.
     *
     * @return true if the victim has speak/listen capability
     */
    public boolean hasSpeakListen() {
        return speakListen;
    }

    /**
     * Sets whether the victim can speak and listen in this language.
     *
     * @param speakListen true if the victim has speak/listen capability
     */
    public void setSpeakListen(boolean speakListen) {
        this.speakListen = speakListen;
    }
}
