package main.java.expression.parser;

import main.java.expression.exceptions.ConstException;

public interface INumericParser<T> {
    public T parseNum(String s) throws ConstException;
}
