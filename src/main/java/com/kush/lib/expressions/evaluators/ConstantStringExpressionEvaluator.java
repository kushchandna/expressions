package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.Type.STRING;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;

import com.kush.lib.expressions.clauses.ConstantStringExpression;
import com.kush.lib.expressions.types.TypedValue;

class ConstantStringExpressionEvaluator<T> extends BaseConstantExpressionEvaluator<ConstantStringExpression, T> {

    public ConstantStringExpressionEvaluator(ConstantStringExpression expression) {
        super(expression, STRING);
    }

    @Override
    protected TypedValue evaluateConstantValue(ConstantStringExpression expression) {
        return value(expression.getValue());
    }
}
