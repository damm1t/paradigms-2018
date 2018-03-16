package expression;

public class Add extends BinaryOperation {

    protected Add(SuperExpression firstTerm, SuperExpression secondTerm) {
        super(firstTerm, secondTerm, (a, b) -> (a + b), (a, b) -> (a + b));
    }

    protected String toStringImpl(){
        return " + ";
    }
}
