package expression.Unary;

import expression.TripleExpression;

public class Abs extends UnaryOperation {

    public Abs(final TripleExpression x) {
        super(x);
    }

    protected int apply(final int x) {
        return Math.abs(x);
    }

    @Override
    protected String toStringImpl() {
        return "abs ";
    }
}