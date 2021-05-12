package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

class MutableObjectValue extends BaseMutableTypedValue {

    private Object value;

    @Override
    public void set(boolean value) {
        setObject(value);
    }

    @Override
    public void set(byte value) {
        setObject(value);
    }

    @Override
    public void set(char value) {
        setObject(value);
    }

    @Override
    public void set(int value) {
        setObject(value);
    }

    @Override
    public void set(long value) {
        setObject(value);
    }

    @Override
    public void set(float value) {
        setObject(value);
    }

    @Override
    public void set(double value) {
        setObject(value);
    }

    @Override
    public void set(Object value) {
        setObject(value);
    }

    @Override
    public Object getObject() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.OBJECT;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return getObject().equals(other.getObject());
    }

    @Override
    protected int nonNullValueHashCode() {
        return getObject().hashCode();
    }

    private void setObject(Object value) {
        this.value = value;
    }

    @Override
    public TypedValue clone() {
        MutableTypedValue mutableValue = new MutableStringValue();
        mutableValue.set(value);
        return mutableValue;
    }
}
