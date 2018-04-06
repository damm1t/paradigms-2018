package main.java.expression.operations;

import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.DivByZeroException;
import main.java.expression.exceptions.MathException;
import main.java.expression.exceptions.OverflowException;

public class ByteOperation implements Operation<Byte> {
    @Override
    public Byte parseNumber(String number) throws ConstException {
        return null;
    }

    @Override
    public Byte add(Byte x, Byte y) throws OverflowException {
        return null;
    }

    @Override
    public Byte substract(Byte x, Byte y) throws OverflowException {
        return null;
    }

    @Override
    public Byte multiply(Byte x, Byte y) throws OverflowException {
        return null;
    }

    @Override
    public Byte divide(Byte x, Byte y) throws MathException {
        return null;
    }

    @Override
    public Byte mod(Byte x, Byte y) throws DivByZeroException {
        return null;
    }

    @Override
    public Byte negative(Byte x) throws OverflowException {
        return null;
    }

    @Override
    public Byte abs(Byte x) throws OverflowException {
        return null;
    }
}
