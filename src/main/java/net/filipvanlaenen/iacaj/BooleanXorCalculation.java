package net.filipvanlaenen.iacaj;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an Xor calculation.
 */
public final class BooleanXorCalculation extends BooleanCalculation {
    /**
     * Constructor taking the right hand side string as parameter.
     *
     * @param rightHandSideString A right hand side string representing the Xor
     *                            calculation.
     */
    public BooleanXorCalculation(final String rightHandSideString) {
        super(rightHandSideString);
    }

    /**
     * Constructor taking a list of operands as the parameter.
     *
     * @param operands The list of operands.
     */
    public BooleanXorCalculation(final List<BooleanOperand> operands) {
        super(operands);
    }

    @Override
    protected BooleanXorCalculation deepClone() {
        return new BooleanXorCalculation(getOperands());
    }

    @Override
    protected BooleanOperator getOperator() {
        return BooleanOperator.Xor;
    }

    @Override
    protected BooleanRightHandSide resolve(final BooleanFunction booleanFunction) {
        List<BooleanOperand> falseOperands = new ArrayList<BooleanOperand>();
        List<BooleanOperand> trueOperands = new ArrayList<BooleanOperand>();
        for (BooleanOperand operand : getOperands()) {
            BooleanExpression be = booleanFunction.getExpression(operand.getName());
            if (be != null) {
                if (be.isTrue() && !operand.isNegated() || be.isFalse() && operand.isNegated()) {
                    trueOperands.add(operand);
                } else if (be.isFalse() && !operand.isNegated() || be.isTrue() && operand.isNegated()) {
                    falseOperands.add(operand);
                }
            }
        }
        removeOperands(falseOperands);
        removeOperands(trueOperands);
        boolean numberOfTrueOperandsIsEven = trueOperands.size() % 2 == 0;
        if (getNumberOfOperands() == 0) {
            return new BooleanConstant(!numberOfTrueOperandsIsEven);
        } else if (!numberOfTrueOperandsIsEven) {
            BooleanOperand firstOperand = getOperands().get(0);
            removeOperand(firstOperand);
            addOperand(firstOperand.negated());
        }
        return this;
    }
}
