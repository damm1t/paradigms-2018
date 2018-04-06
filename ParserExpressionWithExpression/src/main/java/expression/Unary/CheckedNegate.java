package main.java.expression.Unary;


import main.java.expression.Exceptions.OverflowException;
import main.java.expression.TripleExpression;

public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(final TripleExpression x) {
        super(x);
    }

    protected int apply(final int x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }

    @Override
    protected String toStringImpl() {
        return " -";
    }
}
