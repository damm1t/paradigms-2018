package main.java.expression.Exceptions;

import main.java.expression.Tokenizer.Token;

public class TokenizerException extends Exception {
    public TokenizerException(String expression, String message, int position) {
        super(expression + "; " + message + "; failed at the position " + position);
    }
}
