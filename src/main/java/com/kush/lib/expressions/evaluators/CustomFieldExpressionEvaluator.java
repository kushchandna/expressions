package com.kush.lib.expressions.evaluators;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.clauses.CustomFieldExpression;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

public class CustomFieldExpressionEvaluator<T> extends BaseExpressionEvaluator<CustomFieldExpression, T> {

    private final ExpressionEvaluator<T> formulaEvaluator;
    private final Type fieldType;

    public CustomFieldExpressionEvaluator(CustomFieldExpression expression, ExpressionEvaluatorFactory<T> evalFactory)
            throws ExpressionException {
        super(expression);
        Expression formula = expression.getFormula();
        formulaEvaluator = evalFactory.create(formula);
        fieldType = formulaEvaluator.evaluateType();
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        return formulaEvaluator.evaluate(object);
    }

    @Override
    public Type evaluateType() throws ExpressionException {
        return fieldType;
    }
}
