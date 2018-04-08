package main.java.expression.Exceptions;

import main.java.expression.Tokenizer.Token;

public class ParserException extends RuntimeException {
    public ParserException(String message) {
        super(message);
    }

    public ParserException(String expression, String expected, int position) {
        super(expression + "; Expected: " + expected + "; at the position " + position);
    }
}
