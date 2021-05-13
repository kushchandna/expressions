package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

final class ShortValue extends BaseTypedValue {

    private final short value;

    public ShortValue(short value) {
        this.value = value;
    }

    @Override
    public short getShort() {
        return value;
    }

    @Override
    public Object getObject() {
        return Short.valueOf(value);
    }

    @Override
    public Type getType() {
        return Type.SHORT;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return value == other.getShort();
    }

    @Override
    protected int nonNullValueHashCode() {
        return Short.hashCode(value);
    }

    @Override
    public TypedValue clone() {
        return new ShortValue(value);
    }
}
