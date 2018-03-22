package expression.parser;

import expression.TripleExpression;
public class Main{
    public static void main(final String[] args) {
        String expression = "(2 - 2) - (2 - 2) << 262222222222222222222222222222222222222222222222222224";
        Parser parser = new ExpressionParser();
        TripleExpression exp = parser.parse(expression);
        System.out.println(exp.evaluate(0, 0, 0));
    }
}
