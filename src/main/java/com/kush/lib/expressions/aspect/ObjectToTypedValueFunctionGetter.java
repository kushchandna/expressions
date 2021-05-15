package com.kush.lib.expressions.aspect;

import static com.kush.lib.expressions.types.Type.OBJECT;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.nullValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;

import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.aspect.ObjectToTypedValueFunctionGetter.FieldValueGetter;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.factory.MutableTypedValue;
import com.kush.lib.expressions.types.factory.TypedValueFactory;
import com.kush.lib.expressions.types.handlers.TypeHandler;

class ObjectToTypedValueFunctionGetter<T> extends TypeHandler<FieldValueGetter<T>> {

    private final java.lang.reflect.Field classField;
    private final MutableTypedValue mutableValue;

    public ObjectToTypedValueFunctionGetter(java.lang.reflect.Field classField, MutableTypedValue mutableValue) {
        this.classField = classField;
        this.mutableValue = mutableValue;
    }

    @Override
    protected FieldValueGetter<T> handleString() throws ExpressionException {
        return object -> {
            try {
                Object value = classField.get(object);
                if (value == null) {
                    return nullValue(mutableValue);
                } else {
                    return value((String) value);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    @Override
    protected FieldValueGetter<T> handleLong() throws ExpressionException {
        return object -> {
            try {
                long value = classField.getLong(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    @Override
    protected FieldValueGetter<T> handleFloat() throws ExpressionException {
        return object -> {
            try {
                float value = classField.getFloat(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    @Override
    protected FieldValueGetter<T> handleDouble() throws ExpressionException {
        return object -> {
            try {
                double value = classField.getDouble(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    @Override
    protected FieldValueGetter<T> handleBoolean() throws ExpressionException {
        return object -> {
            try {
                boolean value = classField.getBoolean(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    @Override
    protected FieldValueGetter<T> handleByte() throws ExpressionException {
        return object -> {
            try {
                byte value = classField.getByte(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    @Override
    protected FieldValueGetter<T> handleShort() throws ExpressionException {
        return object -> {
            try {
                short value = classField.getShort(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    @Override
    protected FieldValueGetter<T> handleChar() throws ExpressionException {
        return object -> {
            try {
                char value = classField.getChar(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    @Override
    protected FieldValueGetter<T> handleInt() throws ExpressionException {
        return object -> {
            try {
                int value = classField.getInt(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    @Override
    protected FieldValueGetter<T> handleObject() throws ExpressionException {
        return object -> {
            try {
                Object value = classField.get(object);
                return TypedValueFactory.nullableValue(value, OBJECT);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        };
    }

    interface FieldValueGetter<T> {

        TypedValue evaluate(T object) throws ExpressionException;
    }
}
