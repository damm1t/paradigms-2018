package main.java;


import main.java.expression.TripleExpression;
import main.java.expression.parser.ExpressionParser;
import main.java.expression.parser.Parser;

public class MainTest {
    public static void main(final String[] args) throws Exception {
        String expression = "(6 ^ 1 + abas)";
        var parser = new ExpressionParser();
        var exp = parser.parse(expression);
        try {
            System.out.println(exp.evaluate(0, 0, 0));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
