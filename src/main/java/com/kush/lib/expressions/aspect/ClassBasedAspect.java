package com.kush.lib.expressions.aspect;

import static com.kush.lib.expressions.types.Type.OBJECT;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.newMutableValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.nullValue;
import static com.kush.lib.expressions.types.factory.TypedValueFactory.value;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import com.kush.lib.expressions.AccessException;
import com.kush.lib.expressions.Accessor;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.factory.MutableTypedValue;
import com.kush.lib.expressions.types.factory.TypedValueFactory;
import com.kush.lib.expressions.types.processors.TypeHandler;

class ClassBasedAspect<T> extends BaseAspect<T> {

    public ClassBasedAspect(Class<T> clazz) {
        super(createFields(clazz));
    }

    private static <T> Collection<Field<T>> createFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
            .map(f -> ClassBasedAspect.<T>createField(f))
            .filter(Objects::nonNull)
            .collect(toList());
    }

    private static <T> Field<T> createField(java.lang.reflect.Field classField) {
        classField.setAccessible(true);
        String fieldName = classField.getName();
        Type fieldType = Type.forClass(classField.getType());
        MutableTypedValue mutableValue = newMutableValue(fieldType);
        Accessor<T> accessor = new Accessor<T>() {

            @Override
            public TypedValue access(T object) throws AccessException {
                ClassFieldToTypedResultHandler handler = new ClassFieldToTypedResultHandler(classField, object, mutableValue);
                try {
                    return handler.handle(fieldType);
                } catch (ExpressionException e) {
                    throw new AccessException(e.getMessage(), e);
                }
            }
        };
        if (fieldType == Type.OBJECT) {
            return null;
        }
        return new DefaultField<>(fieldName, fieldType, accessor);
    }

    private static class ClassFieldToTypedResultHandler extends TypeHandler<TypedValue> {

        private final java.lang.reflect.Field classField;
        private final Object object;
        private final MutableTypedValue mutableValue;

        public ClassFieldToTypedResultHandler(java.lang.reflect.Field classField, Object object, MutableTypedValue mutableValue) {
            this.classField = classField;
            this.object = object;
            this.mutableValue = mutableValue;
        }

        @Override
        protected TypedValue handleString() throws ExpressionException {
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
        }

        @Override
        protected TypedValue handleLong() throws ExpressionException {
            try {
                long value = classField.getLong(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        }

        @Override
        protected TypedValue handleFloat() throws ExpressionException {
            try {
                float value = classField.getFloat(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        }

        @Override
        protected TypedValue handleDouble() throws ExpressionException {
            try {
                double value = classField.getDouble(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        }

        @Override
        protected TypedValue handleBoolean() throws ExpressionException {
            try {
                boolean value = classField.getBoolean(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        }

        @Override
        protected TypedValue handleByte() throws ExpressionException {
            try {
                byte value = classField.getByte(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        }

        @Override
        protected TypedValue handleShort() throws ExpressionException {
            try {
                short value = classField.getShort(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        }

        @Override
        protected TypedValue handleChar() throws ExpressionException {
            try {
                char value = classField.getChar(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        }

        @Override
        protected TypedValue handleInt() throws ExpressionException {
            try {
                int value = classField.getInt(object);
                return value(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        }

        @Override
        protected TypedValue handleObject() throws ExpressionException {
            try {
                Object value = classField.get(object);
                return TypedValueFactory.nullableValue(value, OBJECT);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ExpressionException(e.getMessage(), e);
            }
        }
    }
}
