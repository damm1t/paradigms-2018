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

import java.util.HashMap;

public class LongVisitor implements Visitor<Long, Long, HashMap<String, Integer>> {

    @Override
    public Pair<Long, Long> visitBinary(BinaryOperation<Long> bin, HashMap<String, Integer> map) throws MathException {
        var x = bin.firstTerm.evaluate(this, map);
        var y = bin.secondTerm.evaluate(this, map);
        return new Pair<>(x, y);
    }

    @Override
    public Long visitUnary(UnaryOperation<Long> un, HashMap<String, Integer> map) throws MathException {
        return un.term.evaluate(this, map);
    }

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
        var bin = visitBinary(add, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return x + y;
    }

    @Override
    public Long visitSub(Subtract<Long> sub, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(sub, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return x - y;
    }

    @Override
    public Long visitMul(Multiply<Long> mul, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mul, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return x * y;
    }

    @Override
    public Long visitDiv(Divide<Long> div, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(div, map);
        var x = bin.getKey();
        var y = bin.getValue();
        if (y == 0) {
            throw new DivByZeroException();
        }
        return x / y;
    }

    @Override
    public Long visitMod(Mod<Long> mod, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mod, map);
        var y = bin.getValue();
        var x = bin.getKey();
        if (y == 0) {
            throw new DivByZeroException();
        }
        return x % y;
    }

    @Override
    public Long visitMin(Min<Long> min, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(min, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return Long.min(x, y);
    }

    @Override
    public Long visitMax(Max<Long> max, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(max, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return Long.max(x, y);
    }

    @Override
    public Long visitNeg(Negative<Long> neg, HashMap<String, Integer> map) throws MathException {
        var x = visitUnary(neg, map);
        return -x;
    }

    @Override
    public Long visitCount(Count<Long> count, HashMap<String, Integer> map) throws MathException {
        var x = visitUnary(count, map);
        return (long) Long.bitCount(x);
    }

    @Override
    public Long visitVariable(Variable<Long> variable, HashMap<String, Integer> map) throws MathException {
        return Long.valueOf(map.get(variable.name));
    }
}
