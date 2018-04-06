package main.java.expression.exceptions;

public class OverflowException extends MathException {
    private Integer valueOverflow;
    public OverflowException(Integer value) {
        super("overflow");
        valueOverflow = value;
    }

    public Integer getValueOverflow() {
        return valueOverflow;
    }
}
