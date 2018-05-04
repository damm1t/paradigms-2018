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

public class ShortVisitor implements Visitor<Short, Short, HashMap<String, Integer>> {
    @Override
    public Pair<Short, Short> visitBinary(BinaryOperation<Short> bin, HashMap<String, Integer> map) throws MathException {
        var x = bin.firstTerm.evaluate(this, map);
        var y = bin.secondTerm.evaluate(this, map);
        return new Pair<>(x, y);
    }

    @Override
    public Short visitUnary(UnaryOperation<Short> un, HashMap<String, Integer> map) throws MathException {
        return un.term.evaluate(this, map);
    }

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
        var bin = visitBinary(add, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return (short) (x + y);
    }

    @Override
    public Short visitSub(Subtract<Short> sub, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(sub, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return (short) (x - y);
    }

    @Override
    public Short visitMul(Multiply<Short> mul, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mul, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return (short) (x * y);
    }

    @Override
    public Short visitDiv(Divide<Short> div, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(div, map);
        var x = bin.getKey();
        var y = bin.getValue();
        if (y == 0) {
            throw new DivByZeroException();
        }
        return (short) (x / y);
    }

    @Override
    public Short visitMod(Mod<Short> mod, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mod, map);
        var x = bin.getKey();
        var y = bin.getValue();
        if (y == 0) {
            throw new DivByZeroException();
        }
        return (short) (x % y);
    }

    @Override
    public Short visitMin(Min<Short> min, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(min, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return (short) Math.min(x, y);
    }

    @Override
    public Short visitMax(Max<Short> max, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(max, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return (short) Math.max(x, y);
    }

    @Override
    public Short visitNeg(Negative<Short> neg, HashMap<String, Integer> map) throws MathException {
        var x = visitUnary(neg, map);
        return (short) -x;
    }

    @Override
    public Short visitCount(Count<Short> count, HashMap<String, Integer> map) throws MathException {
        var x = visitUnary(count, map);
        return (short) Integer.bitCount(Short.toUnsignedInt(x));
    }

    @Override
    public Short visitVariable(Variable<Short> variable, HashMap<String, Integer> map) throws MathException {
        return (short) (int) (map.get(variable.name));
    }
}
