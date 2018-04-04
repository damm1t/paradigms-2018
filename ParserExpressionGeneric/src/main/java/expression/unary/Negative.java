package main.java.expression.unary;


import main.java.expression.exceptions.MathException;
import main.java.expression.TripleExpression;
import main.java.expression.operations.Operation;

public class Negative<T> extends UnaryOperation<T> {

    public Negative(TripleExpression<T> term, Operation<T> operation) {
        super(term, operation);
    }

    @Override
    protected T apply(T x) throws MathException {
        return operation.negative(x);
    }

    @Override
    protected String toStringImpl() {
        return " -";
    }
}
