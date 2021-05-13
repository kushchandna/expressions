package com.kush.lib.expressions.types.factory;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;

import com.kush.commons.markers.ImpactedByAutoBoxing;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.processors.TypeProcessor;

@ImpactedByAutoBoxing
class NonNullTypedValueGenerator extends TypeProcessor<TypedValue> {

    private final Object value;

    public NonNullTypedValueGenerator(Object value) {
        this.value = value;
    }

    @Override
    protected TypedValue handleBoolean() {
        return value((Boolean) value);
    }

    @Override
    protected TypedValue handleByte() {
        return value((Byte) value);
    }

    @Override
    protected TypedValue handleChar() {
        return value((Character) value);
    }

    @Override
    protected TypedValue handleInt() {
        return value((Integer) value);
    }

    @Override
    protected TypedValue handleLong() {
        return value((Long) value);
    }

    @Override
    protected TypedValue handleFloat() {
        return value((Float) value);
    }

    @Override
    protected TypedValue handleDouble() {
        return value((Double) value);
    }

    @Override
    protected TypedValue handleString() {
        return value((String) value);
    }

    @Override
    protected TypedValue handleObject() {
        throw new UnsupportedOperationException();
    }
}
