package main.java.expression.operations;

import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.DivByZeroException;
import main.java.expression.exceptions.MathException;
import main.java.expression.exceptions.OverflowException;

import java.math.BigInteger;

public class BigIntegerOperation implements Operation<BigInteger> {

    BigInteger x, y, z;

    public BigIntegerOperation(BigInteger x, BigInteger y, BigInteger z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public BigInteger parseNumber(String number) throws ConstException {
        try {
            return new BigInteger(number);
        } catch (NumberFormatException NFE) {
            throw new ConstException();
        }
    }

    @Override
    public BigInteger add(BigInteger x, BigInteger y) throws OverflowException {
        return x.add(y);
    }

    @Override
    public BigInteger substract(BigInteger x, BigInteger y) throws OverflowException {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) throws OverflowException {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) throws MathException {
        if (y.equals(BigInteger.ZERO)) {
            throw new DivByZeroException();
        }
        return x.divide(y);
    }

    @Override
    public BigInteger mod(BigInteger x, BigInteger y) throws DivByZeroException {
        if (y.equals(BigInteger.ZERO)) {
            throw new DivByZeroException();
        }
        return x.mod(y);
    }

    @Override
    public BigInteger negative(BigInteger x) throws OverflowException {
        return x.negate();
    }

    @Override
    public BigInteger abs(BigInteger x) throws OverflowException {
        return x.abs();
    }
}
