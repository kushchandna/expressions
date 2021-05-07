package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;

final class MutableLongValue extends BaseMutableTypedValue {

    private long value;

    @Override
    public void set(long value) {
        this.value = value;
    }

    @Override
    public long getLong() {
        return value;
    }

    @Override
    public Object getObject() {
        return Long.valueOf(value);
    }

    @Override
    public Type getType() {
        return Type.LONG;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return value == other.getLong();
    }

    @Override
    protected int nonNullValueHashCode() {
        return Long.hashCode(value);
    }
}
