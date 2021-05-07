package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;

final class MutableDoubleValue extends BaseMutableTypedValue {

    private double value;

    @Override
    public void set(double value) {
        this.value = value;
    }

    @Override
    public double getDouble() {
        return value;
    }

    @Override
    public Object getObject() {
        return Double.valueOf(value);
    }

    @Override
    public Type getType() {
        return Type.DOUBLE;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return value == other.getDouble();
    }

    @Override
    protected int nonNullValueHashCode() {
        return Double.hashCode(value);
    }
}
