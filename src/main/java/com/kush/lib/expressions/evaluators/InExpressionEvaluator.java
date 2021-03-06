package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.Type.BOOLEAN;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.newMutableValue;

import java.util.ArrayList;
import java.util.Collection;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.clauses.InExpression;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.factory.MutableTypedValue;

class InExpressionEvaluator<T> implements ExpressionEvaluator<T> {

    private final ExpressionEvaluator<T> targetEvaluator;
    private final Collection<ExpressionEvaluator<T>> inExprEvaluators;

    private final MutableTypedValue evaluatedResult;

    public InExpressionEvaluator(InExpression inExpression, ExpressionEvaluatorFactory<T> exprEvalFactory)
            throws ExpressionException {
        targetEvaluator = exprEvalFactory.create(inExpression.getTarget());
        Collection<Expression> inExpressions = inExpression.getInExpressions();
        inExprEvaluators = new ArrayList<>(inExpressions.size());
        for (Expression inExpr : inExpressions) {
            ExpressionEvaluator<T> evaluator = exprEvalFactory.create(inExpr);
            inExprEvaluators.add(evaluator);
        }
        evaluatedResult = newMutableValue(BOOLEAN);
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        TypedValue targetValue = targetEvaluator.evaluate(object);
        for (ExpressionEvaluator<T> inExprEval : inExprEvaluators) {
            TypedValue inExprValue = inExprEval.evaluate(object);
            if (targetValue.equals(inExprValue)) {
                return value(true, evaluatedResult);
            }
        }
        return value(false, evaluatedResult);
    }

    @Override
    public Type evaluateType() throws ExpressionException {
        return BOOLEAN;
    }
}
