package expression;

public class Multiply extends BinaryOperation {

    public Multiply(SuperExpression firstTerm, SuperExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected double apply(double x, double y) {
        return x * y;
    }

    @Override
    protected int apply(int x, int y) {
        return x * y;
    }

    @Override
    protected String toStringImpl() {
        return " * ";
    }
}
