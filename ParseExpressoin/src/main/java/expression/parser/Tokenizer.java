package expression.parser;

import java.util.HashMap;
import java.util.Map;

public class Tokenizer {
    private String expression;
    private int position = 0;
    private int value;
    private String name;
    private Token curToken = Token.MISTAKE;
    //Map<String, Token> mapper = new HashMap<>();

    public Tokenizer(String s) {
        //mapper.put("", )
        expression = s;
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
        return value;
    }

    public String getName() {
        return name;
    }

    public void nextToken() {
        skipWhiteSpace();
        if (position >= expression.length()) {
            curToken = Token.EOF;
            return;
        }
        char ch = expression.charAt(position);
        switch (ch) {
            case '^':
                curToken = Token.XOR;
                break;
            case '&':
                curToken = Token.AND;
                break;
            case '|':
                curToken = Token.OR;
                break;
            case '-':
                curToken = Token.MINUS;
                break;
            case '+':
                curToken = Token.ADD;
                break;
            case '*':
                curToken = Token.MUL;
                break;
            case '/':
                curToken = Token.DIV;
                break;
            case '(':
                curToken = Token.OPEN_BRACE;
                break;
            case ')':
                curToken = Token.CLOSE_BRACE;
                break;
            default:
                if (Character.isDigit(ch)) {
                    int l = position;
                    while (position < expression.length() && Character.isDigit(expression.charAt(position))) {
                        position++;
                    }
                    int r = position;
                    if (position == expression.length()) {
                        position++;
                    }
                    value = Integer.parseUnsignedInt(expression.substring(l, r));
                    curToken = Token.NUMBER;
                    position--;
                } else if (ch == 'x' || ch == 'y' || ch == 'z') {
                    name = "" + ch;
                    curToken = Token.VARIABLE;
                } else if (expression.substring(position, position + 2).equals("<<")) {
                    curToken = Token.LEFT_SHIFT;
                    position++;
                } else if (expression.substring(position, position + 2).equals(">>")) {
                    curToken = Token.RIGHT_SHIFT;
                    position++;
                } else if (expression.substring(position, position + 3).equals("abs")) {
                    curToken = Token.ABS;
                    position += 2;
                } else if (expression.substring(position, position + 3).equals("mod")) {
                    curToken = Token.MOD;
                    position += 2;
                } else if (position + 6 <= expression.length() && expression.substring(position, position + 6).equals("square")) {
                    curToken = Token.SQUARE;
                    position += 5;
                } else {
                    curToken = Token.MISTAKE;
                }
        }
        position++;
    }
}
