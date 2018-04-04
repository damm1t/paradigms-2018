package main.java.expression.exceptions;

public class ModeException extends Exception {
    public ModeException(final String mode) {
        super("Incorrect mode: " + mode);
    }
}
