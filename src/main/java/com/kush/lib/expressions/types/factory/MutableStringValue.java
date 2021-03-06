package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

class MutableStringValue extends BaseMutableTypedValue {

    private String value;

    @Override
    public void set(String value) {
        this.value = value;
    }

    @Override
    public String getString() {
        return value;
    }

    @Override
    public Object getObject() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return value.equals(other.getString());
    }

    @Override
    protected int nonNullValueHashCode() {
        return value.hashCode();
    }

    @Override
    public TypedValue clone() {
        MutableTypedValue mutableValue = new MutableStringValue();
        mutableValue.set(value);
        return mutableValue;
    }
}
