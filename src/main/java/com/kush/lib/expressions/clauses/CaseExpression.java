package com.kush.lib.expressions.clauses;

import java.util.List;

import com.kush.lib.expressions.Expression;

/*
 * CASE field1 // reference
 * WHEN val11
 * THEN val21
 * WHEN val12
 * THEN val22
 * ELSE
 * val23
 * END
 *
 * CASE
 * WHEN field1 = val11
 * THEN val21
 * WHEN field1 = val12
 * THEN val22
 * ELSE
 * val23
 * END
 */
public interface CaseExpression extends Expression {

    Expression getReference();

    List<Branch> getBranches();

    Expression getDefaultBranch();

    interface Branch {

        Expression getEntry();

        Expression getResult();
    }
}
