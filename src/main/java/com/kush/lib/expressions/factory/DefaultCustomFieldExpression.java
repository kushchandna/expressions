package com.kush.lib.expressions.factory;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.clauses.CustomFieldExpression;

public class DefaultCustomFieldExpression extends BaseUnaryExpression implements CustomFieldExpression {

    public DefaultCustomFieldExpression(Expression formula) {
        super(formula);
    }

    @Override
    public Expression getFormula() {
        return getChild();
    }

    @Override
    public String toString() {
        return String.valueOf(getFormula());
    }
}
