package com.kush.lib.expressions.functions.reflection;

import static com.kush.lib.expressions.types.factory.TypedValueFactory.nullableValue;
import static java.util.Arrays.stream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.kush.commons.markers.ImpactedByAutoBoxing;
import com.kush.lib.expressions.functions.FunctionExecutionFailedException;
import com.kush.lib.expressions.functions.FunctionExecutor;
import com.kush.lib.expressions.types.Type;
import com.kush.lib.expressions.types.TypedValue;

@ImpactedByAutoBoxing
class ReflectionBasedFunctionExecutor implements FunctionExecutor {

    private final Object methodOwner;
    private final Method method;
    private final Type returnType;

    public static FunctionExecutor forMethod(Object methodOwner, Method method) {
        return new ReflectionBasedFunctionExecutor(methodOwner, method);
    }

    public static FunctionExecutor forStaticMethod(Method method) {
        return forMethod(null, method);
    }

    private ReflectionBasedFunctionExecutor(Object methodOwner, Method method) {
        this.methodOwner = methodOwner;
        this.method = method;
        returnType = Type.forClass(method.getReturnType());
    }

    @Override
    public TypedValue execute(TypedValue... arguments) throws FunctionExecutionFailedException {
        Object result = invokeMethod(arguments);
        return nullableValue(result, returnType);
    }

    private Object invokeMethod(TypedValue... arguments) throws FunctionExecutionFailedException {
        try {
            return method.invoke(methodOwner, stream(arguments)
                .map(TypedValue::getObject)
                .toArray());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new FunctionExecutionFailedException(e.getMessage(), e);
        }
    }
}
