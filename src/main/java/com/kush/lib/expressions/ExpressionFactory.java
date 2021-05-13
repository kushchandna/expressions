package com.kush.lib.expressions;

import java.util.Collection;

import com.kush.lib.expressions.clauses.AdditionExpression;
import com.kush.lib.expressions.clauses.AndExpression;
import com.kush.lib.expressions.clauses.ConstantIntExpression;
import com.kush.lib.expressions.clauses.ConstantStringExpression;
import com.kush.lib.expressions.clauses.DivisionExpression;
import com.kush.lib.expressions.clauses.EqualsExpression;
import com.kush.lib.expressions.clauses.FieldExpression;
import com.kush.lib.expressions.clauses.FunctionExpression;
import com.kush.lib.expressions.clauses.GreaterThanEqualsExpression;
import com.kush.lib.expressions.clauses.GreaterThanExpression;
import com.kush.lib.expressions.clauses.InExpression;
import com.kush.lib.expressions.clauses.LessThanEqualsExpression;
import com.kush.lib.expressions.clauses.LessThanExpression;
import com.kush.lib.expressions.clauses.MultiplicationExpression;
import com.kush.lib.expressions.clauses.NotExpression;
import com.kush.lib.expressions.clauses.OrExpression;
import com.kush.lib.expressions.clauses.SubtractionExpression;

public interface ExpressionFactory {

    FieldExpression createFieldExpression(String fieldName);

    AndExpression createAndExpression(Expression leftExpr, Expression rightExpr);

    OrExpression createOrExpression(Expression leftExpr, Expression rightExpr);

    NotExpression createNotExpression(Expression childExpr);

    EqualsExpression createEqualsExpression(Expression leftExpr, Expression rightExpr);

    InExpression createInExpression(Expression targetExpr, Collection<Expression> inExprs);

    GreaterThanExpression createGreaterThanExpression(Expression leftExpr, Expression rightExpr);

    GreaterThanEqualsExpression createGreaterThanEqualsExpression(Expression leftExpr, Expression rightExpr);

    LessThanExpression createLessThanExpression(Expression leftExpr, Expression rightExpr);

    LessThanEqualsExpression createLessThanEqualsExpression(Expression leftExpr, Expression rightExpr);

    ConstantStringExpression createConstantStringExpression(String value);

    ConstantIntExpression createConstantIntExpression(int value);

    FunctionExpression createFunctionExpression(String functionName, Collection<Expression> arguments);

    AdditionExpression createAdditionExpression(Expression leftExpr, Expression rightExpr);

    SubtractionExpression createSubtractionExpression(Expression leftExpr, Expression rightExpr);

    MultiplicationExpression createMultiplicationExpression(Expression leftExpr, Expression rightExpr);

    DivisionExpression createDivisionExpression(Expression leftExpr, Expression rightExpr);
}
