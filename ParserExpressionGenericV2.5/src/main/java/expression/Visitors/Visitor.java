package main.java.expression.Visitors;

import javafx.util.Pair;
import main.java.expression.Const;
import main.java.expression.Variable;
import main.java.expression.binary.*;
import main.java.expression.exceptions.MathException;
import main.java.expression.unary.Count;
import main.java.expression.unary.Negative;
import main.java.expression.unary.UnaryOperation;

public interface Visitor<T, R, C> {

    Pair<R, R> visitBinary(BinaryOperation<T> bin, C map) throws MathException;

    R visitUnary(UnaryOperation<T> un, C map) throws MathException;

    R visitConst(Const<T> num, C map) throws MathException;

    R visitAdd(Add<T> add, C map) throws MathException;

    R visitSub(Subtract<T> sub, C map) throws MathException;

    R visitMul(Multiply<T> mul, C map) throws MathException;

    R visitDiv(Divide<T> div, C map) throws MathException;

    R visitMod(Mod<T> mod, C map) throws MathException;

    R visitMin(Min<T> min, C map) throws MathException;

    R visitMax(Max<T> max, C map) throws MathException;

    R visitNeg(Negative<T> neg, C map) throws MathException;

    R visitCount(Count<T> count, C map) throws MathException;

    R visitVariable(Variable<T> variable, C map) throws MathException;
}
