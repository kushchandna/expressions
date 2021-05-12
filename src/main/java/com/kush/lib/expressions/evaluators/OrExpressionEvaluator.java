package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.Type.BOOLEAN;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.newMutableValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.nullValue;

import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.clauses.OrExpression;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.factory.MutableTypedValue;

/**
 * true OR true = true
 * true OR false = true
 * true OR null = true
 *
 * false OR true = true
 * false OR false = false
 * false OR null = null
 *
 * null OR true = true
 * null OR false = null
 * null OR null = null
 */
class OrExpressionEvaluator<T> extends BaseExpressionEvaluator<OrExpression, T> {

    private final ExpressionEvaluator<T> leftExprEvaluator;
    private final ExpressionEvaluator<T> rightExprEvaluator;

    private final MutableTypedValue evaluatedResult;

    public OrExpressionEvaluator(OrExpression expression, ExpressionEvaluatorFactory<T> evaluatorFactory)
            throws ExpressionException {
        super(expression);
        leftExprEvaluator = evaluatorFactory.create(expression.getLeft());
        validateType(leftExprEvaluator, BOOLEAN, "OR");
        rightExprEvaluator = evaluatorFactory.create(expression.getRight());
        validateType(rightExprEvaluator, BOOLEAN, "OR");
        evaluatedResult = newMutableValue(BOOLEAN);
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        TypedValue leftValue = leftExprEvaluator.evaluate(object);
        boolean leftResult = leftValue.getBoolean();
        if (!leftValue.isNull() && leftResult == true) {
            // left != null && left == true
            return value(true, evaluatedResult);
        }

        // left == null || left == false
        TypedValue rightValue = rightExprEvaluator.evaluate(object);
        boolean rightResult = rightValue.getBoolean();
        if (leftValue.isNull() && rightResult != true) {
            // left == null
            return nullValue(evaluatedResult);
        }

        // left == true
        return rightValue;
    }

    @Override
    public Type evaluateType() {
        return BOOLEAN;
    }
}
