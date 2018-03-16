package expression;

public class Const implements SuperExpression {
    private final int value;
    private final double doubleValue;

    public Const(int value) {
        this.value = value;
        this.doubleValue = value;
    }

    public Const(double value) {
        this.doubleValue = value;
        this.value = 0;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public double evaluate(double x) {
        return doubleValue;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
