package main.java.expression.unary;


import main.java.expression.IExpression;
import main.java.expression.exceptions.MathException;
import main.java.expression.Visitors.Visitor;

public class Negative<T> extends UnaryOperation<T> {

    public Negative(IExpression<T> term) {
        super(term);
    }

    @Override
    public <R, C> R evaluate(Visitor<T, R, C> visitor, C map) throws MathException {
        return visitor.visitNeg(this, map);
    }

    @Override
    protected String toStringImpl() {
        return " -";
    }
}
