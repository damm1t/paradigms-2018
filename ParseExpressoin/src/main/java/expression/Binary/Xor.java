package expression.Binary;

import expression.TripleExpression;

public class Xor extends BinaryOperation{
    public Xor(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected int apply(final int x, final int y) {
        return x ^ y;
    }

    @Override
    protected String toStringImpl() {
        return " ^ ";
    }
}