package main.java.expression.Unary;

import main.java.expression.Exceptions.LogException;
import main.java.expression.Exceptions.MathException;
import main.java.expression.TripleExpression;

public class Log10 extends UnaryOperation {
    public Log10(final TripleExpression x) {
        super(x);
    }

    @Override
    protected int apply(int x) throws MathException {
        int st = 1, res = 0;
        if (x <= 0) {
            throw new LogException();
        }
        if (x < 10) {
            return 0;
        }
        while (st <= x / 10 && st <= Integer.MAX_VALUE / 10) {
            st *= 10;
            res++;
        }
        return res;
    }

    @Override
    protected String toStringImpl() {
        return " log10 ";
    }
}
