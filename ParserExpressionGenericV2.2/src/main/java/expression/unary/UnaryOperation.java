package main.java.expression.unary;


import main.java.expression.exceptions.MathException;
import main.java.expression.IExpression;
import main.java.expression.Visitors.Visitor;

import java.util.HashMap;

public abstract class UnaryOperation<T> implements IExpression<T> {
    public final IExpression<T> term;

    protected UnaryOperation(IExpression<T> term) {
        this.term = term;
    }

    public abstract <R, C> R evaluate(Visitor<T, R, C> visitor, C map) throws MathException;

    public String toString() {
        return toStringImpl() + term.toString();
    }

    protected abstract String toStringImpl();
}