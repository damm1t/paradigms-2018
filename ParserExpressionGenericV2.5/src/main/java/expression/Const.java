package main.java.expression;

import main.java.expression.exceptions.MathException;
import main.java.expression.Visitors.Visitor;

public class Const<T> implements IExpression<T> {
    public final String value;

    public Const(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public <R, C> R evaluate(Visitor<T, R, C> visitor, C map) throws MathException {
        return visitor.visitConst(this, map);
    }
}
