package main.java.expression.Unary;

import main.java.expression.Exceptions.LogException;
import main.java.expression.Exceptions.MathException;
import main.java.expression.Exceptions.OverflowException;
import main.java.expression.Exceptions.PowException;
import main.java.expression.TripleExpression;

public class Pow10 extends UnaryOperation {
    public Pow10(final TripleExpression x) {
        super(x);
    }

    @Override
    protected int apply(int x) throws MathException {
        int res = 1;
        if (x < 0) {
            throw new PowException();
        }
        var i = 0;
        for(; i < x && res <= Integer.MAX_VALUE / 10; ++i){
            res *= 10;
        }
        if (i < x){
            throw new OverflowException();
        }
        return res;
    }

    @Override
    protected String toStringImpl() {
        return " pow10 ";
    }
}
