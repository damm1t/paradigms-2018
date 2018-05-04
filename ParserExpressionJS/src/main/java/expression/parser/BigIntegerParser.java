package main.java.expression.parser;

import main.java.expression.exceptions.ConstException;

import java.math.BigInteger;

public class BigIntegerParser implements INumericParser<BigInteger> {
    @Override
    public BigInteger parseNum(String s) throws ConstException {
        try {
            return new BigInteger(s);
        } catch (NumberFormatException NFE) {
            throw new ConstException();
        }
    }
}
