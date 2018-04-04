package main.java.expression.exceptions;

public class DivByZeroException extends MathException {
    public DivByZeroException() {
        super("division by zero");
    }

}
