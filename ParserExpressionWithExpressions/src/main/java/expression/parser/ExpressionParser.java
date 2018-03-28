package main.java.expression.parser;


import main.java.expression.Binary.*;
import main.java.expression.Const;
import main.java.expression.Exceptions.ParserException;
import main.java.expression.Exceptions.TokenizerException;
import main.java.expression.Tokenizer.Token;
import main.java.expression.Tokenizer.Tokenizer;
import main.java.expression.TripleExpression;
import main.java.expression.Unary.*;
import main.java.expression.Variable;

public class ExpressionParser implements Parser {
    private Tokenizer tokenizer = null;

    private TripleExpression unary() throws Exception {
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
            case NEGATIVE:
                res = new CheckedNegate(unary());
                break;
            case NOT:
                res = new BinNot(unary());
                break;
            case COUNT:
                res = new Count(unary());
                break;
            case ABS:
                res = new Abs(unary());
                break;
            case SQUARE:
                res = new Square(unary());
                break;
            case OPEN_BRACE:
                res = addSub();
                if (tokenizer.getToken() != Token.CLOSE_BRACE) {
                    throw new ParserException(Token.CLOSE_BRACE.toString(),
                            tokenizer.getToken().toString(),
                            tokenizer.getPosition());
                }
                tokenizer.nextToken();
                break;
            default:
                throw new ParserException("[OPEN_BRACE, SQUARE, ABS, MINUS, VARIABLE, NUMBER]",
                        tokenizer.getToken().toString(),
                        tokenizer.getPosition());
        }
        return res;
    }

    private TripleExpression mulDivMod() throws Exception {
        var res = unary();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case MUL:
                    res = new CheckedMultiply(res, unary());
                    break;
                case DIV:
                    res = new CheckedDivide(res, unary());
                    break;
                case MOD:
                    res = new CheckedMod(res, unary());
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression addSub() throws Exception {
        var res = mulDivMod();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case ADD:
                    res = new CheckedAdd(res, mulDivMod());
                    break;
                case SUB:
                    res = new CheckedSubtract(res, mulDivMod());
                    break;
                default:
                    return res;
            }
        }
    }

   /* private TripleExpression and() throws ParserException {
        var res = addSub();
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

    private TripleExpression xor() throws ParserException {
        var res = and();
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

    private TripleExpression or() throws ParserException {
        var res = xor();
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

    private TripleExpression shifts() throws ParserException {
        var res = or();
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
    }*/

    @Override
    public TripleExpression parse(final String s) throws Exception {
        tokenizer = new Tokenizer(s);
        var exp = addSub();
        if (tokenizer.getToken() != Token.EOF)
            throw new ParserException(Token.EOF.toString(),
                    tokenizer.getToken().toString(),
                    tokenizer.getPosition());
        return exp;
    }
}
