package expression.Binary;

import expression.TripleExpression;

public class RightShift extends BinaryOperation {
    public RightShift(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected int apply(final int x, final int y) {
        return x >> y;

    }

    @Override
    protected String toStringImpl() {
        return " >> ";
    }
}