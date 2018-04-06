package main.java.expression.Visitors;

import main.java.expression.Const;
import main.java.expression.Variable;
import main.java.expression.binary.*;
import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.DivByZeroException;
import main.java.expression.exceptions.MathException;
import main.java.expression.unary.Count;
import main.java.expression.unary.Negative;

import java.util.HashMap;

public class ShortVisitor implements Visitor<Short, Short, HashMap<String, Integer>> {
    @Override
    public Short visitConst(Const<Short> num, HashMap<String, Integer> map) throws MathException {
        try {
            return Short.valueOf(num.value);
        } catch (NumberFormatException e) {
            throw new ConstException();
        }
    }

    @Override
    public Short visitAdd(Add<Short> add, HashMap<String, Integer> map) throws MathException {
        Short first = add.firstTerm.evaluate(this, map);
        Short second = add.secondTerm.evaluate(this, map);
        return (short)(first + second);
    }

    @Override
    public Short visitSub(Subtract<Short> sub, HashMap<String, Integer> map) throws MathException {
        Short first = sub.firstTerm.evaluate(this, map);
        Short second = sub.secondTerm.evaluate(this, map);
        return (short)(first - second);
    }

    @Override
    public Short visitMul(Multiply<Short> mul, HashMap<String, Integer> map) throws MathException {
        Short first = mul.firstTerm.evaluate(this, map);
        Short second = mul.secondTerm.evaluate(this, map);
        return (short)(first * second);
    }

    @Override
    public Short visitDiv(Divide<Short> div, HashMap<String, Integer> map) throws MathException {
        Short x = div.firstTerm.evaluate(this, map);
        Short y = div.secondTerm.evaluate(this, map);
        if (y == 0) {
            throw new DivByZeroException();
        }
        return (short)(x / y);
    }

    @Override
    public Short visitMod(Mod<Short> mod, HashMap<String, Integer> map) throws MathException {
        Short x = mod.firstTerm.evaluate(this, map);
        Short y = mod.secondTerm.evaluate(this, map);
        if (y == 0) {
            throw new DivByZeroException();
        }
        return (short)(x % y);
    }

    @Override
    public Short visitMin(Min<Short> min, HashMap<String, Integer> map) throws MathException {
        Short x = min.firstTerm.evaluate(this, map);
        Short y = min.secondTerm.evaluate(this, map);
        return (short)Math.min(x, y);
    }

    @Override
    public Short visitMax(Max<Short> max, HashMap<String, Integer> map) throws MathException {
        Short x = max.firstTerm.evaluate(this, map);
        Short y = max.secondTerm.evaluate(this, map);
        return (short)Math.max(x, y);
    }

    @Override
    public Short visitNeg(Negative<Short> neg, HashMap<String, Integer> map) throws MathException {
        Short value = neg.term.evaluate(this, map);
        return (short)-value;
    }

    @Override
    public Short visitCount(Count<Short> count, HashMap<String, Integer> map) throws MathException {
        Short value = count.term.evaluate(this, map);
        return (short)Integer.bitCount(Short.toUnsignedInt(value));
    }

    @Override
    public Short visitVariable(Variable<Short> variable, HashMap<String, Integer> map) throws MathException {
        return (short) (int) (map.get(variable.name));
    }
}
