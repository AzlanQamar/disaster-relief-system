package edu.ucalgary.oop;

import java.util.Arrays;

/**
 * Represents a trade-related skill associated with a disaster victim. Extends
 * VictimSkill by adding a validated trade type selected from a predefined set
 * of allowed values.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class TradeSkill extends VictimSkill {

    public static final String[] VALID_TRADE_TYPES = {"carpentry", "plumbing", "electricity"};

    private String tradeType;

    /**
     * Constructs a TradeSkill with the specified skill, proficiency level, and
     * trade type.
     *
     * @param skill the skill associated with this record
     * @param proficiencyLevel the proficiency level of the skill
     * @param tradeType the type of trade
     * @throws IllegalArgumentException if tradeType is not valid
     */
    public TradeSkill(Skill skill, String proficiencyLevel, String tradeType) throws IllegalArgumentException {
        super(skill, proficiencyLevel);
        setTradeType(tradeType);
    }

    /**
     * Returns the trade type for this skill.
     *
     * @return the trade type
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * Sets the trade type for this skill.
     *
     * @param tradeType the trade type to set
     * @throws IllegalArgumentException if tradeType is not one of the valid
     * types
     */
    public void setTradeType(String tradeType) throws IllegalArgumentException {
        if (!Arrays.asList(VALID_TRADE_TYPES).contains(tradeType)) {
            throw new IllegalArgumentException("Invalid trade type. Must be one of: "
                    + Arrays.toString(VALID_TRADE_TYPES));
        }
        this.tradeType = tradeType;
    }
}
