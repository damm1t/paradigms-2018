package main.java.expression.Visitors;

import javafx.util.Pair;
import main.java.expression.Const;
import main.java.expression.Variable;
import main.java.expression.binary.*;
import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.MathException;
import main.java.expression.unary.Count;
import main.java.expression.unary.Negative;
import main.java.expression.unary.UnaryOperation;

import java.util.HashMap;

public class DoubleVisitor implements Visitor<Double, Double, HashMap<String, Integer>> {

    @Override
    public Pair<Double, Double> visitBinary(BinaryOperation<Double> bin, HashMap<String, Integer> map) throws MathException {
        var x = bin.firstTerm.evaluate(this, map);
        var y = bin.secondTerm.evaluate(this, map);
        return new Pair<>(x, y);
    }

    @Override
    public Double visitUnary(UnaryOperation<Double> un, HashMap<String, Integer> map) throws MathException {
        return un.term.evaluate(this, map);
    }

    @Override
    public Double visitConst(Const<Double> num, HashMap<String, Integer> map) throws ConstException {
        try {
            return Double.parseDouble(num.value);
        } catch (NumberFormatException NFE) {
            throw new ConstException();
        }
    }

    @Override
    public Double visitAdd(Add<Double> add, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(add, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return first + second;
    }

    @Override
    public Double visitSub(Subtract<Double> sub, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(sub, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return first - second;
    }

    @Override
    public Double visitMul(Multiply<Double> mul, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mul, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return first * second;
    }

    @Override
    public Double visitDiv(Divide<Double> div, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(div, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return first / second;
    }

    @Override
    public Double visitMod(Mod<Double> mod, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(mod, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return first % second;
    }

    @Override
    public Double visitMin(Min<Double> min, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(min, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return Double.min(first, second);
    }

    @Override
    public Double visitMax(Max<Double> max, HashMap<String, Integer> map) throws MathException {
        var bin = visitBinary(max, map);
        var first = bin.getKey();
        var second = bin.getValue();
        return Double.max(first, second);
    }

    @Override
    public Double visitNeg(Negative<Double> neg, HashMap<String, Integer> map) throws MathException {
        var value = neg.term.evaluate(this, map);
        return -value;
    }

    @Override
    public Double visitCount(Count<Double> count, HashMap<String, Integer> map) throws MathException {
        var value = count.term.evaluate(this, map);
        return (double) Long.bitCount(Double.doubleToLongBits(value));
    }

    @Override
    public Double visitVariable(Variable<Double> variable, HashMap<String, Integer> map) throws MathException {
        return Double.valueOf(map.get(variable.name));
    }


}
