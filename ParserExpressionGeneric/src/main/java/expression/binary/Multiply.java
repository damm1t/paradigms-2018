package main.java.expression.binary;


import main.java.expression.exceptions.MathException;
import main.java.expression.TripleExpression;
import main.java.expression.operations.Operation;

public class Multiply<T> extends BinaryOperation<T> {

    public Multiply(TripleExpression<T> firstTerm, TripleExpression<T> secondTerm, Operation<T> op) {
        super(firstTerm, secondTerm, op);
    }

    @Override
    protected T apply(T x, T y) throws MathException {
        return operation.multiply(x, y);
    }

    protected String toStringImpl() {
        return " * ";
    }
}
