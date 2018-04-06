package main.java.expression.generic;

import main.java.expression.TripleExpression;
import main.java.expression.exceptions.*;
import main.java.expression.operations.*;
import main.java.expression.parser.ExpressionParser;

import java.util.HashMap;


public class GenericTabulator implements Tabulator {
    private HashMap<String, Operation<?>> modes = new HashMap<>();

    public GenericTabulator() {
        modes.put("i", new IntegerOperation(true));
        modes.put("u", new IntegerOperation(false));
        modes.put("bi", new BigIntegerOperation());
        modes.put("b", new ByteOperation());
        modes.put("d", new DoubleOperation());
        modes.put("f", new FloatOperation());
    }

    public Object[][][] tabulate(String mode, String expression,
                                 int x1, int x2,
                                 int y1, int y2,
                                 int z1, int z2) throws ModeException {
        return makeTable(getOperation(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private Operation<?> getOperation(String mode) throws ModeException {
        var result = modes.get(mode);
        if (result == null) {
            throw new ModeException(mode);
        }
        return result;
    }

    private <T> Object[][][] makeTable(Operation<T> operation, String expression,
                                       int x1, int x2,
                                       int y1, int y2,
                                       int z1, int z2) {
        var x = x2 - x1 + 1;
        var y = y2 - y1 + 1;
        var z = z2 - z1 + 1;
        var result = new Object[x][y][z];
        var parser = new ExpressionParser<T>(operation);
        TripleExpression<T> exp;
        try {
            exp = parser.parse(expression);
        } catch (ParserException e) {
            return result;
        }
        for (var i = 0; i < x; ++i) {
            for (var j = 0; j < y; ++j) {
                for (var k = 0; k < z; ++k) {
                    try {
                        result[i][j][k] = exp.evaluate(operation.parseNumber(Integer.toString(i + x1)),
                                                        operation.parseNumber(Integer.toString(j + y1)),
                                                         operation.parseNumber(Integer.toString(k + z1)));
                    }
                    catch (MathException e) {
                        result[i][j][k] = null;
                    }
                }
            }
        }
        return result;
    }
}