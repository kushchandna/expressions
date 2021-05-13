package com.kush.lib.expressions.functions;

public class FunctionExecutionFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    public FunctionExecutionFailedException() {
    }

    public FunctionExecutionFailedException(String message, Exception e) {
        super(message, e);
    }
}
