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

public class CaseExpressionEvaluatorTest {

    @Test
    public void constants() throws Exception {
        Expression expression = parseSql(""
                + "CASE 37 "
                + " WHEN 31 + 1 "
                + "     THEN 'wrong' "
                + " WHEN 32 + 5 "
                + "     THEN 'right' "
                + "END");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);
        assertThat(evaluator.evaluate(null), is(equalTo(value("right"))));
    }

    @Test
    public void constantsWithElse() throws Exception {
        Expression expression = parseSql(""
                + "CASE 37 "
                + " WHEN 31 + 1 "
                + "     THEN 'wrong' "
                + " WHEN 32 + 8 "
                + "     THEN 'wrong again' "
                + " ELSE "
                + "     'right' "
                + "END");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);
        assertThat(evaluator.evaluate(null), is(equalTo(value("right"))));
    }

    @Test
    public void withSwicth() throws Exception {
        Expression expression = parseSql(""
                + "CASE value1 "
                + " WHEN 1 "
                + "     THEN value2 + 1 "
                + " WHEN 2 "
                + "     THEN value2 + 2 "
                + " ELSE 0 "
                + "END");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);

        assertThat(evaluator.evaluate(sample(1, 2)), is(equalTo(value(3))));
        assertThat(evaluator.evaluate(sample(2, 2)), is(equalTo(value(4))));
        assertThat(evaluator.evaluate(sample(3, 2)), is(equalTo(value(0))));
    }

    @Test
    public void withoutSwicth() throws Exception {
        Expression expression = parseSql(""
                + "CASE "
                + " WHEN value1 = 1 "
                + "     THEN value2 + 1 "
                + " WHEN value1 = 2 "
                + "     THEN value2 + 2 "
                + " ELSE 0 "
                + "END");
        ExpressionEvaluator<SampleObject> evaluator = getEvaluator(expression, SampleObject.class);

        assertThat(evaluator.evaluate(sample(1, 2)), is(equalTo(value(3))));
        assertThat(evaluator.evaluate(sample(2, 2)), is(equalTo(value(4))));
        assertThat(evaluator.evaluate(sample(3, 2)), is(equalTo(value(0))));
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
