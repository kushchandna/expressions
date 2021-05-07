package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;

final class MutableFloatValue extends BaseMutableTypedValue {

    private float value;

    @Override
    public void set(float value) {
        this.value = value;
    }

    @Override
    public float getFloat() {
        return value;
    }

    @Override
    public Object getObject() {
        return Float.valueOf(value);
    }

    @Override
    public Type getType() {
        return Type.FLOAT;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return value == other.getFloat();
    }

    @Override
    protected int nonNullValueHashCode() {
        return Float.hashCode(value);
    }
}
