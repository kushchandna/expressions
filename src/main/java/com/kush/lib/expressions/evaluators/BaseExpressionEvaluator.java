package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.ExpressionException.exceptionWithMessage;
import static java.util.Collections.singleton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.types.Type;

abstract class BaseExpressionEvaluator<E extends Expression, T> implements ExpressionEvaluator<T> {

    protected final E expression;

    public BaseExpressionEvaluator(E expression) {
        this.expression = expression;
    }

    // utility methods

    protected final void validateSameTypeOnBothSides(ExpressionEvaluator<T> leftExprEvaluator,
            ExpressionEvaluator<T> rightExprEvaluator, String operation) throws ExpressionException {
        Type leftType = leftExprEvaluator.evaluateType();
        Type rightType = rightExprEvaluator.evaluateType();
        validateSameTypeOnBothSides(leftType, rightType, operation);
    }

    protected final void validateSameTypeOnBothSides(Type leftType, Type rightType, String operation) throws ExpressionException {
        if (leftType != rightType) {
            throw exceptionWithMessage("Both sides of an %s expression should be same, but got %s and %s",
                    operation, leftType, rightType);
        }
    }

    protected final void validateType(ExpressionEvaluator<T> expressionEvaluator, String operation,
            Type expressionType) throws ExpressionException {
        validateType(expressionEvaluator, operation, singleton(expressionType));
    }

    protected final void validateType(ExpressionEvaluator<T> expressionEvaluator, String operation,
            Collection<Type> allowedTypes) throws ExpressionException {
        Type type = expressionEvaluator.evaluateType();
        if (!allowedTypes.contains(type)) {
            throw exceptionWithMessage("%s operation can only accept types %s, but got %s",
                    operation, allowedTypes, type);
        }
    }

    protected final Collection<ExpressionEvaluator<T>> createEvaluators(ExpressionEvaluatorFactory<T> evaluatorFactory,
            Collection<Expression> expressions) throws ExpressionException {
        List<ExpressionEvaluator<T>> evaluators = new ArrayList<>();
        for (Expression argument : expressions) {
            evaluators.add(evaluatorFactory.create(argument));
        }
        return evaluators;
    }
}
