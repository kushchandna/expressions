package com.kush.lib.expressions.evaluators;

import java.util.ArrayList;
import java.util.List;

import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.clauses.FunctionExpression;
import com.kush.lib.expressions.functions.FunctionExecutor;
import com.kush.lib.expressions.functions.FunctionSpec;
import com.kush.lib.expressions.functions.FunctonSpecRepository;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

// TODO avoid creation of new TypedValue instance on every evaluate call
public class FunctionExpressionEvaluator<T> extends BaseExpressionEvaluator<FunctionExpression, T> {

    private final Type returnType;
    private final FunctionExecutor functionExecutor;
    private final List<ExpressionEvaluator<T>> argEvaluators;

    public FunctionExpressionEvaluator(FunctionExpression expression, ExpressionEvaluatorFactory<T> evaluatorFactory,
            FunctonSpecRepository repository) throws ExpressionException {
        super(expression);

        FunctionSpec functionSpec = repository.getFunctionSpec(expression.getFunctionName());
        Class<?> returnTypeClass = functionSpec.getReturnType();
        returnType = Type.forClass(returnTypeClass);
        argEvaluators = new ArrayList<>(createEvaluators(evaluatorFactory, expression.getArguments()));
        functionExecutor = functionSpec.getFunctionExecutor();
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        List<TypedValue> arguments = new ArrayList<>(argEvaluators.size());
        for (ExpressionEvaluator<T> argEvaluator : argEvaluators) {
            arguments.add(argEvaluator.evaluate(object));
        }
        return functionExecutor.execute(arguments.toArray(new TypedValue[arguments.size()]));
    }

    @Override
    public Type evaluateType() throws ExpressionException {
        return returnType;
    }
}
