package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.Type;

final class MutableCharValue extends BaseMutableTypedValue {

    private char value;

    @Override
    public void set(char value) {
        this.value = value;
    }

    @Override
    public char getChar() {
        return value;
    }

    @Override
    public Object getObject() {
        return Character.valueOf(value);
    }

    @Override
    public Type getType() {
        return Type.CHAR;
    }

    @Override
    protected boolean nonNullValueEquals(BaseTypedValue other) {
        return value == other.getChar();
    }

    @Override
    protected int nonNullValueHashCode() {
        return Character.hashCode(value);
    }
}
