package main.java.expression;

import main.java.expression.exceptions.MathException;

public class Const<T> implements TripleExpression<T> {
    private final T value;

    public Const(T value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public T evaluate(T x, T y, T z) throws MathException {
        return value;
    }
}
