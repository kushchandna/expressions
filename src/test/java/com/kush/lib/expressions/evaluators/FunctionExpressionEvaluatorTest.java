package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.ExpressionFactory;
import com.kush.lib.expressions.aspect.Aspect;
import com.kush.lib.expressions.aspect.AspectFieldEvaluationFactory;
import com.kush.lib.expressions.aspect.Aspects;
import com.kush.lib.expressions.factory.DefaultExpressionFactory;
import com.kush.lib.expressions.functions.FunctonsRepository;
import com.kush.lib.expressions.functions.samples.SampleFunctionsRepository;
import com.kush.lib.expressions.parsers.sql.SqlExpressionParser;

public class FunctionExpressionEvaluatorTest {

    @Test
    public void existingFunctionValue() throws Exception {
        Aspect<SampleObject> aspect = Aspects.classBased(SampleObject.class);
        FieldExpressionEvaluatorFactory<SampleObject> fieldEvalFactory = new AspectFieldEvaluationFactory<>(aspect);
        FunctonsRepository functionsRepo = new SampleFunctionsRepository();
        ExpressionEvaluatorFactory<SampleObject> evaluatorFactory =
                new DefaultExpressionEvaluatorFactory<>(fieldEvalFactory, functionsRepo);

        ExpressionFactory expressionFactory = new DefaultExpressionFactory();
        SqlExpressionParser parser = new SqlExpressionParser(expressionFactory);
        Expression expression = parser.parse("MultiplyInt(value, 3)");

        ExpressionEvaluator<SampleObject> evaluator = evaluatorFactory.create(expression);

        assertThat(evaluator.evaluate(sample(0)), is(equalTo(value(0))));
        assertThat(evaluator.evaluate(sample(1)), is(equalTo(value(3))));
        assertThat(evaluator.evaluate(sample(-1)), is(equalTo(value(-3))));
        assertThat(evaluator.evaluate(sample(25)), is(equalTo(value(75))));
        assertThat(evaluator.evaluate(sample(50)), is(equalTo(value(150))));
    }

    @Test
    public void nonExistingFunctionValue() throws Exception {
        Aspect<SampleObject> aspect = Aspects.classBased(SampleObject.class);
        FieldExpressionEvaluatorFactory<SampleObject> fieldEvalFactory = new AspectFieldEvaluationFactory<>(aspect);
        FunctonsRepository functionsRepo = new SampleFunctionsRepository();
        ExpressionEvaluatorFactory<SampleObject> evaluatorFactory =
                new DefaultExpressionEvaluatorFactory<>(fieldEvalFactory, functionsRepo);

        ExpressionFactory expressionFactory = new DefaultExpressionFactory();
        SqlExpressionParser parser = new SqlExpressionParser(expressionFactory);
        Expression expression = parser.parse("NonExistingFunction(value, 3)");

        assertThrows(ExpressionException.class, () -> {
            evaluatorFactory.create(expression);
        });
    }

    private static SampleObject sample(int value) {
        SampleObject object = new SampleObject();
        object.value = value;
        return object;
    }

    @SuppressWarnings("unused")
    private static class SampleObject {
        int value;
    }
}
