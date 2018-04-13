package main.java.expression.binary;

import main.java.expression.IExpression;
import main.java.expression.exceptions.MathException;
import main.java.expression.Visitors.Visitor;

public class Add<T> extends BinaryOperation<T> {
    public Add(IExpression<T> firstTerm, IExpression<T> secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    public <R, C> R evaluate(Visitor<T, R, C> visitor, C map) throws MathException {
        return visitor.visitAdd(this, map);
    }

    protected String toStringImpl() {
        return " + ";
    }
}
