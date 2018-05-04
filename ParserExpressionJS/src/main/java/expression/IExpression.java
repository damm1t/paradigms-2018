package main.java.expression;

import main.java.expression.exceptions.MathException;
import main.java.expression.Visitors.Visitor;

import java.util.HashMap;

public interface IExpression<T> {
    <R, C> R evaluate(Visitor<T, R, C> visitor, C map) throws MathException;
}