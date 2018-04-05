package main.java.expression;

import main.java.expression.Exceptions.MathException;

public interface TripleExpression {
    int evaluate(int x, int y, int z) throws Exception;
}