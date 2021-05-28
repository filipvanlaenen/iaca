package net.filipvanlaenen.iacaj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests on the <code>BooleanXorCalculation</code> class.
 */
public class BooleanXorCalculationTest {
    /**
     * Verifies that two trues are resolved to false.
     */
    @Test
    public void shouldResolveTwoTruesToFalse() {
        BooleanXorCalculation calculation = new BooleanXorCalculation("v1 ⊻ v2");
        BooleanFunction booleanFunction = BooleanFunction.parse("v1 = True", "v2 = True");
        BooleanRightHandSide resolved = calculation.resolve(booleanFunction);
        assertEquals(BooleanConstant.FALSE, resolved);
    }

    /**
     * Verifies that two falses are resolved to false.
     */
    @Test
    public void shouldResolveTwoFalsesToFalse() {
        BooleanXorCalculation calculation = new BooleanXorCalculation("v1 ⊻ v2");
        BooleanFunction booleanFunction = BooleanFunction.parse("v1 = False", "v2 = False");
        BooleanRightHandSide resolved = calculation.resolve(booleanFunction);
        assertEquals(BooleanConstant.FALSE, resolved);
    }

    /**
     * Verifies that true and false are resolved to true.
     */
    @Test
    public void shouldResolveTrueAndFalseToTrue() {
        BooleanXorCalculation calculation = new BooleanXorCalculation("v1 ⊻ v2");
        BooleanFunction booleanFunction = BooleanFunction.parse("v1 = False", "v2 = True");
        BooleanRightHandSide resolved = calculation.resolve(booleanFunction);
        assertEquals(BooleanConstant.TRUE, resolved);
    }

    /**
     * Verifies that xor with false is resolved to equality.
     */
    @Test
    public void shouldResolveXorWithFalseToEquality() {
        BooleanXorCalculation calculation = new BooleanXorCalculation("v1 ⊻ v2");
        BooleanFunction booleanFunction = BooleanFunction.parse("v1 = False");
        BooleanRightHandSide resolved = calculation.resolve(booleanFunction);
        assertEquals(new BooleanEquation("v2"), resolved);
    }

    /**
     * Verifies that xor with true is resolved to negation.
     */
    @Test
    public void shouldResolveXorWithTrueToNegation() {
        BooleanXorCalculation calculation = new BooleanXorCalculation("v1 ⊻ v2");
        BooleanFunction booleanFunction = BooleanFunction.parse("v1 = True");
        BooleanRightHandSide resolved = calculation.resolve(booleanFunction);
        assertEquals(new BooleanEquation("v2", true), resolved);
    }

    /**
     * Verifies that xor with not false is resolved to equality.
     */
    @Test
    public void shouldResolveXorWithNotFalseToNegation() {
        BooleanXorCalculation calculation = new BooleanXorCalculation("¬v1 ⊻ v2");
        BooleanFunction booleanFunction = BooleanFunction.parse("v1 = False");
        BooleanRightHandSide resolved = calculation.resolve(booleanFunction);
        assertEquals(new BooleanEquation("v2", true), resolved);
    }

    /**
     * Verifies that xor with not true is resolved to equality.
     */
    @Test
    public void shouldResolveXorWithNotTrueToEquality() {
        BooleanXorCalculation calculation = new BooleanXorCalculation("¬v1 ⊻ v2");
        BooleanFunction booleanFunction = BooleanFunction.parse("v1 = True");
        BooleanRightHandSide resolved = calculation.resolve(booleanFunction);
        assertEquals(new BooleanEquation("v2"), resolved);
    }
}
