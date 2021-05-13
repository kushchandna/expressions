package com.kush.lib.expressions.functions.reflection;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.kush.lib.expressions.functions.FunctionExecutor;
import com.kush.lib.expressions.functions.FunctionSpec;
import com.kush.lib.expressions.functions.FunctonsRepository;

// TODO this class is currently not able to handle if two public static methods exists with same name
public class ClassFunctionsRepository implements FunctonsRepository {

    private final Map<String, FunctionSpec> functionSpecs;

    /**
     * @param klasses
     *            all classes passed should be public
     * @return
     */
    public static FunctonsRepository using(Class<?>... klasses) {
        return new ClassFunctionsRepository(asList(klasses));
    }

    private ClassFunctionsRepository(List<Class<?>> klasses) {
        functionSpecs = klasses.stream()
            .map(Class::getMethods)
            .flatMap(Arrays::stream)
            .filter(ClassFunctionsRepository::isPublicStatic)
            .map(ClassFunctionsRepository::toSpec)
            .collect(toMap(spec -> spec.getFunctionName().toUpperCase(), identity()));
    }

    @Override
    public Optional<FunctionSpec> getFunctionSpec(String functionName) {
        return Optional.ofNullable(functionSpecs.get(functionName.toUpperCase()));
    }

    private static boolean isPublicStatic(Method method) {
        int modifiers = method.getModifiers();
        return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers);
    }

    private static FunctionSpec toSpec(Method method) {
        FunctionExecutor executor = ReflectionBasedFunctionExecutor.forStaticMethod(method);
        return new FunctionSpec() {

            @Override
            public Class<?> getReturnType() {
                return method.getReturnType();
            }

            @Override
            public String getFunctionName() {
                return method.getName();
            }

            @Override
            public FunctionExecutor getFunctionExecutor() {
                return executor;
            }
        };
    }
}
