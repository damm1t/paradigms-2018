package main.java.expression.Visitors;

import main.java.expression.Const;
import main.java.expression.Variable;
import main.java.expression.binary.*;
import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.MathException;
import main.java.expression.unary.Count;
import main.java.expression.unary.Negative;

import java.util.HashMap;

public class DoubleVisitor implements Visitor<Double, Double, HashMap<String, Integer>> {

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
        var first = add.firstTerm.evaluate(this, map);
        var second = add.secondTerm.evaluate(this, map);
        return first + second;
    }

    @Override
    public Double visitSub(Subtract<Double> sub, HashMap<String, Integer> map) throws MathException {
        var first = sub.firstTerm.evaluate(this, map);
        var second = sub.secondTerm.evaluate(this, map);
        return first - second;
    }

    @Override
    public Double visitMul(Multiply<Double> mul, HashMap<String, Integer> map) throws MathException {
        var first = mul.firstTerm.evaluate(this, map);
        var second = mul.secondTerm.evaluate(this, map);
        return first * second;
    }

    @Override
    public Double visitDiv(Divide<Double> div, HashMap<String, Integer> map) throws MathException {
        var first = div.firstTerm.evaluate(this, map);
        var second = div.secondTerm.evaluate(this, map);
        return first / second;
    }

    @Override
    public Double visitMod(Mod<Double> mod, HashMap<String, Integer> map) throws MathException {
        var first = mod.firstTerm.evaluate(this, map);
        var second = mod.secondTerm.evaluate(this, map);
        return first % second;
    }

    @Override
    public Double visitMin(Min<Double> min, HashMap<String, Integer> map) throws MathException {
        var first = min.firstTerm.evaluate(this, map);
        var second = min.secondTerm.evaluate(this, map);
        return Double.min(first, second);
    }

    @Override
    public Double visitMax(Max<Double> max, HashMap<String, Integer> map) throws MathException {
        var first = max.firstTerm.evaluate(this, map);
        var second = max.secondTerm.evaluate(this, map);
        return Double.max(first, second);
    }

    @Override
    public Double visitNeg(Negative<Double> neg, HashMap<String, Integer> map) throws MathException {
        Double value = neg.term.evaluate(this, map);
        return -value;
    }

    @Override
    public Double visitCount(Count<Double> count, HashMap<String, Integer> map) throws MathException {
        Double value = count.term.evaluate(this, map);
        return (double)Long.bitCount(Double.doubleToLongBits(value));
    }

    @Override
    public Double visitVariable(Variable<Double> variable, HashMap<String, Integer> map) throws MathException {
        return Double.valueOf(map.get(variable.name));
    }


}
