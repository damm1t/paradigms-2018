package main.java.expression;

import main.java.expression.exceptions.MathException;

public class Variable<T> implements TripleExpression<T> {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override
    public T evaluate(T x, T y, T z) throws MathException {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        assert false : "wrong variable";
        return null;
    }
}
