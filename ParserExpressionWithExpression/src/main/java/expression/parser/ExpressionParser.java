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
            case LOG10:
                res = new Log10(unary());
                break;
            case POW10:
                res = new Pow10(unary());
                break;
            case OPEN_BRACE:
                res = addSub();
                if (tokenizer.getToken() != Token.CLOSE_BRACE) {
                    throw new ParserException(tokenizer.getExpression(),
                            ")",
                            tokenizer.getPosition());
                }
                tokenizer.nextToken();
                break;
            default:
                throw new ParserException(tokenizer.getExpression(),
                        " (, Variable or Digit",
                        tokenizer.getPosition());
        }
        return res;
    }

    private TripleExpression powLog() throws Exception {
        var res = unary();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case POW:
                    res = new CheckedPow(res, unary());
                    break;
                case LOG:
                    res = new CheckedLog(res, unary());
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression mulDivMod() throws Exception {
        var res = powLog();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case MUL:
                    res = new CheckedMultiply(res, powLog());
                    break;
                case DIV:
                    res = new CheckedDivide(res, powLog());
                    break;
                case MOD:
                    res = new CheckedMod(res, powLog());
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

    @Override
    public TripleExpression parse(final String s) throws Exception {
        tokenizer = new Tokenizer(s);
        var exp = addSub();
        if (tokenizer.getToken() != Token.EOF)
            throw new ParserException(tokenizer.getExpression(),
                    " Unknown symbol",
                    tokenizer.getPosition());
        return exp;
    }
}
