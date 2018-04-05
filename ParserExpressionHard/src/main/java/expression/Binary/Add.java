package main.java.expression.Binary;

import main.java.expression.TripleExpression;

public class Add extends BinaryOperation {

    public Add(TripleExpression firstTerm, TripleExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected int apply(int x, int y) {
        return x + y;
    }

    protected String toStringImpl(){
        return " + ";
    }
}
