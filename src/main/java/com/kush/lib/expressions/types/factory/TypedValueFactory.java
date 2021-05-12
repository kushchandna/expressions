package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.ImpactedByAutoBoxing;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

public class TypedValueFactory {

    public static TypedValue value(boolean value) {
        return new BooleanValue(value);
    }

    public static TypedValue value(byte value) {
        return new ByteValue(value);
    }

    public static TypedValue value(char value) {
        return new CharValue(value);
    }

    public static TypedValue value(int value) {
        return new IntValue(value);
    }

    public static TypedValue value(long value) {
        return new LongValue(value);
    }

    public static TypedValue value(float value) {
        return new FloatValue(value);
    }

    public static TypedValue value(double value) {
        return new DoubleValue(value);
    }

    public static TypedValue value(String value) {
        return new StringValue(value);
    }

    public static TypedValue nullValue(Type type) {
        return new NullValue(type);
    }

    @ImpactedByAutoBoxing
    public static TypedValue nullableValue(Object value, Type type) {
        if (value == null) {
            return nullValue(type);
        }
        NonNullTypedValueGenerator generator = new NonNullTypedValueGenerator(value);
        return generator.process(type);
    }

    public static MutableTypedValue value(boolean value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue value(byte value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue value(char value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue value(int value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue value(long value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue value(float value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue value(double value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue value(String value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue value(Object value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue newMutableValue(Type type) {
        MutableTypedValueGenerator generator = new MutableTypedValueGenerator();
        return generator.process(type);
    }

    public static MutableTypedValue nullValue(MutableTypedValue typedValue) {
        typedValue.setNull(true);
        return typedValue;
    }
}
