package main.java.expression.Unary;

import main.java.expression.TripleExpression;

public class BinNot extends UnaryOperation {

    public BinNot(final TripleExpression x) {
        super(x);
    }

    protected int apply(final int x) {
        return ~x;
    }

    @Override
    protected String toStringImpl() {
        return "~";
    }
}

