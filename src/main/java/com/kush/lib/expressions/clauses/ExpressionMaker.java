package com.kush.lib.expressions.clauses;

import java.util.Collection;
import java.util.List;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionFactory;
import com.kush.lib.expressions.clauses.CaseExpression.Branch;
import com.kush.lib.expressions.factory.DefaultExpressionFactory;

public class ExpressionMaker {

    private static final ExpressionFactory factory = new DefaultExpressionFactory();

    public static Expression field(String field) {
        return factory.createFieldExpression(field);
    }

    public static Expression and(Expression left, Expression right) {
        return factory.createAndExpression(left, right);
    }

    public static Expression or(Expression left, Expression right) {
        return factory.createOrExpression(left, right);
    }

    public static Expression not(Expression expr) {
        return factory.createNotExpression(expr);
    }

    public static Expression equalTo(Expression left, Expression right) {
        return factory.createEqualsExpression(left, right);
    }

    public static Expression in(Expression targetExpr, Collection<Expression> inExprs) {
        return factory.createInExpression(targetExpr, inExprs);
    }

    public static Expression greaterThan(Expression left, Expression right) {
        return factory.createGreaterThanExpression(left, right);
    }

    public static Expression greaterThanOrEqualTo(Expression left, Expression right) {
        return factory.createGreaterThanEqualsExpression(left, right);
    }

    public static Expression lessThan(Expression left, Expression right) {
        return factory.createLessThanExpression(left, right);
    }

    public static Expression lessThanOrEqualTo(Expression left, Expression right) {
        return factory.createLessThanEqualsExpression(left, right);
    }

    public static Expression value(String value) {
        return factory.createConstantStringExpression(value);
    }

    public static Expression value(int value) {
        return factory.createConstantIntExpression(value);
    }

    public static Expression function(String functionName, Expression... arguments) {
        return factory.createFunctionExpression(functionName, List.of(arguments));
    }

    public static Expression plus(Expression left, Expression right) {
        return factory.createAdditionExpression(left, right);
    }

    public static Expression minus(Expression left, Expression right) {
        return factory.createSubtractionExpression(left, right);
    }

    public static Expression times(Expression left, Expression right) {
        return factory.createMultiplicationExpression(left, right);
    }

    public static Expression divide(Expression left, Expression right) {
        return factory.createDivisionExpression(left, right);
    }

    public static Expression conditional(List<Branch> branches, Expression target, Expression defaultReturn) {
        return factory.createCaseExpression(target, branches, defaultReturn);
    }

    public static CaseExpression.Branch ifThen(Expression condition, Expression result) {
        return new CaseExpression.Branch() {

            @Override
            public Expression getResult() {
                return result;
            }

            @Override
            public Expression getEntry() {
                return condition;
            }
        };
    }


    public interface Test {

        CaseExpression createCaseExpression(Expression reference, List<Branch> branches, Expression defaultReturn);
    }
}
