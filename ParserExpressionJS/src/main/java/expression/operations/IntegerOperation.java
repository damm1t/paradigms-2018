package main.java.expression.operations;

import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.DivByZeroException;
import main.java.expression.exceptions.MathException;
import main.java.expression.exceptions.OverflowException;

public class IntegerOperation implements Operation<Integer> {
    final private boolean checkOverflow;


    public boolean getCheckOverflow(){
        return checkOverflow;
    }
    public IntegerOperation(boolean overflow) {
        this.checkOverflow = overflow;
    }

    @Override
    public Integer parseNumber(String number) throws ConstException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ConstException();
        }
    }

    @Override
    public Integer add(Integer x, Integer y) throws OverflowException {
        if(checkOverflow && ((x > 0 && Integer.MAX_VALUE - x < y) ||
                    (x < 0 && Integer.MIN_VALUE - x > y))) {
                throw new OverflowException();
            }
        return x + y;
    }

    @Override
    public Integer substract(Integer x, Integer y) throws OverflowException {
        if (checkOverflow && ((x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) ||
                (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y))) {
            throw new OverflowException();
        }
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) throws OverflowException {
        if (checkOverflow && ((x > 0 && y > 0 && Integer.MAX_VALUE / x < y) ||
                (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) ||
                (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) ||
                (x < 0 && y < 0 && Integer.MAX_VALUE / x > y))) {
            throw new OverflowException();
        }
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) throws MathException {
        if (y == 0) {
            throw new DivByZeroException();
        }
        if (checkOverflow && x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    public Integer mod(Integer x, Integer y) throws DivByZeroException {
        if (y == 0) {
            throw new DivByZeroException();
        }
        return x % y;
    }

    @Override
    public Integer negative(Integer x) throws OverflowException {
        if (checkOverflow && x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }

    @Override
    public Integer abs(Integer x) throws OverflowException {
        if (checkOverflow && x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return (x < 0 ? -x : x);
    }

}
