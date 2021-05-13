package com.kush.lib.expressions.types;

import com.kush.commons.markers.ImpactedByAutoBoxing;

public interface TypedValue extends Comparable<TypedValue>, Cloneable {

    Type getType();

    // TODO can be used to support function overloading
    default Class<?> getTypeClass() {
        return getType().getPrimaryClass();
    }

    boolean isNull();

    boolean getBoolean();

    byte getByte();

    short getShort();

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
