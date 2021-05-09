package com.kush.lib.expressions.utils;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;

public class ExpressionUtils {

    public static boolean isConstant(Expression expression, ExpressionEvaluatorFactory<?> evalFactory)
            throws ExpressionException {
        ExpressionEvaluator<?> evaluator = evalFactory.create(expression);
        return evaluator.getConstantValue().isPresent();
    }
}
