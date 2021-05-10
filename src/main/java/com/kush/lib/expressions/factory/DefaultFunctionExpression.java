package com.kush.lib.expressions.factory;

import static java.util.stream.Collectors.joining;

import java.util.Collection;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.clauses.FunctionExpression;

class DefaultFunctionExpression extends BaseExpression implements FunctionExpression {

    private final String functionName;
    private final Collection<Expression> arguments;

    public DefaultFunctionExpression(String functionName, Collection<Expression> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    @Override
    public String getFunctionName() {
        return functionName;
    }

    @Override
    public Collection<Expression> getArguments() {
        return arguments;
    }

    @Override
    public Collection<Expression> getChildren() {
        return getArguments();
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(getFunctionName())
            .append("(")
            .append(getArguments().stream()
                .map(String::valueOf)
                .collect(joining(", ")))
            .append(")")
            .toString();
    }
}
