package com.kush.lib.expressions.factory;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.clauses.InExpression;

class DefaultInExpression implements InExpression {

    private final Expression targetExpr;
    private final Collection<Expression> inExprs;

    public DefaultInExpression(Expression targetExpr, Collection<Expression> inExprs) {
        this.targetExpr = targetExpr;
        this.inExprs = inExprs;
    }

    @Override
    public Collection<Expression> getChildren() {
        List<Expression> children = new ArrayList<>(inExprs.size() + 1);
        children.add(targetExpr);
        children.addAll(inExprs);
        return children;
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
