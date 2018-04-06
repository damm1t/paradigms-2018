package main.java.expression.operations;

import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.DivByZeroException;
import main.java.expression.exceptions.MathException;
import main.java.expression.exceptions.OverflowException;

public interface Operation<T> {
    T parseNumber(final String number) throws ConstException;

    T add(final T x, final T y) throws OverflowException;

    T substract(final T x, final T y) throws OverflowException;

    T multiply(final T x, final T y) throws OverflowException;

    T divide(final T x, final T y) throws MathException;

    T mod(final T x, final T y) throws DivByZeroException;

    T negative(final T x) throws OverflowException;

    T abs(final T x) throws OverflowException;

    default T sqr(final T x) throws OverflowException {
        return multiply(x, x);
    }
}