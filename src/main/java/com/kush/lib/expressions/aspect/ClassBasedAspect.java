package com.kush.lib.expressions.aspect;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.newMutableValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.kush.lib.expressions.AccessException;
import com.kush.lib.expressions.Accessor;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.aspect.ObjectToTypedValueFunctionGetter.FieldValueGetter;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;
import com.kush.lib.expressions.types.factory.MutableTypedValue;

class ClassBasedAspect<T> extends BaseAspect<T> {

    public ClassBasedAspect(Class<T> clazz) throws ExpressionException {
        super(createFields(clazz));
    }

    private static <T> Collection<Field<T>> createFields(Class<?> clazz) throws ExpressionException {
        List<Field<T>> fields = new ArrayList<>();
        for (java.lang.reflect.Field classField : clazz.getDeclaredFields()) {
            Field<T> field = createField(classField);
            fields.add(field);
        }
        return fields;
    }

    private static <T> Field<T> createField(java.lang.reflect.Field classField) throws ExpressionException {
        classField.setAccessible(true);
        Type fieldType = Type.forClass(classField.getType());
        FieldValueGetter<T> objectEvaluator = getFieldValueGetter(classField, fieldType);
        Accessor<T> accessor = new Accessor<T>() {

            @Override
            public TypedValue access(T object) throws AccessException {
                try {
                    return objectEvaluator.evaluate(object);
                } catch (ExpressionException e) {
                    throw new AccessException(e.getMessage(), e);
                }
            }
        };
        return new DefaultField<>(classField.getName(), fieldType, accessor);
    }

    private static <T> FieldValueGetter<T> getFieldValueGetter(java.lang.reflect.Field classField, Type fieldType)
            throws ExpressionException {
        MutableTypedValue mutableValue = newMutableValue(fieldType);
        ObjectToTypedValueFunctionGetter<T> functionGetter = new ObjectToTypedValueFunctionGetter<>(classField, mutableValue);
        return functionGetter.handle(fieldType);
    }
}
