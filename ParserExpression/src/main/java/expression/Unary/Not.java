package main.java.expression.Unary;


import main.java.expression.TripleExpression;

public class Not extends UnaryOperation {
    public Not(final TripleExpression x) {
        super(x);
    }

    protected int apply(final int x) {
        return -x;
    }

    @Override
    protected String toStringImpl() {
        return "-";
    }
}
