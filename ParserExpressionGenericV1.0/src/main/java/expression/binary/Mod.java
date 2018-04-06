package main.java.expression.binary;


import main.java.expression.TripleExpression;
import main.java.expression.exceptions.MathException;
import main.java.expression.operations.Operation;

public class Mod<T> extends BinaryOperation<T>{
    public Mod(TripleExpression<T> firstTerm, TripleExpression<T> secondTerm, Operation<T> op) {
        super(firstTerm, secondTerm, op);
    }

    @Override
    protected T apply(T x, T y) throws MathException {
        return operation.mod(x, y);
    }

    @Override
    protected String toStringImpl() {
        return " mod ";
    }
}
