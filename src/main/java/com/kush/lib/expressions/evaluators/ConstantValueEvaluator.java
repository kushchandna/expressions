package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.nullValue;

import java.util.Optional;

import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

public class ConstantValueEvaluator<T> implements ExpressionEvaluator<T> {

    private final TypedValue constantValue;

    public static <T> ExpressionEvaluator<T> withConstantValue(TypedValue value) {
        return new ConstantValueEvaluator<>(value);
    }

    public static <T> ExpressionEvaluator<T> withNull(Type type) {
        return withConstantValue(nullValue(type));
    }

    private ConstantValueEvaluator(TypedValue constantValue) {
        this.constantValue = constantValue;
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        return constantValue;
    }

    @Override
    public Type evaluateType() throws ExpressionException {
        return constantValue.getType();
    }

    @Override
    public Optional<TypedValue> getConstantValue() throws ExpressionException {
        return Optional.of(constantValue);
    }
}
