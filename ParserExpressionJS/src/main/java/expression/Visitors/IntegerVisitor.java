package main.java.expression.Visitors;

import javafx.util.Pair;
import main.java.expression.Const;
import main.java.expression.Variable;
import main.java.expression.binary.*;
import main.java.expression.exceptions.*;
import main.java.expression.unary.Count;
import main.java.expression.unary.Negative;
import main.java.expression.unary.UnaryOperation;

import java.util.HashMap;

public class IntegerVisitor implements Visitor<Integer, Integer, HashMap<String, Integer>> {
    boolean check;

    public IntegerVisitor(boolean check) {
        this.check = check;
    }

    @Override
    public Pair<Integer, Integer> visitBinary(BinaryOperation<Integer> bin, HashMap<String, Integer> map) throws MathException {
        var x = bin.firstTerm.evaluate(this, map);
        var y = bin.secondTerm.evaluate(this, map);
        return new Pair<>(x, y);
    }

    @Override
    public Integer visitUnary(UnaryOperation<Integer> un, HashMap<String, Integer> map) throws MathException {
        return un.term.evaluate(this, map);
    }

    @Override
    public Integer visitConst(Const<Integer> num, HashMap<String, Integer> map) throws MathException {
        try {
            return Integer.valueOf(num.value);
        } catch (NumberFormatException e) {
            throw new ConstException();
        }
    }

    @Override
    public Integer visitAdd(Add<Integer> add, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(add, map);
        var x = bin.getKey();
        var y = bin.getValue();
        if (check && ((x > 0 && Integer.MAX_VALUE - x < y) ||
                (x < 0 && Integer.MIN_VALUE - x > y))) {
            throw new OverflowException();
        }
        return x + y;
    }

    @Override
    public Integer visitSub(Subtract<Integer> sub, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(sub, map);
        var x = bin.getKey();
        var y = bin.getValue();
        if (check && ((x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) ||
                (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y))) {
            throw new OverflowException();
        }
        return x - y;
    }

    @Override
    public Integer visitMul(Multiply<Integer> mul, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mul, map);
        var x = bin.getKey();
        var y = bin.getValue();
        if (check && ((x > 0 && y > 0 && Integer.MAX_VALUE / x < y) ||
                (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) ||
                (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) ||
                (x < 0 && y < 0 && Integer.MAX_VALUE / x > y))) {
            throw new OverflowException();
        }
        return x * y;
    }

    @Override
    public Integer visitDiv(Divide<Integer> div, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(div, map);
        var x = bin.getKey();
        var y = bin.getValue();
        if (y == 0) {
            throw new DivByZeroException();
        }
        if (check && x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    public Integer visitMod(Mod<Integer> mod, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mod, map);
        var x = bin.getKey();
        var y = bin.getValue();
        if (y == 0) {
            throw new DivByZeroException();
        }
        return x % y;
    }

    @Override
    public Integer visitMin(Min<Integer> min, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(min, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return Integer.min(x, y);
    }

    @Override
    public Integer visitMax(Max<Integer> max, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(max, map);
        var x = bin.getKey();
        var y = bin.getValue();
        return Integer.max(x, y);
    }

    @Override
    public Integer visitNeg(Negative<Integer> neg, HashMap<String, Integer> map) throws MathException {
        var x = visitUnary(neg, map);
        if (check && x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }

    @Override
    public Integer visitCount(Count<Integer> count, HashMap<String, Integer> map) throws MathException {
        var x = visitUnary(count, map);
        return Integer.bitCount(x);
    }

    @Override
    public Integer visitVariable(Variable<Integer> variable, HashMap<String, Integer> map) throws MathException {
        return map.get(variable.name);
    }


}
