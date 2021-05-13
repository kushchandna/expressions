package com.kush.lib.expressions.utils;

import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.ExpressionProcessor;
import com.kush.lib.expressions.clauses.AdditionExpression;
import com.kush.lib.expressions.clauses.AndExpression;
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

public class BaseExpressionProcessor<T> extends ExpressionProcessor<T> {

    @Override
    protected T handle(FieldExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(AndExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(OrExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(NotExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(EqualsExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(InExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(GreaterThanExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(GreaterThanEqualsExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(LessThanExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(LessThanEqualsExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(ConstantStringExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(ConstantIntExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(FunctionExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(AdditionExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(SubtractionExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(MultiplicationExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    @Override
    protected T handle(DivisionExpression expression) throws ExpressionException {
        return getDefaultValue();
    }

    protected T getDefaultValue() {
        throw new UnsupportedOperationException();
    }
}
