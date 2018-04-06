package main.java.expression.Unary;

import main.java.expression.TripleExpression;

public class Count extends UnaryOperation {

    public Count(final TripleExpression x) {
        super(x);
    }

    @Override
    protected int apply(int x) {
        return Integer.bitCount(x);
    }

    @Override
    protected String toStringImpl() {
        return " count ";
    }
}
