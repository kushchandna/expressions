package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.handlers.TypeHandler;

class MutableTypedValueGenerator extends TypeHandler<MutableTypedValue> {

    @Override
    protected MutableTypedValue handleBoolean() {
        return new MutableBooleanValue();
    }

    @Override
    protected MutableTypedValue handleByte() {
        return new MutableByteValue();
    }

    @Override
    protected MutableTypedValue handleShort() {
        return new MutableShortValue();
    }

    @Override
    protected MutableTypedValue handleChar() {
        return new MutableCharValue();
    }

    @Override
    protected MutableTypedValue handleInt() {
        return new MutableIntValue();
    }

    @Override
    protected MutableTypedValue handleLong() {
        return new MutableLongValue();
    }

    @Override
    protected MutableTypedValue handleFloat() {
        return new MutableFloatValue();
    }

    @Override
    protected MutableTypedValue handleDouble() {
        return new MutableDoubleValue();
    }

    @Override
    protected MutableTypedValue handleString() {
        return new MutableStringValue();
    }

    @Override
    protected MutableTypedValue handleObject() {
        return new MutableObjectValue();
    }
}
