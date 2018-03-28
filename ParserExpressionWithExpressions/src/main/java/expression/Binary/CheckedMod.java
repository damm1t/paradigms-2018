package main.java.expression.Binary;


import main.java.expression.Exceptions.DivByZeroException;
import main.java.expression.TripleExpression;

public class CheckedMod extends BinaryOperation{
    public CheckedMod(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected int apply(final int x, final int y) throws DivByZeroException {
        if(y == 0){
            throw new DivByZeroException();
        }
        return x % y;
    }

    @Override
    protected String toStringImpl() {
        return " mod ";
    }
}
