package main.java.expression.unary;


import main.java.expression.exceptions.MathException;
import main.java.expression.TripleExpression;
import main.java.expression.operations.Operation;

public abstract class UnaryOperation<T> implements TripleExpression<T> {
    protected final TripleExpression<T> term;
    protected final Operation<T> operation;

    protected UnaryOperation(TripleExpression<T> term, Operation<T> operation) {
        this.term = term;
        this.operation = operation;
    }


    protected abstract T apply(final T x) throws MathException;

    public T evaluate(final T x, final T y, final T z) throws MathException {
        return apply(term.evaluate(x, y, z));
    }

    public String toString() {
        return toStringImpl() + term.toString();
    }

    protected abstract String toStringImpl();
}