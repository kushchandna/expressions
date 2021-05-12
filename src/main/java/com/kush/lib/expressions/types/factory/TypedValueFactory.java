package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.ImpactedByAutoBoxing;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

public class TypedValueFactory {

    public static TypedValue booleanValue(boolean value) {
        return new BooleanValue(value);
    }

    public static TypedValue byteValue(byte value) {
        return new ByteValue(value);
    }

    public static TypedValue charValue(char value) {
        return new CharValue(value);
    }

    public static TypedValue intValue(int value) {
        return new IntValue(value);
    }

    public static TypedValue longValue(long value) {
        return new LongValue(value);
    }

    public static TypedValue floatValue(float value) {
        return new FloatValue(value);
    }

    public static TypedValue doubleValue(double value) {
        return new DoubleValue(value);
    }

    public static TypedValue stringValue(String value) {
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

    public static MutableTypedValue booleanValue(boolean value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue byteValue(byte value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue charValue(char value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue intValue(int value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue longValue(long value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue floatValue(float value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue doubleValue(double value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue stringValue(String value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static MutableTypedValue mutableValue(Type type) {
        MutableTypedValueGenerator generator = new MutableTypedValueGenerator();
        return generator.process(type);
    }

    public static MutableTypedValue nullValue(MutableTypedValue typedValue) {
        typedValue.setNull(true);
        return typedValue;
    }
}
