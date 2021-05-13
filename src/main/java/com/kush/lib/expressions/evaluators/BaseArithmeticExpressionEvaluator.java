package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.ExpressionException.exceptionWithMessage;

import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.commons.BinomialExpression;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.handlers.TypeHandler;

abstract class BaseArithmeticExpressionEvaluator<E extends BinomialExpression, T> extends BaseExpressionEvaluator<E, T> {

    private final ExpressionEvaluator<T> leftEvaluator;
    private final ExpressionEvaluator<T> rightEvaluator;
    private final Type compatibleType;
    private final ArithmeticOperationPerformer operationPerformer;
    private final String operation;

    public BaseArithmeticExpressionEvaluator(E expression, ExpressionEvaluatorFactory<T> evaluatorFactory, String operation)
            throws ExpressionException {
        super(expression);
        this.operation = operation;
        leftEvaluator = evaluatorFactory.create(expression.getLeft());
        rightEvaluator = evaluatorFactory.create(expression.getRight());

        Type leftType = leftEvaluator.evaluateType();
        Type rightType = rightEvaluator.evaluateType();
        validateSameTypeOnBothSides(leftType, rightType, operation);

        compatibleType = getNarrowestCompatibleType(leftType, rightType);
        operationPerformer = getArithematicOperationPerformerGetter().handle(compatibleType);
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        return operationPerformer.performOperation(leftEvaluator.evaluate(object), rightEvaluator.evaluate(object));
    }

    @Override
    public Type evaluateType() throws ExpressionException {
        return compatibleType;
    }

    private Type getNarrowestCompatibleType(Type type1, Type type2) {
        return type1;
    }

    protected abstract TypeHandler<ArithmeticOperationPerformer> getArithematicOperationPerformerGetter();

    protected interface ArithmeticOperationPerformer {

        TypedValue performOperation(TypedValue value1, TypedValue value2) throws ExpressionException;
    }

    protected abstract class ArithmeticOperationPerformerGetter extends TypeHandler<ArithmeticOperationPerformer> {

        protected final ArithmeticOperationPerformer unsupportedOperation() {
            return new ArithmeticOperationPerformer() {

                @Override
                public TypedValue performOperation(TypedValue value1, TypedValue value2) throws ExpressionException {
                    throw exceptionWithMessage("Operation %s is not supported on types %s and %s", operation, value1.getType(),
                            value2.getType());
                }
            };
        }
    }
}
