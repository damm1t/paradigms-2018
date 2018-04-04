package main.java.expression.parser;


import main.java.expression.exceptions.ParserException;
import main.java.expression.TripleExpression;

public interface Parser<T> {
    TripleExpression<T> parse(String expression) throws ParserException;
}