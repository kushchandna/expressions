package com.kush.lib.expressions.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.ExpressionFactory;
import com.kush.lib.expressions.ExpressionProcessor;
import com.kush.lib.expressions.clauses.AdditionExpression;
import com.kush.lib.expressions.clauses.AndExpression;
import com.kush.lib.expressions.clauses.CaseExpression;
import com.kush.lib.expressions.clauses.ConstantIntExpression;
import com.kush.lib.expressions.clauses.ConstantStringExpression;
import com.kush.lib.expressions.clauses.DivisionExpression;
import com.kush.lib.expressions.clauses.EqualsExpression;
import com.kush.lib.expressions.clauses.FieldExpression;
import com.kush.lib.expressions.clauses.FunctionExpression;
import com.kush.lib.expressions.clauses.GreaterThanEqualsExpression;
import com.kush.lib.expressions.clauses.GreaterThanExpression;
import com.kush.lib.expressions.clauses.InExpression;
import com.kush.lib.expressions.clauses.LessThanEqualsExpression;
import com.kush.lib.expressions.clauses.LessThanExpression;
import com.kush.lib.expressions.clauses.MultiplicationExpression;
import com.kush.lib.expressions.clauses.NotExpression;
import com.kush.lib.expressions.clauses.OrExpression;
import com.kush.lib.expressions.clauses.SubtractionExpression;

public class ExpressionCloner extends ExpressionProcessor<Expression> {

    private final ExpressionFactory expressionFactory;

    public ExpressionCloner(ExpressionFactory expressionFactory) {
        this.expressionFactory = expressionFactory;
    }

    @Override
    protected Expression handle(FieldExpression expression) {
        return expressionFactory.createFieldExpression(expression.getFieldName());
    }

    @Override
    protected Expression handle(AndExpression expression) throws ExpressionException {
        return expressionFactory.createAndExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(OrExpression expression) throws ExpressionException {
        return expressionFactory.createOrExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(NotExpression expression) throws ExpressionException {
        return expressionFactory.createNotExpression(process(expression.getChild()));
    }

    @Override
    protected Expression handle(EqualsExpression expression) throws ExpressionException {
        return expressionFactory.createEqualsExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(InExpression expression) throws ExpressionException {
        return expressionFactory.createInExpression(process(expression.getTarget()),
                processExpressions(expression.getInExpressions()));
    }

    @Override
    protected Expression handle(GreaterThanExpression expression) throws ExpressionException {
        return expressionFactory.createGreaterThanExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(GreaterThanEqualsExpression expression) throws ExpressionException {
        return expressionFactory.createGreaterThanEqualsExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(LessThanExpression expression) throws ExpressionException {
        return expressionFactory.createLessThanExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(LessThanEqualsExpression expression) throws ExpressionException {
        return expressionFactory.createLessThanEqualsExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(FunctionExpression expression) throws ExpressionException {
        return expressionFactory.createFunctionExpression(expression.getFunctionName(),
                processExpressions(expression.getArguments()));
    }

    @Override
    protected Expression handle(ConstantStringExpression expression) {
        return expressionFactory.createConstantStringExpression(expression.getValue());
    }

    @Override
    protected Expression handle(ConstantIntExpression expression) {
        return expressionFactory.createConstantIntExpression(expression.getValue());
    }

    @Override
    protected Expression handle(AdditionExpression expression) throws ExpressionException {
        return expressionFactory.createAdditionExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(SubtractionExpression expression) throws ExpressionException {
        return expressionFactory.createSubtractionExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(MultiplicationExpression expression) throws ExpressionException {
        return expressionFactory.createMultiplicationExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(DivisionExpression expression) throws ExpressionException {
        return expressionFactory.createDivisionExpression(process(expression.getLeft()), process(expression.getRight()));
    }

    @Override
    protected Expression handle(CaseExpression expression) throws ExpressionException {
        return expressionFactory.createCaseExpression(process(expression.getReference()), processBranches(expression),
                process(expression.getDefaultBranch()));
    }

    private List<CaseExpression.Branch> processBranches(CaseExpression expression) throws ExpressionException {
        List<CaseExpression.Branch> processedBranches = new ArrayList<>();
        for (CaseExpression.Branch branch : expression.getBranches()) {
            processedBranches.add(processBranches(branch));
        }
        return processedBranches;
    }

    private CaseExpression.Branch processBranches(CaseExpression.Branch branch) throws ExpressionException {
        Expression processedEntry = process(branch.getEntry());
        Expression processedReturn = process(branch.getResult());
        return new CaseExpression.Branch() {

            @Override
            public Expression getResult() {
                return processedReturn;
            }

            @Override
            public Expression getEntry() {
                return processedEntry;
            }
        };
    }

    private List<Expression> processExpressions(Collection<Expression> inExpressions) throws ExpressionException {
        List<Expression> processedExprList = new ArrayList<>(inExpressions.size());
        for (Expression expr : inExpressions) {
            processedExprList.add(process(expr));
        }
        return processedExprList;
    }
}
