package main.java.expression.parser;


import main.java.expression.binary.*;
import main.java.expression.Const;
import main.java.expression.exceptions.ParserException;
import main.java.expression.operations.Operation;
import main.java.expression.tokenizer.Token;
import main.java.expression.tokenizer.Tokenizer;
import main.java.expression.TripleExpression;
import main.java.expression.unary.*;
import main.java.expression.Variable;

public class ExpressionParser<T> implements Parser<T> {
    private Tokenizer<T> tokenizer = null;
    private Operation<T> operation;

    public ExpressionParser(final Operation<T> operation) {
        this.operation = operation;
    }

    private TripleExpression<T> unary() throws ParserException {
        tokenizer.nextToken();
        TripleExpression<T> res;
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
                res = new Negative<T>(unary(), operation);
                break;
            case ABS:
                res = new Abs<T>(unary(), operation);
                break;
            case SQR:
                res = new Sqr<T>(unary(), operation);
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

    private TripleExpression<T> mulDivMod() throws ParserException {
        var res = unary();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case MUL:
                    res = new Multiply<T>(res, unary(), operation);
                    break;
                case DIV:
                    res = new Divide<T>(res, unary(), operation);
                    break;
                case MOD:
                    res = new Mod<T>(res, unary(), operation);
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression<T> addSub() throws ParserException {
        var res = mulDivMod();
        for (; ; ) {
            switch (tokenizer.getToken()) {
                case ADD:
                    res = new Add<T>(res, mulDivMod(), operation);
                    break;
                case SUB:
                    res = new Substract<T>(res, mulDivMod(), operation);
                    break;
                default:
                    return res;
            }
        }
    }

    @Override
    public TripleExpression<T> parse(final String s) throws ParserException {
        tokenizer = new Tokenizer<T>(s, operation);
        var exp = addSub();
        if (tokenizer.getToken() != Token.EOF)
            throw new ParserException(tokenizer.getExpression(),
                    " Unknown symbol",
                    tokenizer.getPosition());
        return exp;
    }
}
