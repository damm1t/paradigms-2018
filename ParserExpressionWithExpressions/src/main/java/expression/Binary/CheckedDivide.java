package main.java.expression.Binary;


import main.java.expression.Exceptions.DivByZeroException;
import main.java.expression.Exceptions.MathException;
import main.java.expression.Exceptions.OverflowException;
import main.java.expression.TripleExpression;

public class CheckedDivide extends BinaryOperation {

    public CheckedDivide(TripleExpression firstTerm, TripleExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected int apply(int x, int y) throws MathException {
        if(y == 0){
            throw new DivByZeroException();
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    protected String toStringImpl() {
        return " / ";
    }
}
