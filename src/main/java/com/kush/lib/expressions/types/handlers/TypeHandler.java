package com.kush.lib.expressions.types.handlers;

import com.kush.commons.markers.ImpactedByAutoBoxing;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.types.Type;

@ImpactedByAutoBoxing
public abstract class TypeHandler<T> {

    public final T handle(Type type) throws ExpressionException {
        switch (type) {
        case BOOLEAN:
            return handleBoolean();
        case BYTE:
            return handleByte();
        case SHORT:
            return handleShort();
        case CHAR:
            return handleChar();
        case INTEGER:
            return handleInt();
        case LONG:
            return handleLong();
        case FLOAT:
            return handleFloat();
        case DOUBLE:
            return handleDouble();
        case STRING:
            return handleString();
        case OBJECT:
            return handleObject();
        default:
            throw new UnsupportedOperationException();
        }
    }

    protected abstract T handleBoolean() throws ExpressionException;

    protected abstract T handleByte() throws ExpressionException;

    protected abstract T handleShort() throws ExpressionException;

    protected abstract T handleChar() throws ExpressionException;

    protected abstract T handleInt() throws ExpressionException;

    protected abstract T handleLong() throws ExpressionException;

    protected abstract T handleFloat() throws ExpressionException;

    protected abstract T handleDouble() throws ExpressionException;

    protected abstract T handleString() throws ExpressionException;

    protected abstract T handleObject() throws ExpressionException;
}
