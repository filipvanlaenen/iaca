package net.filipvanlaenen.iacaj;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an And calculation.
 */
public final class BooleanAndCalculation extends BooleanCalculation {
    /**
     * Constructor taking the right hand side string as parameter.
     *
     * @param rightHandSideString A right hand side string representing the Xor
     *                            calculation.
     */
    public BooleanAndCalculation(final String rightHandSideString) {
        super(rightHandSideString);
    }

    /**
     * Constructor taking a list of operands as the parameter.
     *
     * @param operands The list of operands.
     */
    public BooleanAndCalculation(final List<BooleanOperand> operands) {
        super(operands);
    }

    @Override
    protected BooleanAndCalculation deepClone() {
        return new BooleanAndCalculation(getOperands());
    }

    @Override
    protected BooleanOperator getOperator() {
        return BooleanOperator.And;
    }

    @Override
    protected BooleanRightHandSide resolve(final BooleanFunction booleanFunction) {
        List<BooleanOperand> trueOperands = new ArrayList<BooleanOperand>();
        for (BooleanOperand operand : getOperands()) {
            if (!operand.isNegated()) {
                BooleanExpression be = booleanFunction.getExpression(operand.getName());
                if (be != null) {
                    if (be.isTrue()) {
                        trueOperands.add(operand);
                    } else if (be.isFalse()) {
                        return BooleanConstant.FALSE;
                    }
                }
            }
        }
        removeOperands(trueOperands);
        if (getNumberOfOperands() == 0) {
            return BooleanConstant.TRUE;
        }
        return this;
    }
}