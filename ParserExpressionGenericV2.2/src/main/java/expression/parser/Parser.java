package main.java.expression.parser;


import main.java.expression.exceptions.ParserException;
import main.java.expression.IExpression;
import main.java.expression.tokenizer.Tokenizer;

public interface Parser<T> {
    IExpression<T> parse(String expression) throws ParserException;
}