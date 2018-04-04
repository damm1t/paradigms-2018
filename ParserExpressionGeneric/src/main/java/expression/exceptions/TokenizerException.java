package main.java.expression.exceptions;

public class TokenizerException extends ParserException {
    public TokenizerException(String expression, String message, int position) {
        super(expression + "; " + message + "; failed at the position " + position);
    }
}
