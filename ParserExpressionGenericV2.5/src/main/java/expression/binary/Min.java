package main.java.expression.binary;

import main.java.expression.IExpression;
import main.java.expression.Visitors.Visitor;
import main.java.expression.exceptions.MathException;

public class Min<T> extends BinaryOperation<T> {
    public Min(IExpression<T> firstTerm, IExpression<T> secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    public <R, C> R evaluate(Visitor<T, R, C> visitor, C map) throws MathException {
        return visitor.visitMin(this, map);
    }

    protected String toStringImpl() {
        return " + ";
    }
}
