package com.kush.lib.expressions.functions;

public interface FunctionSpec {

    String getFunctionName();

    Class<?> getReturnType();

    FunctionExecutor getFunctionExecutor();
}
