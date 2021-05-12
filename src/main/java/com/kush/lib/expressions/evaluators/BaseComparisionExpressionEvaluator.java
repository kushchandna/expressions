package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.Type.BOOLEAN;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.newMutableValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.nullValue;

import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.commons.ComparisionExpression;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.factory.MutableTypedValue;

/**
 * null compared with null is null
 * null compared with non-null is also null
 */
abstract class BaseComparisionExpressionEvaluator<E extends ComparisionExpression, T> extends BaseExpressionEvaluator<E, T> {

    private final ExpressionEvaluator<T> leftExprEvaluator;
    private final ExpressionEvaluator<T> rightExprEvaluator;

    private final MutableTypedValue evaluatedResult;

    public BaseComparisionExpressionEvaluator(E expression, ExpressionEvaluatorFactory<T> evaluatorFactory)
            throws ExpressionException {
        super(expression);
        leftExprEvaluator = evaluatorFactory.create(expression.getLeft());
        rightExprEvaluator = evaluatorFactory.create(expression.getRight());
        validateSameTypeOnBothSides(leftExprEvaluator, rightExprEvaluator, "EQUALS");
        evaluatedResult = newMutableValue(BOOLEAN);
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        TypedValue leftValue = leftExprEvaluator.evaluate(object);
        TypedValue rightValue = rightExprEvaluator.evaluate(object);
        if (leftValue.isNull() || rightValue.isNull()) {
            return nullValue(evaluatedResult);
        }
        return value(evaluateNonNullComparision(leftValue, rightValue), evaluatedResult);
    }

    @Override
    public Type evaluateType() {
        return BOOLEAN;
    }

    protected abstract boolean evaluateNonNullComparision(TypedValue leftValue, TypedValue rightValue);
}
