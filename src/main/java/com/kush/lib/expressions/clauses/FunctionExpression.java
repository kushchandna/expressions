package com.kush.lib.expressions.clauses;

import java.util.Collection;

import com.kush.lib.expressions.Expression;

public interface FunctionExpression extends Expression {

    String getFunctionName();

    Collection<Expression> getArguments();
}
