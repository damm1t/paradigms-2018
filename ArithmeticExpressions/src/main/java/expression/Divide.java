package expression;

public class Divide extends BinaryOperation {


    public Divide(SuperExpression firstTerm, SuperExpression secondTerm) {

        super(firstTerm, secondTerm);
    }

    @Override
    protected int apply(int x, int y) {
        return x / y;
    }

    @Override
    protected double apply(double x, double y) {
        return x / y;
    }

    @Override
    protected String toStringImpl() {
        return " / ";
    }
}
