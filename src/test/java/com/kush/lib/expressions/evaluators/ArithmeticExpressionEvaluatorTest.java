package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static com.kush.lib.expressions.utils.ExpressionUtils.getEvaluator;
import static com.kush.lib.expressions.utils.ExpressionUtils.parseSql;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;

public class ArithmeticExpressionEvaluatorTest {

    @Test
    public void additionConstants() throws Exception {
        Expression expression = parseSql("1 + 2");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);
        assertThat(evaluator.evaluate(null), is(equalTo(value(3))));
    }

    @Test
    public void additionFieldAndConstants() throws Exception {
        Expression expression = parseSql("value1 + 2");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);

        assertThat(evaluator.evaluate(sample(0)), is(equalTo(value(2))));
        assertThat(evaluator.evaluate(sample(-2)), is(equalTo(value(0))));
        assertThat(evaluator.evaluate(sample(2)), is(equalTo(value(4))));
        assertThat(evaluator.evaluate(sample(-20)), is(equalTo(value(-18))));
    }

    @Test
    public void additionFieldAndField() throws Exception {
        Expression expression = parseSql("value1 + value2");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);

        assertThat(evaluator.evaluate(sample(0, 0)), is(equalTo(value(0))));
        assertThat(evaluator.evaluate(sample(-2, 2)), is(equalTo(value(0))));
        assertThat(evaluator.evaluate(sample(2, 5)), is(equalTo(value(7))));
        assertThat(evaluator.evaluate(sample(-20, -40)), is(equalTo(value(-60))));
    }

    @Test
    public void subtractionConstants() throws Exception {
        Expression expression = parseSql("1 - 2");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);
        assertThat(evaluator.evaluate(null), is(equalTo(value(-1))));
    }

    @Test
    public void subtractionFieldAndConstants() throws Exception {
        Expression expression = parseSql("value1 - 2");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);

        assertThat(evaluator.evaluate(sample(0)), is(equalTo(value(-2))));
        assertThat(evaluator.evaluate(sample(-2)), is(equalTo(value(-4))));
        assertThat(evaluator.evaluate(sample(2)), is(equalTo(value(0))));
        assertThat(evaluator.evaluate(sample(-20)), is(equalTo(value(-22))));
    }

    @Test
    public void subtractionFieldAndField() throws Exception {
        Expression expression = parseSql("value1 - value2");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);

        assertThat(evaluator.evaluate(sample(0, 0)), is(equalTo(value(0))));
        assertThat(evaluator.evaluate(sample(-2, 2)), is(equalTo(value(-4))));
        assertThat(evaluator.evaluate(sample(2, 5)), is(equalTo(value(-3))));
        assertThat(evaluator.evaluate(sample(-20, -40)), is(equalTo(value(20))));
    }

    private static SampleObject sample(int value1) {
        SampleObject obj = new SampleObject();
        obj.value1 = value1;
        return obj;
    }

    private static SampleObject sample(int value1, int value2) {
        SampleObject obj = new SampleObject();
        obj.value1 = value1;
        obj.value2 = value2;
        return obj;
    }

    @SuppressWarnings("unused")
    private static class SampleObject {
        int value1;
        int value2;
    }
}
