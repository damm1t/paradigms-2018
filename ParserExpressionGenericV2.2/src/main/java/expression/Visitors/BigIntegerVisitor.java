package main.java.expression.Visitors;

import main.java.expression.Const;
import main.java.expression.Variable;
import main.java.expression.binary.*;
import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.DivByZeroException;
import main.java.expression.exceptions.MathException;
import main.java.expression.unary.Count;
import main.java.expression.unary.Negative;

import java.math.BigInteger;
import java.util.HashMap;

public class BigIntegerVisitor implements Visitor<BigInteger, BigInteger, HashMap<String, Integer>> {

    @Override
    public BigInteger visitConst(Const<BigInteger> num, HashMap<String, Integer> map) throws ConstException {
        try {
            return new BigInteger(num.value);
        } catch (NumberFormatException NFE) {
            throw new ConstException();
        }
    }

    @Override
    public BigInteger visitAdd(Add<BigInteger> add, HashMap<String, Integer> map) throws MathException {
        BigInteger first = add.firstTerm.evaluate(this, map);
        BigInteger second = add.secondTerm.evaluate(this, map);
        return first.add(second);
    }

    @Override
    public BigInteger visitSub(Subtract<BigInteger> sub, HashMap<String, Integer> map) throws MathException {
        BigInteger first = sub.firstTerm.evaluate(this, map);
        BigInteger second = sub.secondTerm.evaluate(this, map);
        return first.subtract(second);
    }

    @Override
    public BigInteger visitMul(Multiply<BigInteger> mul, HashMap<String, Integer> map) throws MathException {
        var first = mul.firstTerm.evaluate(this, map);
        var second = mul.secondTerm.evaluate(this, map);
        return first.multiply(second);
    }

    @Override
    public BigInteger visitDiv(Divide<BigInteger> div, HashMap<String, Integer> map) throws MathException {
        BigInteger first = div.firstTerm.evaluate(this, map);
        BigInteger second = div.secondTerm.evaluate(this, map);
        if (second.equals(BigInteger.ZERO)) {
            throw new DivByZeroException();
        }
        return first.divide(second);
    }

    @Override
    public BigInteger visitMod(Mod<BigInteger> mod, HashMap<String, Integer> map) throws MathException {
        BigInteger first = mod.firstTerm.evaluate(this, map);
        BigInteger second = mod.secondTerm.evaluate(this, map);
        if (second.equals(BigInteger.ZERO)) {
            throw new DivByZeroException();
        }
        return first.mod(second);
    }

    @Override
    public BigInteger visitMin(Min<BigInteger> min, HashMap<String, Integer> map) throws MathException {
        var first = min.firstTerm.evaluate(this, map);
        var second = min.secondTerm.evaluate(this, map);
        return first.min(second);
    }

    @Override
    public BigInteger visitMax(Max<BigInteger> max, HashMap<String, Integer> map) throws MathException {
        var first = max.firstTerm.evaluate(this, map);
        var second = max.secondTerm.evaluate(this, map);
        return first.max(second);
    }

    @Override
    public BigInteger visitNeg(Negative<BigInteger> neg, HashMap<String, Integer> map) throws MathException {
        BigInteger value = neg.term.evaluate(this, map);
        return value.negate();
    }

    @Override
    public BigInteger visitCount(Count<BigInteger> count, HashMap<String, Integer> map) throws MathException {
        BigInteger value = count.term.evaluate(this, map);
        return BigInteger.valueOf(value.bitCount());
    }

    @Override
    public BigInteger visitVariable(Variable<BigInteger> variable, HashMap<String, Integer> map) throws MathException {
        return BigInteger.valueOf(map.get(variable.name));
    }


}
