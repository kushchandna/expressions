package com.kush.lib.expressions.evaluators;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static com.kush.lib.expressions.utils.ExpressionUtils.evaluateConstantExpression;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.types.TypedValue;

public class InExpressionEvaluatorTest {

    @Test
    public void existingConstantValue() throws Exception {
        assertThat(eval("1 IN (1, 2)"), is(equalTo(value(true))));
    }

    @Test
    public void nonExistingConstantValue() throws Exception {
        assertThat(eval("1 IN (2, 3, 4)"), is(equalTo(value(false))));
    }

    private TypedValue eval(String sql) throws ExpressionException {
        return evaluateConstantExpression(sql);
    }
}
