package main.java.expression.Tokenizer;

import main.java.expression.Exceptions.TokenizerException;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Tokenizer {
    private String expression;
    private int position = 0;
    private Number value;
    private String name;
    private Token curToken = Token.MISTAKE;
    private Map<String, Token> hashMap = new LinkedHashMap<>();
    private Set<Token> operations = EnumSet.of(Token.NEGATIVE, Token.ABS, Token.ADD, Token.SUB, Token.MUL, Token.DIV);
    private Set<Token> BINARY_OPERATIONS = EnumSet.of(Token.ADD, Token.SUB, Token.MUL, Token.DIV);

    public Tokenizer(String s) {
        expression = s;
        hashMap.put("+", Token.ADD);
        hashMap.put("*", Token.MUL);
        hashMap.put("/", Token.DIV);
        hashMap.put("(", Token.OPEN_BRACE);
        hashMap.put(")", Token.CLOSE_BRACE);
        hashMap.put("abs", Token.ABS);
        hashMap.put("mod", Token.MOD);
        hashMap.put("square", Token.SQUARE);
    }

    private void skipWhiteSpace() {
        while (position < expression.length() && Character.isWhitespace(expression.charAt(position))) {
            position++;
        }
    }

    private String getNumber() throws Exception {
        var l = position;
        while (position < expression.length() && Character.isDigit(expression.charAt(position))) {
            position++;
        }
        var r = position;
        var strNum = expression.substring(l, r);

        if(strNum.length() > 10){
            throw new TokenizerException(strNum + " not Integer value");
        }
        return strNum;
    }

    public Token getToken() {
        return curToken;
    }

    public int getPosition() {
        return position;
    }

    public Number getValue() throws Exception {
        if (value == null)
            throw new TokenizerException("Current token is not digit");
        return value;
    }

    public String getName() throws Exception {
        if (name == null)
            throw new TokenizerException("Current token is not variable");
        return name;
    }

/*    private void checkForOperand(int pos) throws MissOperandException {
        if (operations.contains(curToken) || curToken == Token.OPEN_BRACE || curToken == Token.MISTAKE) {
            throw new MissOperandException(expression, pos);
        }
    }

    private void checkForOperation(int pos) throws MissOperationException {
        if (curToken == Token.CLOSE_BRACE || curToken == Token.VARIABLE || curToken == Token.NUMBER) {
            throw new MissOperationException(expression, pos);
        }
    }*/

    public void nextToken() throws Exception {
        name = null;
        value = null;

        skipWhiteSpace();
        if (position >= expression.length()) {
            curToken = Token.EOF;
            return;
        }
        var c = expression.charAt(position);
        if (Character.isDigit(c)) {
            var strNum = getNumber();
            value = Integer.parseUnsignedInt(strNum);
            if(value.intValue() < 0){
                throw new TokenizerException("Inncorrect Const " + strNum);
            }
            curToken = Token.NUMBER;
            return;
        }
        if (c == '-') {
            if (curToken == Token.NUMBER || curToken == Token.VARIABLE || curToken == Token.CLOSE_BRACE) {
                curToken = Token.SUB;
                position++;
            } else {
                if (position + 1 >= expression.length()) {
                    throw new TokenizerException("expression failed on " + (position + 1));
                }
                if (Character.isDigit(expression.charAt(position + 1))) {
                    position++;
                    String temp = getNumber();
                    try {
                        value = Integer.parseInt("-" + temp);
                    } catch (NumberFormatException e) {
                        throw new TokenizerException("Inncorrect Const -" + temp);
                    }
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
                throw new TokenizerException("Undefined variable " + name);
            }
            return;
        }
        curToken = Token.MISTAKE;
        position++;
    }
}
