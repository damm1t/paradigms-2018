package main.java.expression.Exceptions;

public class DivByZeroException extends MathException {
    public DivByZeroException() {
        super("division by zero");
    }

}
