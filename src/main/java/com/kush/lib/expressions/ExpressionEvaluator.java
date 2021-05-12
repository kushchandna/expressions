package com.kush.lib.expressions;

import java.util.Optional;

import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

public interface ExpressionEvaluator<T> {

    /**
     * @param object
     * @return returned typed value may get updated on subsequent evaluate calls
     * @throws ExpressionException
     */
    TypedValue evaluate(T object) throws ExpressionException;

    Type evaluateType() throws ExpressionException;

    default TypedValue evaluateAndClone(T object) throws ExpressionException {
        return evaluate(object).clone();
    }

    default Optional<TypedValue> getConstantValue() throws ExpressionException {
        return Optional.empty();
    }
}
