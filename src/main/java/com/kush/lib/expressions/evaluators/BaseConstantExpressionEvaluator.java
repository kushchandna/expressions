package com.kush.lib.expressions.evaluators;

import java.util.Optional;

import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.commons.ConstantExpression;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

abstract class BaseConstantExpressionEvaluator<E extends ConstantExpression, T> extends BaseExpressionEvaluator<E, T> {

    private final TypedValue constantValue;
    private final Type type;

    public BaseConstantExpressionEvaluator(E expression, Type type) {
        super(expression);
        this.type = type;
        constantValue = evaluateConstantValue(expression);
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        return constantValue;
    }

    @Override
    public Type evaluateType() {
        return type;
    }

    @Override
    public Optional<TypedValue> getConstantValue() throws ExpressionException {
        return Optional.of(evaluate(null));
    }

    protected abstract TypedValue evaluateConstantValue(E expression);
}
