package main.java.expression.Binary;


import main.java.expression.Exceptions.OverflowException;
import main.java.expression.TripleExpression;

public class CheckedSubtract extends BinaryOperation {
    public CheckedSubtract(TripleExpression firstTerm, TripleExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected int apply(int x, int y) throws OverflowException {
        if ((x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) ||
                (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y)) {
            throw new OverflowException();
        }
        return x - y;
    }

    @Override
    protected String toStringImpl() {
        return " - ";
    }
}
