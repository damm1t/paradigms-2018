package main.java.expression.Unary;


import main.java.expression.Exceptions.MathException;
import main.java.expression.TripleExpression;

public abstract class UnaryOperation implements TripleExpression {
    protected final TripleExpression term;

    protected UnaryOperation(final TripleExpression x) {
        term = x;
    }

    protected abstract int apply(final int x) throws MathException;

    public int evaluate(final int x, final int y, final int z) throws Exception {
        return apply(term.evaluate(x, y, z));
    }

    public String toString() {
        return toStringImpl() + term.toString();
    }

    protected abstract String toStringImpl();
}