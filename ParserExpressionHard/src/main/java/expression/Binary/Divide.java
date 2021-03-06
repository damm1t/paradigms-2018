package main.java.expression.Binary;


import main.java.expression.TripleExpression;

public class Divide extends BinaryOperation {

    public Divide(TripleExpression firstTerm, TripleExpression secondTerm) {

        super(firstTerm, secondTerm);
    }

    @Override
    protected int apply(int x, int y) {
        return x / y;
    }

    @Override
    protected String toStringImpl() {
        return " / ";
    }
}
