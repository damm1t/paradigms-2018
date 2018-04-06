package main.java.expression.operations;

import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.DivByZeroException;
import main.java.expression.exceptions.MathException;
import main.java.expression.exceptions.OverflowException;

import java.math.BigInteger;

public class DoubleOperation implements Operation<Double> {
    @Override
    public Double parseNumber(String number) throws ConstException {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException NFE) {
            throw new ConstException();
        }
    }

    @Override
    public Double add(Double x, Double y) throws OverflowException {
        return x + y;
    }

    @Override
    public Double substract(Double x, Double y) throws OverflowException {
        return x - y;
    }

    @Override
    public Double multiply(Double x, Double y) throws OverflowException {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) throws MathException {
        return x / y;
    }

    @Override
    public Double mod(Double x, Double y) throws DivByZeroException {
        return x % y;
    }

    @Override
    public Double negative(Double x) throws OverflowException {
        return -x;
    }

    @Override
    public Double abs(Double x) throws OverflowException {
        return Math.abs(x);
    }
}
