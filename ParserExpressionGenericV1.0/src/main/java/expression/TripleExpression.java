package main.java.expression;

import main.java.expression.exceptions.MathException;

public interface TripleExpression<T> {
    T evaluate(T x, T y, T z) throws MathException;
}