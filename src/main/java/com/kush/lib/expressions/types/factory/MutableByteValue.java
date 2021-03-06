package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

final class MutableByteValue extends BaseMutableTypedValue {

    private byte value;

    @Override
    public void set(byte value) {
        this.value = value;
    }

    @Override
    public byte getByte() {
        return value;
    }

    @Override
    public Object getObject() {
        return Byte.valueOf(value);
    }

    @Override
    public Type getType() {
        return Type.BYTE;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return value == other.getByte();
    }

    @Override
    protected int nonNullValueHashCode() {
        return Byte.hashCode(value);
    }

    @Override
    public TypedValue clone() {
        MutableTypedValue mutableValue = new MutableByteValue();
        mutableValue.set(value);
        return mutableValue;
    }
}
