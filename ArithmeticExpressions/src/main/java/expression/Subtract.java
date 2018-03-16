package expression;

public class Subtract extends BinaryOperation {
    public Subtract(SuperExpression firstTerm, SuperExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected double apply(double x, double y) {
        return x - y;
    }

    @Override
    protected int apply(int x, int y) {
        return x - y;
    }

    @Override
    protected String toStringImpl() {
        return " - ";
    }
}
