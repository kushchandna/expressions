package com.kush.lib.expressions.factory;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.clauses.AdditionExpression;

class DefaultAdditionExpression extends BaseBinomialExpression implements AdditionExpression {

    public DefaultAdditionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return "+";
    }
}
