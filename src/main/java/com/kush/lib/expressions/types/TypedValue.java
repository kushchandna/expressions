package com.kush.lib.expressions.types;

public interface TypedValue extends Comparable<TypedValue>, Cloneable {

    Type getType();

    boolean isNull();

    boolean getBoolean();

    byte getByte();

    char getChar();

    int getInt();

    long getLong();

    float getFloat();

    double getDouble();

    String getString();

    @ImpactedByAutoBoxing
    Object getObject();

    TypedValue clone();
}
