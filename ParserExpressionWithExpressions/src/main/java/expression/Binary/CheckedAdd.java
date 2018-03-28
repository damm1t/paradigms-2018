package main.java.expression.Binary;

import main.java.expression.Exceptions.OverflowException;
import main.java.expression.TripleExpression;

public class CheckedAdd extends BinaryOperation {

    public CheckedAdd(TripleExpression firstTerm, TripleExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected int apply(int x, int y) throws OverflowException {
        if ((x > 0 && Integer.MAX_VALUE - x < y) ||
                (x < 0 && Integer.MIN_VALUE - x > y)) {
            throw new OverflowException();
        }
        return x + y;
    }

    protected String toStringImpl(){
        return " + ";
    }
}
