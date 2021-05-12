package com.kush.lib.expressions.functions.samples;

import static java.lang.String.CASE_INSENSITIVE_ORDER;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.kush.lib.expressions.functions.FunctionExecutor;
import com.kush.lib.expressions.functions.FunctionSpec;
import com.kush.lib.expressions.functions.FunctonsRepository;

public class SampleFunctionsRepository implements FunctonsRepository {

    private final Map<String, FunctionSpec> functionSpecs = new TreeMap<>(CASE_INSENSITIVE_ORDER);

    public SampleFunctionsRepository() {
        add(function("MultiplyInt", int.class, new IntMultiplierExecutor()));
    }

    @Override
    public Optional<FunctionSpec> getFunctionSpec(String functionName) {
        return Optional.ofNullable(functionSpecs.get(functionName));
    }

    private static FunctionSpec function(String functionName, Class<?> returnType, FunctionExecutor executor) {
        return new DefaultFunctionSpec(functionName, returnType, executor);
    }

    private void add(FunctionSpec functionSpec) {
        functionSpecs.put(functionSpec.getFunctionName(), functionSpec);
    }

    private static class DefaultFunctionSpec implements FunctionSpec {

        private final String functionName;
        private final Class<?> returnType;
        private final FunctionExecutor executor;

        public DefaultFunctionSpec(String functionName, Class<?> returnType, FunctionExecutor executor) {
            this.functionName = functionName;
            this.returnType = returnType;
            this.executor = executor;
        }

        @Override
        public String getFunctionName() {
            return functionName;
        }

        @Override
        public Class<?> getReturnType() {
            return returnType;
        }

        @Override
        public FunctionExecutor getFunctionExecutor() {
            return executor;
        }
    }
}
