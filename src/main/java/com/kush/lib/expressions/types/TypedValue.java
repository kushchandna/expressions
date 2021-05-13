package com.kush.lib.expressions.types;

import com.kush.commons.markers.ImpactedByAutoBoxing;

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
