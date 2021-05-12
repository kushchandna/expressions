package com.kush.lib.expressions.functions.samples;

import static com.kush.lib.expressions.types.Type.INTEGER;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.newMutableValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;

import com.kush.lib.expressions.functions.FunctionExecutor;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.factory.MutableTypedValue;

public class IntMultiplierExecutor implements FunctionExecutor {

    private final MutableTypedValue returnValue;

    public IntMultiplierExecutor() {
        returnValue = newMutableValue(INTEGER);
    }

    @Override
    public TypedValue execute(TypedValue... arguments) {
        int value = arguments[0].getInt();
        int multiplier = arguments[1].getInt();
        return value(value * multiplier, returnValue);
    }
}
