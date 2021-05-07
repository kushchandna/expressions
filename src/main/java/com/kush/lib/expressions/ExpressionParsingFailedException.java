package com.kush.lib.expressions;

public class ExpressionParsingFailedException extends ExpressionException {

    private static final long serialVersionUID = 1L;

    public ExpressionParsingFailedException() {
    }

    public ExpressionParsingFailedException(String message, Exception e) {
        super(message, e);
    }
}
