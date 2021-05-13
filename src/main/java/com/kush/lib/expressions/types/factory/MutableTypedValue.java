package com.kush.lib.expressions.types.factory;

import com.kush.commons.markers.ImpactedByAutoBoxing;
import com.kush.lib.expressions.types.TypedValue;

public interface MutableTypedValue extends TypedValue {

    void setNull(boolean isNull);

    void set(boolean value);

    void set(byte value);

    void set(short value);

    void set(char value);

    void set(int value);

    void set(long value);

    void set(float value);

    void set(double value);

    void set(String value);

    @ImpactedByAutoBoxing
    void set(Object value);
}
