package expression.parser;

import expression.*;
import expression.Binary.*;
import expression.Unary.Abs;
import expression.Unary.Not;
import expression.Unary.Square;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParser implements Parser {


    private Tokenizer tokenizer = null;

    private TripleExpression unary() {
        tokenizer.nextToken();
        TripleExpression res;
        switch (tokenizer.getToken()) {
            case NUMBER:
                res = new Const(tokenizer.getValue());
                tokenizer.nextToken();
                break;
            case VARIABLE:
                res = new Variable(tokenizer.getName());
                tokenizer.nextToken();
                break;
            case MINUS:
                res = new Not(unary());
                break;
            case ABS:
                res = new Abs(unary());
                break;
            case SQUARE:
                res = new Square(unary());
                break;
            case OPEN_BRACE:
                res = shifts();
                tokenizer.nextToken();
                break;
            default:
                throw new IllegalStateException("Expected: [OPEN_BRACE, SQUARE, ABS, MINUS, " +
                    "VARIABLE, NUMBER], found: " + tokenizer.getToken());
        }
        return res;
    }

    private TripleExpression mulDivMod() {
        TripleExpression res = unary();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case MUL:
                    res = new Multiply(res, unary());
                    break;
                case DIV:
                    res = new Divide(res, unary());
                    break;
                case MOD:
                    res = new Mod(res, unary());
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression addSub() {
        TripleExpression res = mulDivMod();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case ADD:
                    res = new Add(res, mulDivMod());
                    break;
                case MINUS:
                    res = new Subtract(res, mulDivMod());
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression and() {
        TripleExpression res = addSub();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case AND:
                    res = new And(res, addSub());
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression xor() {
        TripleExpression res = and();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case XOR:
                    res = new Xor(res, and());
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression or() {
        TripleExpression res = xor();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case OR:
                    res = new Or(res, xor());
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression shifts() {
        TripleExpression res = or();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case LEFT_SHIFT:
                    res = new LeftShift(res, or());
                    break;
                case RIGHT_SHIFT:
                    res = new RightShift(res, or());
                    break;
                default:
                    return res;
            }
        }
    }

    @Override
    public TripleExpression parse(final String s) {
        tokenizer = new Tokenizer(s);
        TripleExpression exp = shifts();
        if (tokenizer.getToken() != Token.EOF)
            throw new IllegalStateException("Expected: EOF, Found: " + tokenizer.getToken());
        return exp;
    }
}
