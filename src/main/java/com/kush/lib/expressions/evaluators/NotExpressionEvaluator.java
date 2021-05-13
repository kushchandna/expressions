package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.Type.BOOLEAN;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.newMutableValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.nullValue;

import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.clauses.NotExpression;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.factory.MutableTypedValue;

/**
 * !null = null
 * !true = false
 * !false = true
 */
class NotExpressionEvaluator<T> extends BaseExpressionEvaluator<NotExpression, T> {

    private final ExpressionEvaluator<T> evaluator;

    private final MutableTypedValue evaluatedResult;

    public NotExpressionEvaluator(NotExpression expression, ExpressionEvaluatorFactory<T> evaluatorFactory)
            throws ExpressionException {
        super(expression);
        evaluator = evaluatorFactory.create(expression.getChild());
        validateType(evaluator, "NOT", BOOLEAN);
        evaluatedResult = newMutableValue(BOOLEAN);
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        TypedValue value = evaluator.evaluate(object);
        if (value.isNull()) {
            return nullValue(evaluatedResult);
        }
        return value(!value.getBoolean(), evaluatedResult);
    }

    @Override
    public Type evaluateType() {
        return BOOLEAN;
    }
}
