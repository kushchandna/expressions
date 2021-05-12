package com.kush.lib.expressions.types.factory;

import com.kush.lib.expressions.types.ImpactedByAutoBoxing;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

public class TypedValueFactory {

    public static TypedValue booleanValue(boolean value) {
        return new BooleanValue(value);
    }

    public static MutableTypedValue mutableBooleanValue() {
        return new MutableBooleanValue();
    }

    public static MutableTypedValue booleanValue(boolean value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static TypedValue byteValue(byte value) {
        return new ByteValue(value);
    }

    public static MutableTypedValue mutableByteValue() {
        return new MutableByteValue();
    }

    public static MutableTypedValue byteValue(byte value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static TypedValue charValue(char value) {
        return new CharValue(value);
    }

    public static MutableTypedValue mutableCharValue() {
        return new MutableCharValue();
    }

    public static MutableTypedValue charValue(char value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static TypedValue intValue(int value) {
        return new IntValue(value);
    }

    public static MutableTypedValue mutableIntValue() {
        return new MutableIntValue();
    }

    public static MutableTypedValue intValue(int value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static TypedValue longValue(long value) {
        return new LongValue(value);
    }

    public static MutableTypedValue mutableLongValue() {
        return new MutableLongValue();
    }

    public static MutableTypedValue longValue(long value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static TypedValue floatValue(float value) {
        return new FloatValue(value);
    }

    public static MutableTypedValue mutableFloatValue() {
        return new MutableFloatValue();
    }

    public static MutableTypedValue floatValue(float value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static TypedValue doubleValue(double value) {
        return new DoubleValue(value);
    }

    public static MutableTypedValue mutableDoubleValue() {
        return new MutableDoubleValue();
    }

    public static MutableTypedValue doubleValue(double value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static TypedValue stringValue(String value) {
        return new StringValue(value);
    }

    public static MutableTypedValue mutableStringValue() {
        return new MutableStringValue();
    }

    public static MutableTypedValue stringValue(String value, MutableTypedValue typedValue) {
        typedValue.set(value);
        return typedValue;
    }

    public static TypedValue nullValue(Type type) {
        return new NullValue(type);
    }

    public static MutableTypedValue nullValue(MutableTypedValue typedValue) {
        typedValue.setNull(true);
        return typedValue;
    }

    @ImpactedByAutoBoxing
    public static TypedValue nullableValue(Object value, Type type) {
        if (value == null) {
            return nullValue(type);
        }
        NonNullTypedValueGenerator generator = new NonNullTypedValueGenerator(value);
        return generator.process(type);
    }

    public static MutableTypedValue mutableNullableValue(Type type) {
        MutableTypedValueGenerator generator = new MutableTypedValueGenerator();
        return generator.process(type);
    }

    public static MutableTypedValue mutableNullableValue() {
        return new MutableObjectValue();
    }
}
