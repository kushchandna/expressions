package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static com.kush.lib.expressions.utils.ExpressionUtils.getEvaluator;
import static com.kush.lib.expressions.utils.ExpressionUtils.getEvaluatorFactory;
import static com.kush.lib.expressions.utils.ExpressionUtils.parseSql;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;

public class FunctionExpressionEvaluatorTest {

    @Test
    public void existingFunctionValue() throws Exception {
        Expression expression = parseSql("MultiplyInt(value, 3)");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class, DummySupportedFunctions.class);

        assertThat(evaluator.evaluate(sample(0)), is(equalTo(value(0))));
        assertThat(evaluator.evaluate(sample(1)), is(equalTo(value(3))));
        assertThat(evaluator.evaluate(sample(-1)), is(equalTo(value(-3))));
        assertThat(evaluator.evaluate(sample(25)), is(equalTo(value(75))));
        assertThat(evaluator.evaluate(sample(50)), is(equalTo(value(150))));
    }

    @Test
    public void nonExistingFunctionValue() throws Exception {
        Expression expression = parseSql("NonExistingFunction(value, 3)");
        ExpressionEvaluatorFactory<SampleObject> evaluatorFactory =
                getEvaluatorFactory(SampleObject.class, DummySupportedFunctions.class);

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
