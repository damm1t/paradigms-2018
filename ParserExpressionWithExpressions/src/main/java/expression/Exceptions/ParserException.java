package main.java.expression.Exceptions;

import main.java.expression.Tokenizer.Token;

public class ParserException extends RuntimeException {
    public ParserException(String expected, String found, int position) {
        super("Expected: " + expected + ", found: " + found + " at the position " + position);
    }
}
