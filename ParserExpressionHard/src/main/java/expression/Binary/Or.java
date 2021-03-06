package main.java.expression.Binary;


import main.java.expression.TripleExpression;

public class Or extends BinaryOperation {
    public Or(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected int apply(final int x, final int y) {
        return x | y;
    }

    @Override
    protected String toStringImpl() {
        return " | ";
    }
}