package com.kush.lib.expressions.factory;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.commons.BinomialExpression;

abstract class BaseBinomialExpression extends BaseExpression implements BinomialExpression {

    private final Expression left;
    private final Expression right;

    public BaseBinomialExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression getLeft() {
        return left;
    }

    @Override
    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(String.valueOf(getLeft()))
            .append(" ").append(getOperator()).append(" ")
            .append(String.valueOf(getRight()))
            .toString();
    }

    protected abstract String getOperator();
}