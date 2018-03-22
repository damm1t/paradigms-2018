package expression.Binary;

import expression.TripleExpression;

public abstract class BinaryOperation implements TripleExpression {
    private final TripleExpression firstTerm, secondTerm;

    protected BinaryOperation(TripleExpression firstTerm, TripleExpression secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    protected abstract int apply(int x, int y);

    public int evaluate(int x, int y, int z) {
        return apply(firstTerm.evaluate(x, y, z), secondTerm.evaluate(x, y, z));
    }

    public String toString() {
        return "(" + firstTerm.toString() + toStringImpl() + secondTerm.toString() + ")";
    }

    protected abstract String toStringImpl();
}
