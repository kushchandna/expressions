package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;

import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.clauses.AdditionExpression;
import com.kush.lib.expressions.handler.TypeHandler;

class AdditionExpressionEvaluator<T> extends BaseArithmeticExpressionEvaluator<AdditionExpression, T> {

    public AdditionExpressionEvaluator(AdditionExpression expression, ExpressionEvaluatorFactory<T> evaluatorFactory)
            throws ExpressionException {
        super(expression, evaluatorFactory, "Addition");
    }

    @Override
    protected TypeHandler<ArithmeticOperationPerformer> getArithematicOperationPerformerGetter() {
        return new ArithmeticOperationPerformerGetter() {

            @Override
            protected ArithmeticOperationPerformer handleString() throws ExpressionException {
                return unsupportedOperation();
            }

            @Override
            protected ArithmeticOperationPerformer handleLong() throws ExpressionException {
                return (val1, val2) -> value(val1.getLong() + val2.getLong());
            }

            @Override
            protected ArithmeticOperationPerformer handleInteger() throws ExpressionException {
                return (val1, val2) -> value(val1.getInt() + val2.getInt());
            }

            @Override
            protected ArithmeticOperationPerformer handleFloat() throws ExpressionException {
                return (val1, val2) -> value(val1.getFloat() + val2.getFloat());
            }

            @Override
            protected ArithmeticOperationPerformer handleDouble() throws ExpressionException {
                return (val1, val2) -> value(val1.getDouble() + val2.getDouble());
            }

            @Override
            protected ArithmeticOperationPerformer handleBoolean() throws ExpressionException {
                return unsupportedOperation();
            }
        };
    }
}
