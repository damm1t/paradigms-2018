package expression;

public class Subtract extends BinaryOperation {
    protected Subtract(SuperExpression firstTerm, SuperExpression secondTerm) {
        super(firstTerm, secondTerm, (a, b) -> (a - b), (a, b) -> (a - b));
    }

    @Override
    protected String toStringImpl() {
        return " - ";
    }
}
