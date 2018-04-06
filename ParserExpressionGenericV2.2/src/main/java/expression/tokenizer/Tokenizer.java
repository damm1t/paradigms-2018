package main.java.expression.tokenizer;

import main.java.expression.IExpression;
import main.java.expression.exceptions.ConstException;
import main.java.expression.exceptions.ParserException;
import main.java.expression.exceptions.TokenizerException;
import main.java.expression.operations.Operation;
import main.java.expression.parser.INumericParser;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class Tokenizer<T> {
    private String expression;
    private int position = 0;
    private String value;
    private String name;
    private Token curToken = Token.MISTAKE;
    private Map<String, Token> hashMap = new LinkedHashMap<>();

    public Tokenizer(String s) {
        expression = s;
        hashMap.put("+", Token.ADD);
        hashMap.put("//", Token.LOG);
        hashMap.put("**", Token.POW);
        hashMap.put("*", Token.MUL);
        hashMap.put("/", Token.DIV);
        hashMap.put("(", Token.OPEN_BRACE);
        hashMap.put(")", Token.CLOSE_BRACE);
        hashMap.put("mod", Token.MOD);
        hashMap.put("min", Token.MIN);
        hashMap.put("max", Token.MAX);
        hashMap.put("count", Token.COUNT);
        hashMap.put("abs", Token.ABS);
        hashMap.put("sqr", Token.SQR);
    }

    private void skipWhiteSpace() {
        while (position < expression.length() && Character.isWhitespace(expression.charAt(position))) {
            position++;
        }
    }

    private String getNumber() throws ParserException {
        var l = position;
        while (position < expression.length() && Character.isDigit(expression.charAt(position))) {
            position++;
        }
        var r = position;
        var strNum = expression.substring(l, r);

        if (strNum.length() > 10) {
            throw new ParserException(strNum + " negative Integer value");
        }
        return strNum;
    }

    public Token getToken() {
        return curToken;
    }

    public String getExpression() {
        return expression;
    }

    public int getPosition() {
        return position;
    }

    public String getValue() throws ParserException {
        if (value == null)
            throw new ParserException("Current token " + curToken + " is negative digit");
        return value;
    }

    public String getName() throws ParserException {
        if (name == null)
            throw new ParserException("Current token " + curToken + " is negative variable");
        return name;
    }


    public void nextToken() throws ParserException {
        name = null;
        value = null;

        skipWhiteSpace();
        if (position >= expression.length()) {
            curToken = Token.EOF;
            return;
        }
        var c = expression.charAt(position);
        if (Character.isDigit(c)) {
            var curPos = position;
            value = getNumber();

            curToken = Token.NUMBER;
            return;
        }
        if (c == '-') {
            var curPos = position;
            if (curToken == Token.NUMBER || curToken == Token.VARIABLE || curToken == Token.CLOSE_BRACE) {
                curToken = Token.SUB;
                position++;
            } else {
                if (position + 1 >= expression.length()) {
                    throw new TokenizerException(expression, "Unexpected " + c + " at the end of expression", curPos);
                }
                if (Character.isDigit(expression.charAt(position + 1))) {
                    position++;
                    String temp = getNumber();
                    value = "-" + temp;
                    curToken = Token.NUMBER;
                } else {
                    curToken = Token.NEGATIVE;
                    position++;
                }
            }
            return;
        }

        var ch = String.valueOf(expression.charAt(position));

        for (Map.Entry entry : hashMap.entrySet()) {
            var key = String.valueOf(entry.getKey());
            var length = key.length();
            if (position + length <= expression.length()
                    && expression.substring(position, position + length).equals(key)) {

                curToken = (Token) entry.getValue();
                if (curToken == Token.VARIABLE) {
                    name = ch;
                }
                position += length;
                return;
            }
        }
        if (Character.isLetter(c)) {
            var l = position;
            while (position < expression.length() && Character.isLetter(expression.charAt(position))) {
                position++;
            }
            var r = position;
            if (position == expression.length()) {
                position++;
            }
            name = expression.substring(l, r);
            curToken = Token.VARIABLE;
            if (!name.equals("x") && !name.equals("y") && !name.equals("z")) {
                throw new TokenizerException(expression, "Undefined variable " + name, l);
            }
            return;
        }
        curToken = Token.MISTAKE;
        position++;
    }
}
