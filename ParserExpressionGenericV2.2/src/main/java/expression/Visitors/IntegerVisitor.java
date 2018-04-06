package main.java.expression.Visitors;

import main.java.expression.Const;
import main.java.expression.Variable;
import main.java.expression.binary.*;
import main.java.expression.exceptions.*;
import main.java.expression.unary.Count;
import main.java.expression.unary.Negative;

import java.util.HashMap;

public class IntegerVisitor implements Visitor<Integer, Integer, HashMap<String, Integer>> {

    @Override
    public Integer visitConst(Const<Integer> num, HashMap<String, Integer> map) throws MathException{
        try {
            return Integer.valueOf(num.value);
        } catch (NumberFormatException e) {
            throw new ConstException();
        }
    }

    @Override
    public Integer visitAdd(Add<Integer> add, HashMap<String, Integer> map) throws MathException {
        var x = add.firstTerm.evaluate(this, map);
        var y = add.secondTerm.evaluate(this, map);
        if((x > 0 && Integer.MAX_VALUE - x < y) ||
                (x < 0 && Integer.MIN_VALUE - x > y)) {
            throw new OverflowException();
        }
        return x + y;
    }

    @Override
    public Integer visitSub(Subtract<Integer> sub, HashMap<String, Integer> map) throws MathException {
        var x = sub.firstTerm.evaluate(this, map);
        var y = sub.secondTerm.evaluate(this, map);
        if ((x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) ||
                (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y)) {
            throw new OverflowException();
        }
        return x - y;
    }

    @Override
    public Integer visitMul(Multiply<Integer> mul, HashMap<String, Integer> map) throws MathException {
        var x = mul.firstTerm.evaluate(this, map);
        var y = mul.secondTerm.evaluate(this, map);
        if ((x > 0 && y > 0 && Integer.MAX_VALUE / x < y) ||
                (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) ||
                (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) ||
                (x < 0 && y < 0 && Integer.MAX_VALUE / x > y)) {
            throw new OverflowException();
        }
        return x * y;
    }

    @Override
    public Integer visitDiv(Divide<Integer> div, HashMap<String, Integer> map) throws MathException {
        var x = div.firstTerm.evaluate(this, map);
        var y = div.secondTerm.evaluate(this, map);
        if (y == 0) {
            throw new DivByZeroException();
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    public Integer visitMod(Mod<Integer> mod, HashMap<String, Integer> map) throws MathException {
        var x = mod.firstTerm.evaluate(this, map);
        var y = mod.secondTerm.evaluate(this, map);
        if (y == 0) {
            throw new DivByZeroException();
        }
        return x % y;
    }

    @Override
    public Integer visitMin(Min<Integer> min, HashMap<String, Integer> map) throws MathException {
        var first = min.firstTerm.evaluate(this, map);
        var second = min.secondTerm.evaluate(this, map);
        return Integer.min(first, second);
    }

    @Override
    public Integer visitMax(Max<Integer> max, HashMap<String, Integer> map) throws MathException {
        var first = max.firstTerm.evaluate(this, map);
        var second = max.secondTerm.evaluate(this, map);
        return Integer.max(first, second);
    }

    @Override
    public Integer visitNeg(Negative<Integer> neg, HashMap<String, Integer> map) throws MathException {
        var value = neg.term.evaluate(this, map);
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -value;
    }

    @Override
    public Integer visitCount(Count<Integer> count, HashMap<String, Integer> map) throws MathException {
        var value = count.term.evaluate(this, map);
        return Integer.bitCount(value);
    }

    @Override
    public Integer visitVariable(Variable<Integer> variable, HashMap<String, Integer> map) throws MathException {
        return map.get(variable.name);
    }


}
