package main.java.expression.Unary;

import main.java.expression.TripleExpression;

public class Square extends UnaryOperation {
    public Square(final TripleExpression x) {
        super(x);
    }

    protected int apply(final int x) {
        return x * x;
    }

    @Override
    protected String toStringImpl() {
        return " square ";
    }
}