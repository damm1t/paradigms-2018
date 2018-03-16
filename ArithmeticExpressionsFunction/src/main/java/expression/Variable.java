package expression;

public class Variable implements SuperExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public int evaluate(int x, int y, int z) {
        assert (name.equals("x") || name.equals("y") || name.equals("z")) : "wrong variable";
        switch (name){
            case "x" : return x;
            case "y" : return y;
            case "z" : return z;
        }
        return 0;
    }

    public int evaluate(int x) {
        assert (name.equals("x")) : "wrong variable";
        return x;
    }

    public double evaluate(double x) {
        assert (name.equals("x")) : "wrong variable";
        return x;
    }

    public String toString() {
        return name;
    }
}
