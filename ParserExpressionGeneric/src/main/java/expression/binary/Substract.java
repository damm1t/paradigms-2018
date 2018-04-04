package main.java.expression.binary;


import main.java.expression.exceptions.MathException;
import main.java.expression.TripleExpression;
import main.java.expression.operations.Operation;

public class Substract<T> extends BinaryOperation<T> {
    public Substract(TripleExpression<T> firstTerm, TripleExpression<T> secondTerm, Operation<T> op) {
        super(firstTerm, secondTerm, op);
    }

    @Override
    protected T apply(T x, T y) throws MathException {
        return operation.substract(x, y);
    }

    @Override
    protected String toStringImpl() {
        return " - ";
    }
}
