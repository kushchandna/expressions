package com.kush.lib.expressions.functions;

import java.util.Optional;

public interface FunctonsRepository {

    Optional<FunctionSpec> getFunctionSpec(String functionName);
}
