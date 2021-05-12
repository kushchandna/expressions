package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

final class MutableIntValue extends BaseMutableTypedValue {

    private int value;

    @Override
    public void set(int value) {
        this.value = value;
    }

    @Override
    public int getInt() {
        return value;
    }

    @Override
    public Object getObject() {
        return Integer.valueOf(value);
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return value == other.getInt();
    }

    @Override
    protected int nonNullValueHashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public TypedValue clone() {
        MutableTypedValue mutableValue = new MutableIntValue();
        mutableValue.set(value);
        return mutableValue;
    }
}
