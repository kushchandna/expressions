package com.kush.lib.expressions.functions;

import com.kush.lib.expressions.types.TypedValue;

public interface FunctionExecutor {

    TypedValue execute(TypedValue... arguments);
}
