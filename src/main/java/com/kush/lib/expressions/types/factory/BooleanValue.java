package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

final class BooleanValue extends BaseTypedValue {

    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getBoolean() {
        return value;
    }

    @Override
    public Object getObject() {
        return Boolean.valueOf(value);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return value == other.getBoolean();
    }

    @Override
    protected int nonNullValueHashCode() {
        return Boolean.hashCode(value);
    }

    @Override
    public TypedValue clone() {
        return new BooleanValue(value);
    }
}
