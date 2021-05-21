package com.kush.lib.expressions.factory;

import java.util.Collection;
import java.util.List;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.clauses.CaseExpression;

class DefaultCaseExpression implements CaseExpression {

    private final Expression reference;
    private final List<Branch> branches;
    private final Expression defaultReturn;

    public DefaultCaseExpression(Expression reference, List<Branch> branches, Expression defaultReturn) {
        this.reference = reference;
        this.branches = branches;
        this.defaultReturn = defaultReturn;
    }

    @Override
    public Collection<Expression> getChildren() {
        return null;
    }

    @Override
    public Expression getReference() {
        return reference;
    }

    @Override
    public List<Branch> getBranches() {
        return branches;
    }

    @Override
    public Expression getDefaultBranch() {
        return defaultReturn;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("CASE");
        if (reference != null) {
            builder = builder.append(" ").append(reference);
        }
        builder = builder.append(" ");
        for (Branch branch : branches) {
            builder = builder
                .append("\n")
                .append("WHEN").append(" ").append(branch.getEntry()).append(":")
                .append("\n")
                .append("THEN").append(" ").append(branch.getResult()).append("\n");
        }
        if (defaultReturn != null) {
            builder = builder
                .append("\n")
                .append("ELSE").append(" ").append(defaultReturn);
        }
        return builder.toString();
    }
}
