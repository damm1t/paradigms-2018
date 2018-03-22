package expression.Binary;

import expression.TripleExpression;

public class Multiply extends BinaryOperation {

    public Multiply(TripleExpression firstTerm, TripleExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected int apply(int x, int y) {
        return x * y;
    }

    @Override
    protected String toStringImpl() {
        return " * ";
    }
}
