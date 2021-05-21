package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.ExpressionException.exceptionWithMessage;
import static com.kush.lib.expressions.evaluators.ConstantValueEvaluator.withConstantValue;
import static com.kush.lib.expressions.evaluators.ConstantValueEvaluator.withNull;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;

import java.util.ArrayList;
import java.util.List;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.clauses.CaseExpression;
import com.kush.lib.expressions.clauses.CaseExpression.Branch;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

public class CaseExpressionEvaluator<T> extends BaseExpressionEvaluator<CaseExpression, T> {

    private final ExpressionEvaluator<T> referenceEvaluator;
    private final List<BranchEvaluator<T>> branchEvaluators;
    private final ExpressionEvaluator<T> defaultEvaluator;

    private Type returnType;

    public CaseExpressionEvaluator(CaseExpression expression, ExpressionEvaluatorFactory<T> evalFactory)
            throws ExpressionException {
        super(expression);

        Expression reference = expression.getReference();
        referenceEvaluator = reference != null
                ? evalFactory.create(reference)
                : withConstantValue(value(true));

        List<Branch> branches = expression.getBranches();
        branchEvaluators = new ArrayList<>();
        for (Branch branch : branches) {
            BranchEvaluator<T> evaluator = new BranchEvaluator<>();
            evaluator.entryEvaluator = evalFactory.create(branch.getEntry());
            evaluator.returnEvaluator = evalFactory.create(branch.getResult());

            Type thisReturnType = evaluator.returnEvaluator.evaluateType();
            matchReturnTypeIfSetOrSet(thisReturnType);
            branchEvaluators.add(evaluator);
        }
        Expression defaultReturn = expression.getDefaultBranch();
        if (defaultReturn != null) {
            defaultEvaluator = evalFactory.create(defaultReturn);
            Type defaultValueType = defaultEvaluator.evaluateType();
            matchReturnTypeIfSetOrSet(defaultValueType);
        } else {
            if (returnType == null) {
                returnType = Type.OBJECT;
            }
            defaultEvaluator = withNull(returnType);
        }
    }

    @Override
    public TypedValue evaluate(T object) throws ExpressionException {
        TypedValue reference = referenceEvaluator.evaluate(object);
        for (BranchEvaluator<T> branchEvaluator : branchEvaluators) {
            TypedValue entry = branchEvaluator.entryEvaluator.evaluate(object);
            if (reference.equals(entry)) {
                return branchEvaluator.returnEvaluator.evaluate(object);
            }
        }
        return defaultEvaluator.evaluate(object);
    }

    @Override
    public Type evaluateType() throws ExpressionException {
        return returnType;
    }

    private void matchReturnTypeIfSetOrSet(Type type) throws ExpressionException {
        if (returnType != null && returnType != type) {
            throw exceptionWithMessage("All branches of case statement should return value of same type");
        } else {
            returnType = type;
        }
    }

    private static class BranchEvaluator<T> {
        ExpressionEvaluator<T> entryEvaluator;
        ExpressionEvaluator<T> returnEvaluator;
    }
}
