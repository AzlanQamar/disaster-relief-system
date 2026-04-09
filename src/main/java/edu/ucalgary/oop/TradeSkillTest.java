package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the TradeSkill class.
 *
 * @author Azlan
 * @version 1.0
 * @since 2026-03-01
 */
public class TradeSkillTest {

    private Skill skill;
    private TradeSkill tradeSkill;

    @Before
    public void setUp() {
        skill = new Skill("carpentry", "trade");
        tradeSkill = new TradeSkill(skill, "advanced", "carpentry");
    }

    @Test
    public void testConstructor_validInputs_setsTradeType() {
        assertEquals("Trade type should be carpentry", "carpentry", tradeSkill.getTradeType());
    }

    @Test
    public void testConstructor_validInputs_setsProficiencyLevel() {
        assertEquals("Proficiency level should be advanced", "advanced", tradeSkill.getProficiencyLevel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_invalidTradeType_throwsException() {
        new TradeSkill(skill, "beginner", "cooking");
    }

    @Test
    public void testConstructor_validTradeTypePlumbing_setsSuccessfully() {
        TradeSkill ts = new TradeSkill(skill, "intermediate", "plumbing");
        assertEquals("Trade type should be plumbing", "plumbing", ts.getTradeType());
    }

    @Test
    public void testConstructor_validTradeTypeElectricity_setsSuccessfully() {
        TradeSkill ts = new TradeSkill(skill, "beginner", "electricity");
        assertEquals("Trade type should be electricity", "electricity", ts.getTradeType());
    }

    @Test
    public void testSetTradeType_validType_updatesType() {
        tradeSkill.setTradeType("plumbing");
        assertEquals("Trade type should be updated to plumbing", "plumbing", tradeSkill.getTradeType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTradeType_invalidType_throwsException() {
        tradeSkill.setTradeType("welding");
    }
}
