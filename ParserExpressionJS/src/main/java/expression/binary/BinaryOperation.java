package main.java.expression.binary;


import main.java.expression.IExpression;
import main.java.expression.exceptions.MathException;
import main.java.expression.Visitors.Visitor;

import java.util.HashMap;

public abstract class BinaryOperation<T> implements IExpression<T> {
    public final IExpression<T> firstTerm, secondTerm;

    protected BinaryOperation(IExpression<T> firstTerm, IExpression<T> secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    public abstract <R, C> R evaluate(Visitor<T, R, C> visitor, C map) throws MathException;

    public String toString() {
        return "(" + firstTerm.toString() + toStringImpl() + secondTerm.toString() + ")";
    }

    protected abstract String toStringImpl();
}
