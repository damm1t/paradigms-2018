package expression;

import java.util.function.BiFunction;

public abstract class BinaryOperation implements SuperExpression {
    protected final SuperExpression firstTerm, secondTerm;
    protected final BiFunction<Integer, Integer, Integer> function;
    protected final BiFunction<Double, Double, Double> doubleFunction;

    protected BinaryOperation(SuperExpression firstTerm, SuperExpression secondTerm,
                              BiFunction<Integer, Integer, Integer> function,
                              BiFunction<Double, Double, Double> doubleFunction) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
        this.function = function;
        this.doubleFunction = doubleFunction;
    }

    public int evaluate(int x, int y, int z) {
        return function.apply(firstTerm.evaluate(x, y, z), secondTerm.evaluate(x, y, z));
    }

    public int evaluate(int x) {
        return function.apply(firstTerm.evaluate(x), secondTerm.evaluate(x));
    }

    public double evaluate(double x) {
        return doubleFunction.apply(firstTerm.evaluate(x), secondTerm.evaluate(x));
    }

    public String toString() {
        return "(" + firstTerm.toString() + toStringImpl() + secondTerm.toString() + ")";
    }

    protected abstract String toStringImpl();
}
