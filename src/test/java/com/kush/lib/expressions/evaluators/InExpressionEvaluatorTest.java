package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionFactory;
import com.kush.lib.expressions.factory.DefaultExpressionFactory;
import com.kush.lib.expressions.types.TypedValue;

public class InExpressionEvaluatorTest {

    @Test
    public void existingConstantValue() throws Exception {
        ExpressionFactory expressionFactory = new DefaultExpressionFactory();
        ExpressionEvaluatorFactory<?> evaluatorFactory = new DefaultExpressionEvaluatorFactory<>(null, null);
        Expression inExpression = expressionFactory.createInExpression(
                expressionFactory.createConstantIntExpression(1),
                asList(
                        expressionFactory.createConstantIntExpression(1),
                        expressionFactory.createConstantIntExpression(2)));
        ExpressionEvaluator<?> evaluator = evaluatorFactory.create(inExpression);
        TypedValue value = evaluator.evaluate(null);
        assertThat(value, is(equalTo(value(true))));
    }

    @Test
    public void nonExistingConstantValue() throws Exception {
        ExpressionFactory expressionFactory = new DefaultExpressionFactory();
        ExpressionEvaluatorFactory<?> evaluatorFactory = new DefaultExpressionEvaluatorFactory<>(null, null);
        Expression inExpression = expressionFactory.createInExpression(
                expressionFactory.createConstantIntExpression(1),
                asList(
                        expressionFactory.createConstantIntExpression(2),
                        expressionFactory.createConstantIntExpression(3)));
        ExpressionEvaluator<?> evaluator = evaluatorFactory.create(inExpression);
        TypedValue value = evaluator.evaluate(null);
        assertThat(value, is(equalTo(value(false))));
    }
}
