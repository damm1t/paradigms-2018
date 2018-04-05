package main.java.expression.parser;


import main.java.expression.TripleExpression;

public interface Parser {
    TripleExpression parse(String expression);
}