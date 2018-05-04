package main.java.expression.Visitors;

import javafx.util.Pair;
import main.java.expression.Const;
import main.java.expression.Variable;
import main.java.expression.binary.*;
import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.DivByZeroException;
import main.java.expression.exceptions.MathException;
import main.java.expression.unary.Count;
import main.java.expression.unary.Negative;
import main.java.expression.unary.UnaryOperation;

import java.math.BigInteger;
import java.util.HashMap;

public class BigIntegerVisitor implements Visitor<BigInteger, BigInteger, HashMap<String, Integer>> {

    @Override
    public Pair<BigInteger, BigInteger> visitBinary(BinaryOperation<BigInteger> bin, HashMap<String, Integer> map) throws MathException {
        var x = bin.firstTerm.evaluate(this, map);
        var y = bin.secondTerm.evaluate(this, map);
        return new Pair<>(x, y);
    }

    @Override
    public BigInteger visitUnary(UnaryOperation<BigInteger> un, HashMap<String, Integer> map) throws MathException {
        return un.term.evaluate(this, map);
    }

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
        var bin = visitBinary(add, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return x.add(y);
    }

    @Override
    public BigInteger visitSub(Subtract<BigInteger> sub, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(sub, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return first.subtract(second);
    }

    @Override
    public BigInteger visitMul(Multiply<BigInteger> mul, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mul, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return first.multiply(second);
    }

    @Override
    public BigInteger visitDiv(Divide<BigInteger> div, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(div, map);
        var first = bin.getKey();
        var second = bin.getValue();
        if (second.equals(BigInteger.ZERO)) {
            throw new DivByZeroException();
        }
        return first.divide(second);
    }

    @Override
    public BigInteger visitMod(Mod<BigInteger> mod, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mod, map);
        var first = bin.getKey();
        var second = bin.getValue();
        if (second.equals(BigInteger.ZERO)) {
            throw new DivByZeroException();
        }
        return first.mod(second);
    }

    @Override
    public BigInteger visitMin(Min<BigInteger> min, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(min, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return first.min(second);
    }

    @Override
    public BigInteger visitMax(Max<BigInteger> max, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(max, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return first.max(second);
    }

    @Override
    public BigInteger visitNeg(Negative<BigInteger> neg, HashMap<String, Integer> map) throws MathException {
        var first = visitUnary(neg, map);
        return first.negate();
    }

    @Override
    public BigInteger visitCount(Count<BigInteger> count, HashMap<String, Integer> map) throws MathException {
        var first = visitUnary(count, map);
        return BigInteger.valueOf(first.bitCount());
    }

    @Override
    public BigInteger visitVariable(Variable<BigInteger> variable, HashMap<String, Integer> map) throws MathException {
        return BigInteger.valueOf(map.get(variable.name));
    }


}
