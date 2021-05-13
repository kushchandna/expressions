package com.kush.lib.expressions.factory;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.clauses.SubtractionExpression;

class DefaultSubtractionExpression extends BaseBinomialExpression implements SubtractionExpression {

    public DefaultSubtractionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return "-";
    }
}
