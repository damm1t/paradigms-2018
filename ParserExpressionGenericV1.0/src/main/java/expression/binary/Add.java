package main.java.expression.binary;

import main.java.expression.exceptions.OverflowException;
import main.java.expression.TripleExpression;
import main.java.expression.operations.Operation;

public class Add<T> extends BinaryOperation<T> {
    public Add(TripleExpression<T> firstTerm, TripleExpression<T> secondTerm, Operation<T> op) {
        super(firstTerm, secondTerm, op);
    }

    @Override
    protected T apply(T x, T y) throws OverflowException {
        return operation.add(x, y);
    }

    protected String toStringImpl() {
        return " + ";
    }
}
