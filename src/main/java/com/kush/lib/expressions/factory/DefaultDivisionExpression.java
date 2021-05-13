package com.kush.lib.expressions.factory;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.clauses.DivisionExpression;

class DefaultDivisionExpression extends BaseBinomialExpression implements DivisionExpression {

    public DefaultDivisionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return "/";
    }
}
