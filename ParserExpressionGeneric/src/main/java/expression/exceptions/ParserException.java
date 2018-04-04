package main.java.expression.exceptions;

public class ParserException extends Exception {
    public ParserException(String message) {
        super(message);
    }

    public ParserException(String expression, String expected, int position) {
        super(expression + "; Expected: " + expected + "; at the position " + position);
    }
}
