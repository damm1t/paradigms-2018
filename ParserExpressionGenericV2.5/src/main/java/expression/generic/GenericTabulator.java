package main.java.expression.generic;

import main.java.expression.Const;
import main.java.expression.IExpression;
import main.java.expression.Visitors.*;
import main.java.expression.exceptions.*;
import main.java.expression.parser.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.function.Function;


public class GenericTabulator implements Tabulator {
   /* private HashMap<String, Visitor<?, ?, HashMap<String, Integer>>> convert = new HashMap<>();

    public class openVisitor<T, R, C> {
        public Visitor<T, R, C> f;

        openVisitor(Visitor<T, R, C> fn) {
            f = fn;
        }
    }

    public GenericTabulator() {
        convert.put("i", new IntegerVisitor());
        convert.put("bi", new BigIntegerVisitor());
        convert.put("d", new DoubleVisitor());
    }

    private Visitor<?, ?, ?> getVisitor(String mode) throws ModeException {
        var visitor = convert.get(mode);
        if(visitor == null)
            throw new ModeException("Unsupported type");
        return visitor;
    }*/

    public Object[][][] tabulate(String mode, String expression,
                                 int x1, int x2,
                                 int y1, int y2,
                                 int z1, int z2) throws ModeException {
        //return makeTable(getVisitor(mode), expression, x1, x2, y1, y2, z1, z2);
        if ("i".equals(mode)) {
            return makeTable(new IntegerVisitor(true), expression, x1, x2, y1, y2, z1, z2);
        } else if ("bi".equals(mode)) {
            return makeTable(new BigIntegerVisitor(), expression, x1, x2, y1, y2, z1, z2);

        } else if ("d".equals(mode)) {
            return makeTable(new DoubleVisitor(), expression, x1, x2, y1, y2, z1, z2);

        } else if ("l".equals(mode)) {
            return makeTable(new LongVisitor(), expression, x1, x2, y1, y2, z1, z2);

        } else if ("s".equals(mode)) {
            return makeTable(new ShortVisitor(), expression, x1, x2, y1, y2, z1, z2);

        } else if ("u".equals(mode)) {
            return makeTable(new IntegerVisitor(false), expression, x1, x2, y1, y2, z1, z2);

        } else throw new ModeException("Unsupported type");
    }

    private <T extends Number> Object[][][] makeTable(Visitor<T, T, HashMap<String, Integer>> visitor, String expression,
                                                      int x1, int x2,
                                                      int y1, int y2,
                                                      int z1, int z2) {
        var x = x2 - x1 + 1;
        var y = y2 - y1 + 1;
        var z = z2 - z1 + 1;
        var result = new Object[x][y][z];
        var parser = new ExpressionParser<T>();
        IExpression<T> exp;
        try {
            exp = parser.parse(expression);
        } catch (ParserException e) {
            return result;
        }
        for (var i = 0; i < x; ++i) {
            for (var j = 0; j < y; ++j) {
                for (var k = 0; k < z; ++k) {
                    try {
                        HashMap<String, Integer> map = new HashMap<>();
                        map.put("x", i + x1);
                        map.put("y", j + y1);
                        map.put("z", k + z1);
                        result[i][j][k] = exp.evaluate(visitor, map);
                    } catch (MathException e) {
                        result[i][j][k] = null;
                    }
                }
            }
        }
        return result;
    }
}