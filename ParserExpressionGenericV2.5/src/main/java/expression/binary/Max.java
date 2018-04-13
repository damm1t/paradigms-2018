package main.java.expression.binary;

import main.java.expression.IExpression;
import main.java.expression.Visitors.Visitor;
import main.java.expression.exceptions.MathException;

public class Max<T> extends BinaryOperation<T> {
    public Max(IExpression<T> firstTerm, IExpression<T> secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    public <R, C> R evaluate(Visitor<T, R, C> visitor, C map) throws MathException {
        return visitor.visitMax(this, map);
    }

    protected String toStringImpl() {
        return " + ";
    }
}
