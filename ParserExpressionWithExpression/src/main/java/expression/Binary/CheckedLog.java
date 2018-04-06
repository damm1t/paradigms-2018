package main.java.expression.Binary;

import main.java.expression.Const;
import main.java.expression.Exceptions.LogException;
import main.java.expression.Exceptions.MathException;
import main.java.expression.Exceptions.OverflowException;
import main.java.expression.TripleExpression;

public class CheckedLog extends BinaryOperation {

    public CheckedLog(TripleExpression firstTerm, TripleExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected int apply(int x, int y) throws MathException {
        if (x <= 0) {
            throw new MathException("Log form negative");
        }
        if (y <= 0 || y == 1) {
            throw new MathException("Incorrect log base");
        }
        int l = 0;
        int r = 31;
        while (r - l > 1) {
            int m = (l + r) >> 1;
            try {
                var res = new CheckedPow(new Const(y), new Const(m)).evaluate(0, 0, 0);
                if (res <= x) {
                    l = m;
                } else {
                    r = m;
                }
            } catch (Exception e) {
                r = m;
            }
        }
        return l;
    }

    @Override
    protected String toStringImpl() {
        return " // ";
    }
}
