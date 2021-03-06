package main.java.expression.parser;


import main.java.expression.IExpression;
import main.java.expression.binary.*;
import main.java.expression.Const;
import main.java.expression.exceptions.ParserException;
import main.java.expression.tokenizer.Token;
import main.java.expression.tokenizer.Tokenizer;
import main.java.expression.unary.*;
import main.java.expression.Variable;

public class ExpressionParser<T> implements Parser<T> {
    private Tokenizer<T> tokenizer;

    private IExpression<T> unary() throws ParserException {
        tokenizer.nextToken();
        IExpression<T> res;
        switch (tokenizer.getToken()) {
            case NUMBER:
                res = new Const<T>(tokenizer.getValue());
                tokenizer.nextToken();
                break;
            case VARIABLE:
                res = new Variable<T>(tokenizer.getName());
                tokenizer.nextToken();
                break;
            case NEGATIVE:
                res = new Negative<T>(unary());
                break;
            case COUNT:
                res = new Count<T>(unary());
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

    private IExpression<T> mulDivMod() throws ParserException {
        var res = unary();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case MUL:
                    res = new Multiply<T>(res, unary());
                    break;
                case DIV:
                    res = new Divide<T>(res, unary());
                    break;
                case MOD:
                    res = new Mod<T>(res, unary());
                    break;
                default:
                    return res;
            }
        }
    }

    private IExpression<T> addSub() throws ParserException {
        var res = mulDivMod();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case ADD:
                    res = new Add<T>(res, mulDivMod());
                    break;
                case SUB:
                    res = new Subtract<T>(res, mulDivMod());
                    break;
                default:
                    return res;
            }
        }
    }

    private IExpression<T> minMax() throws ParserException {
        var res = addSub();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case MIN:
                    res = new Min<T>(res, addSub());
                    break;
                case MAX:
                    res = new Max<T>(res, addSub());
                    break;
                default:
                    return res;
            }
        }
    }

    @Override
    public IExpression<T> parse(final String s) throws ParserException {
        this.tokenizer = new Tokenizer<T>(s);
        var exp = minMax();
        if (tokenizer.getToken() != Token.EOF)
            throw new ParserException(tokenizer.getExpression(),
                    " Unknown symbol",
                    tokenizer.getPosition());
        return exp;
    }
}
