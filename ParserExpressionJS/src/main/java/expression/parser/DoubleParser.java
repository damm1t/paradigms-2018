package main.java.expression.parser;

import main.java.expression.exceptions.ConstException;

public class DoubleParser implements INumericParser<Double> {
    @Override
    public Double parseNum(String s) throws ConstException {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException NFE) {
            throw new ConstException();
        }
    }
}
