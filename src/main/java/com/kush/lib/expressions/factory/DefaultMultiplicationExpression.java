package com.kush.lib.expressions.factory;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.clauses.MultiplicationExpression;

class DefaultMultiplicationExpression extends BaseBinomialExpression implements MultiplicationExpression {

    public DefaultMultiplicationExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return "*";
    }
}
