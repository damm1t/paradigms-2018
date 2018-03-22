package expression;

import expression.Binary.Multiply;
import expression.Binary.Subtract;

public class Main {
    public static void main(final String[] args) {
        //assert (args.length == 3) : "Incorrect input";
        int x = Integer.parseInt(args[0]), y = Integer.parseInt(args[1]), z = Integer.parseInt(args[2]);
        System.out.println(new Subtract(
            new Multiply(
                new Const(2),
                new Variable("x")
            ),
            new Const(3)
        ).evaluate(x, y, z));
/*
        System.out.println(new Subtract(
            new Multiply(
                new Const(2),
                new Variable("x")
            ),
            new Const(3)
        ).toString());*/
    }
}
