package com.kush.lib.expressions.factory;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.commons.UnaryExpression;

abstract class BaseUnaryExpression extends BaseExpression implements UnaryExpression {

    private final Expression child;

    public BaseUnaryExpression(Expression child) {
        this.child = child;
    }

    @Override
    public Expression getChild() {
        return child;
    }
}
