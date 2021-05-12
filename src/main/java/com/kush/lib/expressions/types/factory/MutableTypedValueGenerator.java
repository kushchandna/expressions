package com.kush.lib.expressions.types.factory;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.mutableBooleanValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.mutableByteValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.mutableCharValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.mutableDoubleValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.mutableFloatValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.mutableIntValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.mutableLongValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.mutableNullableValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.mutableStringValue;

import com.kush.lib.expressions.types.processors.TypeProcessor;

class MutableTypedValueGenerator extends TypeProcessor<MutableTypedValue> {

    @Override
    protected MutableTypedValue handleBoolean() {
        return mutableBooleanValue();
    }

    @Override
    protected MutableTypedValue handleByte() {
        return mutableByteValue();
    }

    @Override
    protected MutableTypedValue handleChar() {
        return mutableCharValue();
    }

    @Override
    protected MutableTypedValue handleInt() {
        return mutableIntValue();
    }

    @Override
    protected MutableTypedValue handleLong() {
        return mutableLongValue();
    }

    @Override
    protected MutableTypedValue handleFloat() {
        return mutableFloatValue();
    }

    @Override
    protected MutableTypedValue handleDouble() {
        return mutableDoubleValue();
    }

    @Override
    protected MutableTypedValue handleString() {
        return mutableStringValue();
    }

    @Override
    protected MutableTypedValue handleObject() {
        return mutableNullableValue();
    }
}
