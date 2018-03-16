package expression;

public class Variable implements SuperExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public int evaluate(int x, int y, int z) {
        switch (name){
            case "x" : return x;
            case "y" : return y;
            case "z" : return z;
        }
        assert false : "wrong variable";
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
