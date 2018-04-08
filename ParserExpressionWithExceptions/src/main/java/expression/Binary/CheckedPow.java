package main.java.expression.Binary;

import main.java.expression.Const;
import main.java.expression.Exceptions.MathException;
import main.java.expression.Exceptions.OverflowException;
import main.java.expression.Exceptions.PowException;
import main.java.expression.TripleExpression;

public class CheckedPow extends BinaryOperation {
    public CheckedPow(TripleExpression firstTerm, TripleExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected int apply(int x, int y) throws Exception {
        if (x == 0 && y == 0) {
            throw new PowException();
        }
        if (x != 1 && x != -1 && y < 0) {
            throw new PowException();
        }
        if (y < 0) {
            throw new PowException();
        }
        var exp = 0;
        var res = 1;
        while (exp != y) {
            if (exp == 0) {
                res = new CheckedMultiply(new Const(x), new Const(res)).evaluate(0, 0, 0);
                exp++;
            } else if (exp <= y / 2) {
                res = new CheckedMultiply(new Const(res), new Const(res)).evaluate(0, 0, 0);
                exp *= 2;
            } else {
                res = new CheckedMultiply(new Const(x), new Const(res)).evaluate(0, 0, 0);
                exp++;
            }
        }
        return res;
    }

    @Override
    protected String toStringImpl() {
        return " ** ";
    }
}
