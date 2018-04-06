package main.java.expression;

import main.java.expression.exceptions.MathException;
import main.java.expression.Visitors.Visitor;

public class Variable<T> implements IExpression<T> {
    public final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override
    public <R, C> R evaluate(Visitor<T, R, C> visitor, C map) throws MathException {
        return visitor.visitVariable(this, map);
    }
}
