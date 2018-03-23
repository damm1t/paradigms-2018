package main.java.expression.Tokenizer;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tokenizer {
    private String expression;
    private int position = 0;
    private Integer value;
    private String name;
    private Token curToken = Token.MISTAKE;
    Map<String, Token> hashMap = new LinkedHashMap<>();

    public Tokenizer(String s) {
        expression = s;
        hashMap.put("~", Token.NOT);
        hashMap.put("count", Token.COUNT);
        hashMap.put("^", Token.XOR);
        hashMap.put("&", Token.AND);
        hashMap.put("|", Token.OR);
        hashMap.put("-", Token.MINUS);
        hashMap.put("+", Token.ADD);
        hashMap.put("*", Token.MUL);
        hashMap.put("/", Token.DIV);
        hashMap.put("(", Token.OPEN_BRACE);
        hashMap.put(")", Token.CLOSE_BRACE);
        hashMap.put("<<", Token.LEFT_SHIFT);
        hashMap.put(">>", Token.RIGHT_SHIFT);
        hashMap.put("abs", Token.ABS);
        hashMap.put("mod", Token.MOD);
        hashMap.put("square", Token.SQUARE);
    }

    private void skipWhiteSpace() {
        while (position < expression.length() && Character.isWhitespace(expression.charAt(position))) {
            position++;
        }
    }

    public Token getToken() {
        return curToken;
    }

    public int getPosition() {
        return position;
    }

    public int getValue() {
        if (value == null)
            throw new TokenizerException("Current token is not digit");
        return value;
    }

    public String getName() {
        if (name == null)
            throw new TokenizerException("Current token is not variable");
        return name;
    }

    public void nextToken() {
        name = null;
        value = null;

        skipWhiteSpace();
        if (position >= expression.length()) {
            curToken = Token.EOF;
            return;
        }
        var c = expression.charAt(position);
        if (Character.isDigit(c)) {
            var l = position;
            while (position < expression.length() && Character.isDigit(expression.charAt(position))) {
                position++;
            }
            var r = position;
            if (position == expression.length()) {
                position++;
            }
            var strNum = expression.substring(l, r);
            if(strNum.length() > 10 || Integer.MIN_VALUE > Long.parseLong(strNum) + 1 || Long.parseLong(strNum) - 1 > Integer.MAX_VALUE){
                throw new TokenizerException(strNum + " not Integer value");
            }
            value = Integer.parseUnsignedInt(expression.substring(l, r));
            curToken = Token.NUMBER;
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
           /* if(!name.equals("x") && !name.equals("y") && !name.equals("z")){
                throw new TokenizerException("Undefined variable " + name);
            }*/
            return;
        }
        curToken = Token.MISTAKE;
        position++;
    }
}
