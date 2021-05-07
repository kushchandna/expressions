package com.kush.lib.expressions.types.factory;

public abstract class BaseMutableTypedValue extends BaseTypedValue implements MutableTypedValue {

    private boolean isNull;

    public BaseMutableTypedValue() {
        setNull(false);
    }

    @Override
    public boolean isNull() {
        return isNull;
    }

    @Override
    public void setNull(boolean isNull) {
        this.isNull = isNull;
    }

    @Override
    public void set(boolean value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(byte value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(char value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(float value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(Object value) {
        throw new UnsupportedOperationException();
    }
}
