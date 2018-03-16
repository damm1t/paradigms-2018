package expression;

public class Divide extends BinaryOperation {


    protected Divide(SuperExpression firstTerm, SuperExpression secondTerm) {

        super(firstTerm, secondTerm, (a, b) -> {
            //assert (b != 0) : "Incorrect Expresion";
            return a / b;
        }, (a, b) -> {
            //assert (b != 0) : "Incorrect Expresion";
            return a / b;
        });
    }

    @Override
    protected String toStringImpl() {
        return " / ";
    }
}
