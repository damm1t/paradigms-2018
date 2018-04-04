package main.java.expression.binary;


import main.java.expression.exceptions.MathException;
import main.java.expression.TripleExpression;
import main.java.expression.operations.Operation;

public abstract class BinaryOperation<T> implements TripleExpression<T> {
    private final TripleExpression<T> firstTerm, secondTerm;
    protected final Operation<T> operation;
    protected BinaryOperation(TripleExpression<T> firstTerm, TripleExpression<T> secondTerm, Operation<T> op) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
        this.operation = op;
    }

    protected abstract T apply(T x, T y) throws MathException;

    public T evaluate(T x, T y, T z) throws MathException {
        return apply(firstTerm.evaluate(x, y, z), secondTerm.evaluate(x, y, z));
    }

    public String toString() {
        return "(" + firstTerm.toString() + toStringImpl() + secondTerm.toString() + ")";
    }

    protected abstract String toStringImpl();
}
