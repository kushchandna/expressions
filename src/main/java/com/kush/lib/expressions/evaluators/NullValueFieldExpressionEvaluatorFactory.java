package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.Type.STRING;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.nullValue;

import java.util.Optional;

import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.clauses.FieldExpression;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

public class NullValueFieldExpressionEvaluatorFactory<T> implements FieldExpressionEvaluatorFactory<T> {

    @Override
    public FieldExpressionEvaluator<T> create(FieldExpression expression) throws ExpressionException {
        return new NullValueFieldExpressionEvaluator<>();
    }

    private static class NullValueFieldExpressionEvaluator<T> implements FieldExpressionEvaluator<T> {

        private static final TypedValue NULL_VALUE = nullValue(STRING);

        @Override
        public TypedValue evaluate(T object) throws ExpressionException {
            return NULL_VALUE;
        }

        @Override
        public Type evaluateType() throws ExpressionException {
            return STRING;
        }

        @Override
        public Optional<TypedValue> getConstantValue() throws ExpressionException {
            return Optional.of(NULL_VALUE);
        }
    }
}
