package com.kush.lib.expressions;

import java.util.Optional;

import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

public interface ExpressionEvaluator<T> {

    TypedValue evaluate(T object) throws ExpressionException;

    Type evaluateType() throws ExpressionException;

    default Optional<TypedValue> getConstantValue() throws ExpressionException {
        return Optional.empty();
    }
}
