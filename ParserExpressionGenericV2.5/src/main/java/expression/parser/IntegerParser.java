package main.java.expression.parser;

import main.java.expression.exceptions.ConstException;

public class IntegerParser implements INumericParser<Integer> {
    @Override
    public Integer parseNum(String s) throws ConstException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ConstException();
        }
    }
}
