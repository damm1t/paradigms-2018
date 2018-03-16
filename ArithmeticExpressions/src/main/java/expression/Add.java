package expression;

public class Add extends BinaryOperation {

    public Add(SuperExpression firstTerm, SuperExpression secondTerm) {
        super(firstTerm, secondTerm);
    }

    @Override
    protected double apply(double x, double y) {
        return x + y;
    }

    @Override
    protected int apply(int x, int y) {
        return x + y;
    }

    protected String toStringImpl(){
        return " + ";
    }
}
