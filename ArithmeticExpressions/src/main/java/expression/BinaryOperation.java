package expression;

import java.util.function.BiFunction;

public abstract class BinaryOperation implements SuperExpression {
    private final SuperExpression firstTerm, secondTerm;

    protected BinaryOperation(SuperExpression firstTerm, SuperExpression secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    protected abstract double apply(double x, double y);

    protected abstract int apply(int x, int y);

    public int evaluate(int x, int y, int z) {
        return apply(firstTerm.evaluate(x, y, z), secondTerm.evaluate(x, y, z));
    }

    public int evaluate(int x) {
        return apply(firstTerm.evaluate(x), secondTerm.evaluate(x));
    }

    public double evaluate(double x) {
        return apply(firstTerm.evaluate(x), secondTerm.evaluate(x));
    }

    public String toString() {
        return "(" + firstTerm.toString() + toStringImpl() + secondTerm.toString() + ")";
    }

    protected abstract String toStringImpl();
}
