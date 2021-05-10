package com.kush.lib.expressions.factory;

import com.kush.lib.expressions.Expression;

public abstract class BaseExpression implements Expression {

    @Override
    public boolean equals(Object obj) {
        return String.valueOf(this).equals(String.valueOf(obj));
    }

    @Override
    public int hashCode() {
        return String.valueOf(this).hashCode();
    }
}
