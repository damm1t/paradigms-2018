package main.java.expression.operations;

import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.DivByZeroException;
import main.java.expression.exceptions.MathException;
import main.java.expression.exceptions.OverflowException;

public class FloatOperation implements Operation<Float> {
    @Override
    public Float parseNumber(String number) throws ConstException {
        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException NFE) {
            throw new ConstException();
        }
    }

    @Override
    public Float add(Float x, Float y) throws OverflowException {
        return x + y;
    }

    @Override
    public Float substract(Float x, Float y) throws OverflowException {
        return x - y;
    }

    @Override
    public Float multiply(Float x, Float y) throws OverflowException {
        return x * y;
    }

    @Override
    public Float divide(Float x, Float y) throws MathException {
        return x / y;
    }

    @Override
    public Float mod(Float x, Float y) throws DivByZeroException {
        return x % y;
    }

    @Override
    public Float negative(Float x) throws OverflowException {
        return -x;
    }

    @Override
    public Float abs(Float x) throws OverflowException {
        return Math.abs(x);
    }
}
