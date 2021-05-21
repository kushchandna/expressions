package com.kush.lib.expressions.factory;

import static java.util.stream.Collectors.joining;

import java.util.Collection;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.clauses.InExpression;

class DefaultInExpression extends BaseExpression implements InExpression {

    private final Expression targetExpr;
    private final Collection<Expression> inExprs;

    public DefaultInExpression(Expression targetExpr, Collection<Expression> inExprs) {
        this.targetExpr = targetExpr;
        this.inExprs = inExprs;
    }

    @Override
    public Expression getTarget() {
        return targetExpr;
    }

    @Override
    public Collection<Expression> getInExpressions() {
        return inExprs;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(String.valueOf(getTarget()))
            .append(" ").append("IN").append(" ")
            .append("(")
            .append(getInExpressions().stream()
                .map(String::valueOf)
                .collect(joining(", ")))
            .append(")")
            .toString();
    }
}
