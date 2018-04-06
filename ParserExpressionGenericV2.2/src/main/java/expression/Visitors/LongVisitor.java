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

public class LongVisitor implements Visitor<Long, Long, HashMap<String, Integer>> {
    @Override
    public Long visitConst(Const<Long> num, HashMap<String, Integer> map) throws MathException {
        try {
            return Long.parseLong(num.value);
        } catch (NumberFormatException NFE) {
            throw new ConstException();
        }
    }

    @Override
    public Long visitAdd(Add<Long> add, HashMap<String, Integer> map) throws MathException {
        var first = add.firstTerm.evaluate(this, map);
        var second = add.secondTerm.evaluate(this, map);
        return first + second;
    }

    @Override
    public Long visitSub(Subtract<Long> sub, HashMap<String, Integer> map) throws MathException {
        var first = sub.firstTerm.evaluate(this, map);
        var second = sub.secondTerm.evaluate(this, map);
        return first - second;
    }

    @Override
    public Long visitMul(Multiply<Long> mul, HashMap<String, Integer> map) throws MathException {
        var first = mul.firstTerm.evaluate(this, map);
        var second = mul.secondTerm.evaluate(this, map);
        return first * second;
    }

    @Override
    public Long visitDiv(Divide<Long> div, HashMap<String, Integer> map) throws MathException {
        var x = div.firstTerm.evaluate(this, map);
        var y = div.secondTerm.evaluate(this, map);
        if (y == 0) {
            throw new DivByZeroException();
        }
        return x / y;
    }

    @Override
    public Long visitMod(Mod<Long> mod, HashMap<String, Integer> map) throws MathException {
        var x = mod.firstTerm.evaluate(this, map);
        var y = mod.secondTerm.evaluate(this, map);
        if (y == 0) {
            throw new DivByZeroException();
        }
        return x % y;
    }

    @Override
    public Long visitMin(Min<Long> min, HashMap<String, Integer> map) throws MathException {
        var x = min.firstTerm.evaluate(this, map);
        var y = min.secondTerm.evaluate(this, map);
        return Long.min(x, y);
    }

    @Override
    public Long visitMax(Max<Long> max, HashMap<String, Integer> map) throws MathException {
        var x = max.firstTerm.evaluate(this, map);
        var y = max.secondTerm.evaluate(this, map);
        return Long.max(x, y);
    }

    @Override
    public Long visitNeg(Negative<Long> neg, HashMap<String, Integer> map) throws MathException {
        var value = neg.term.evaluate(this, map);
        return -value;
    }

    @Override
    public Long visitCount(Count<Long> count, HashMap<String, Integer> map) throws MathException {
        var value = count.term.evaluate(this, map);
        return (long)Long.bitCount(value);
    }

    @Override
    public Long visitVariable(Variable<Long> variable, HashMap<String, Integer> map) throws MathException {
        return Long.valueOf(map.get(variable.name));
    }
}
