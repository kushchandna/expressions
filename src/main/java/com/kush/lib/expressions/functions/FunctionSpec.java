package com.kush.lib.expressions.functions;

public interface FunctionSpec {

    Class<?> getReturnType();

    FunctionExecutor getFunctionExecutor();
}
