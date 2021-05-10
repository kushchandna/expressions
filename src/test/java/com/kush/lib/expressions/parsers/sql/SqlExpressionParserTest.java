package com.kush.lib.expressions.parsers.sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionFactory;
import com.kush.lib.expressions.clauses.FunctionExpression;
import com.kush.lib.expressions.factory.DefaultExpressionFactory;

public class SqlExpressionParserTest {

    @Test
    public void functionExpr() throws Exception {
        ExpressionFactory exprFactory = new DefaultExpressionFactory();
        SqlExpressionParser parser = new SqlExpressionParser(exprFactory);
        Expression expression = parser.parse("TestFunction( 1, 'hello', field1 )");

        assertThat(expression, is(instanceOf(FunctionExpression.class)));
        FunctionExpression functionExpr = (FunctionExpression) expression;
        assertThat(functionExpr.getFunctionName(), is(equalTo("TestFunction")));
        assertThat(functionExpr.getArguments(), contains(
                exprFactory.createConstantIntExpression(1),
                exprFactory.createConstantStringExpression("hello"),
                exprFactory.createFieldExpression("field1")));
    }
}
