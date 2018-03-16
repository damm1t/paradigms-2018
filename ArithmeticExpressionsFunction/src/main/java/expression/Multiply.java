package expression;

public class Multiply extends BinaryOperation {

    protected Multiply(SuperExpression firstTerm, SuperExpression secondTerm) {
        super(firstTerm, secondTerm, (a, b) -> (a * b), (a, b) -> (a * b));
    }

    @Override
    protected String toStringImpl() {
        return " * ";
    }
}
